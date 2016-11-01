package com.example.demo.download;

import android.text.TextUtils;
import android.util.Log;

import com.chazuo.czlib.module.impl.CZController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by LiQiong on 2016/10/27.
 */

public final class DLQueue {
    private static List<DLTask> tasks;
    private static DLQueue queue = null;
    private final int corePoolSize = 1;
    private DLTask currentTask;
    private DBClient dbClient;

    private DLQueue() {
        tasks = new LinkedList<>();
        dbClient = new DBClientImpl();
        execute();
    }

    public static DLQueue getInstance() {
        synchronized ("Instance") {
            if (queue == null) queue = new DLQueue();
        }
        return queue;
    }

    public List<DLTask> tasks() {
        return tasks;
    }

    /**
     * 放入队列的task，如果名字和下载地址不一样，才能放到队列中
     *
     * @param task
     * @return
     */
    public DLQueue enqueue(DLTask task) {
        DLTask ifTask = getTask(task.getBuilder().getName(), task.getBuilder().getNetUrl());
        if (ifTask == null) {
            task.getBuilder().setTaskType(DLTask.Builder.DTaskType.WAIT);
            tasks.add(task);
            dbClient.taskSaveNewOnly(new DBTask(task.getBuilder().getName(), task.getBuilder().getNetUrl(), 4));
        } else {
            Log.e("liqiong", "task name->" + task.getBuilder().getName() + "队列重复，不放到下载队列中！");
        }
        return this;
    }

    public DLQueue remove(DLTask task) {
        tasks.remove(task);
        dbClient.taskDelete(new DBTask(task.getBuilder().getName(), task.getBuilder().getNetUrl(), 3));
        return this;
    }

    public DLTask getTask(int index) {
        DLTask task = tasks.get(index);
        if (task != null) return task;
        return null;
    }

    public DLTask getTask(String name) {
        if (!TextUtils.isEmpty(name))
            for (DLTask task : tasks) {
                if (task.getBuilder().getName().equals(name)) {
                    return task;
                }
            }
        return null;
    }

    public DLTask getTask(String name, String netUrl) {
        if (!TextUtils.isEmpty(name)) {
            for (DLTask task : tasks) {
                if (task.getBuilder().getName().equals(name) && task.getBuilder().getNetUrl().equals(netUrl)) {
                    return task;
                }
            }
        }
        return null;
    }

    public DLTask getTask(Object tag) {
        if (tag != null)
            for (DLTask task : tasks) {
                if (task.getBuilder().getTag().equals(tag)) {
                    return task;
                }
            }
        return null;
    }

    public DLTask getTask(String name, Object tag) {
        if (!TextUtils.isEmpty(name) && tag != null)
            for (DLTask task : tasks) {
                if (task.getBuilder().getName().equals(name) && task.getBuilder().getTag().equals(tag)) {
                    return task;
                }
            }
        return null;
    }


    public DLTask getCurrentTask() {
        return currentTask;
    }


    /**
     *
     */
    public void pause(DLTask task) {
        if (currentTask != null) {
            currentTask.getBuilder().setTaskType(DLTask.Builder.DTaskType.PAUSE);
            dbClient.taskUpdate(new DBTask(task.getBuilder().getName(), task.getBuilder().getNetUrl(), 4));
        } else {
            CZController.uiHelp.toast("任务不存在！");
        }
    }

    public DBClient getDbClient() {
        return dbClient;
    }

    /**
     *
     */
    private void execute() {
        CZController.uiHelp.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tasks.size() >= 1) {
                    DLTask task = tasks.get(0);
                    if (task != null && task != currentTask) {
                        currentTask = task;
                        new DLManage(task)
                                .client(HttpUrlClient.create())
                                .setThreadCount(2)
                                .execute();

                        //找个地方稍微有点垃圾，待修改
                        task.getBuilder().setTaskType(DLTask.Builder.DTaskType.DOWNLOADING);
                        //

                        DBTask dbTask = new DBTask();
                        dbTask.setName(task.getBuilder().getName());
                        dbTask.setNetUrl(task.getBuilder().getNetUrl());
                        dbTask.setType(3);

                        dbClient.taskUpdate(dbTask);
                        //
                    }
                    if (task.getBuilder().getTaskType() == DLTask.Builder.DTaskType.FINISH
                            || task.getBuilder().getTaskType() == DLTask.Builder.DTaskType.FAIL
                            || task.getBuilder().getTaskType() == DLTask.Builder.DTaskType.PAUSE) {

                        Log.e("liqiongqiong", "task.getBuilder().getDispatcher().shutdown();");
                        //线程池shutdown
                        task.getBuilder().getDispatcher().shutdown();
                        tasks.remove(task);

                        DBTask dbTask = new DBTask();
                        dbTask.setName(task.getBuilder().getName());
                        dbTask.setNetUrl(task.getBuilder().getNetUrl());
                        if (task.getBuilder().getTaskType() == DLTask.Builder.DTaskType.FINISH) {
                            //删除成功的dbTask
                            dbTask.setType(1);
                            dbClient.taskDelete(dbTask);
                        }
                        if (task.getBuilder().getTaskType() == DLTask.Builder.DTaskType.FAIL) {
                            dbTask.setType(0);
                            dbClient.taskUpdate(dbTask);
                        }
                        if (task.getBuilder().getTaskType() == DLTask.Builder.DTaskType.PAUSE) {
                            dbTask.setType(4);
                            dbClient.taskUpdate(dbTask);
                        }
                    }
                } else {
                    currentTask = null;
                }
                execute();
            }
        }, 1000);
    }

    /**
     * 获取未完成的任务
     */
    public List<DBTask> unfinishedTask() {
        List<DBTask> dbTasks = dbClient.taskFind("type!=?", new String[]{"1"});
        Log.e("liqiong", dbTasks.toString());
        CZController.uiHelp.toast(dbTasks.toString());
        return dbTasks;
    }
}
