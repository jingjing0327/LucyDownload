package com.chazuo.college.enterprise.download;

import android.text.TextUtils;
import android.util.Log;

import com.chazuo.czlib.module.impl.CZController;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LiQiong on 2016/10/27.
 */

public final class DLQueue {
    //任务列表
    private List<DLTask> tasks;

    private static DLQueue queue = null;
    private final int corePoolSize = 1;
    //当前运行的任务
    private DLTask currentTask;
    //存储持久化的接口
    private DBClient dbClient;

    private DLQueue() {
        tasks = new LinkedList<>();
        dbClient = new DBClientImpl();
        initUnfinishedTask();
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
        //如果是空
        if (TextUtils.isEmpty(task.getBuilder().getNetUrl()) && TextUtils.isEmpty(task.getBuilder().getName())) {
            return this;
        }
        //如果是 失敗，成功，暫停狀態。這個任務就不放到隊列中！
        if (task.getBuilder().getTaskType() == DLTaskType.FAIL ||
                task.getBuilder().getTaskType() == DLTaskType.SUCCESS ||
                task.getBuilder().getTaskType() == DLTaskType.PAUSE)
            return this;
        DLTask ifTask = getTask(task.getBuilder().getName(), task.getBuilder().getNetUrl());
        if (ifTask == null) {
            if (task.getBuilder().getTaskType() == null)
                task.getBuilder().setTaskType(DLTaskType.WAIT);
            tasks.add(task);
            dbClient.taskSaveNewOnly(new DBTask(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.WAIT.ordinal(), -1));
            Log.e("liqiong", task.getBuilder().getName() + "放到队列中了。");
        } else {
            Log.e("liqiong", "task name->" + task.getBuilder().getName() + "--->队列重复，不放到下载队列中！");
        }
        return this;
    }

    /**
     * @param task
     */
    public void delete(DLTask task) {
        List<DBTask> xxx= CZController.dbHelp.findAll(DBTask.class);

        pause(task);
        //删除DBTask
        dbClient.taskDelete(new DBTask(task.getBuilder().getName(), task.getBuilder().getNetUrl(), 88, 0));
        //删除 文件
        File tempFile = new File(task.getBuilder().getLocalUrl() + "/" + task.getBuilder().getName());
        if (tempFile.exists())
            tempFile.delete();
        //删除 courseItem

        List<DBTask> ooo= CZController.dbHelp.findAll(DBTask.class);
    }

    /**
     *
     */
    public void deleteAll() {
        for (DLTask task : tasks)
            delete(task);
    }

    private DLQueue remove(DLTask task) {
        tasks.remove(task);
        //注：这个 88 可以随便填写，后台不使用
        dbClient.taskDelete(new DBTask(task.getBuilder().getName(), task.getBuilder().getNetUrl(), 88, 0));
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
        if (task != null) {
            task.getBuilder().setTaskType(DLTaskType.PAUSE);
            dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.PAUSE.ordinal());
            tasks.remove(task);
            if (task.getBuilder().getDispatcher() != null)
                task.getBuilder().getDispatcher().shutdown();
        } else {
            CZController.uiHelp.toast("任务不存在！");
        }
    }

    private void pauseNotRemove(DLTask task) {
        if (task != null) {
            task.getBuilder().setTaskType(DLTaskType.PAUSE);
            dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.PAUSE.ordinal());
            if (task.getBuilder().getDispatcher() != null)
                task.getBuilder().getDispatcher().shutdown();
        } else {
            CZController.uiHelp.toast("任务不存在！");
        }
    }

    /**
     * 暂停所有
     */
    public void pauseAll() {
        for (DLTask task : tasks())
            pauseNotRemove(task);
        tasks.clear();
    }

    public void startAll(List<DLTask> startAllTask) {
        for (DLTask task : startAllTask) {
            if (task != currentTask) {
                task.getBuilder().setTaskType(DLTaskType.WAIT);
                dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.WAIT.ordinal());
            }
            enqueue(task);
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

                        //找个地方稍微有点垃圾，待修改
                        task.getBuilder().setTaskType(DLTaskType.DOWNLOADING);

                        dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.DOWNLOADING.ordinal());

                        new DLManage(task)
                                .client(HttpUrlClient.create())
                                .execute();
                        //
                    }
                    if (task.getBuilder().getTaskType() == DLTaskType.SUCCESS
                            || task.getBuilder().getTaskType() == DLTaskType.FAIL
                            || task.getBuilder().getTaskType() == DLTaskType.PAUSE) {

                        Log.e("liqiongqiong", "task.getBuilder().getDispatcher().shutdown();");
                        //线程池shutdown
                        task.getBuilder().getDispatcher().shutdown();
                        tasks.remove(task);

                        if (task.getBuilder().getTaskType() == DLTaskType.SUCCESS) {
                            //删除成功的dbTask
                            dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.SUCCESS.ordinal());
                        }
                        if (task.getBuilder().getTaskType() == DLTaskType.FAIL) {
                            dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.FAIL.ordinal());
                        }
                        if (task.getBuilder().getTaskType() == DLTaskType.PAUSE) {
                            dbClient.taskUpdate(task.getBuilder().getName(), task.getBuilder().getNetUrl(), DLTaskType.PAUSE.ordinal());
                        }
                    }
                } else {
                    currentTask = null;
                }
                execute();
            }
        }, 1500);
    }

    /**
     * 获取未完成的任务
     */
    public List<DBTask> getUnfinishedTask() {
        List<DBTask> dbTasks = dbClient.taskFind("type!=?", new String[]{DLTaskType.SUCCESS.ordinal() + ""});
        return dbTasks;
    }

    /**
     *
     */
    private void initUnfinishedTask() {
        List<DBTask> dbTasks = getUnfinishedTask();
        for (DBTask dbTask : dbTasks) {
            if (TextUtils.isEmpty(dbTask.getName()) || TextUtils.isEmpty(dbTask.getNetUrl()))
                continue;

            DLTask task = new DLTask.Builder()
                    .netUrl(dbTask.getNetUrl())
                    .name(dbTask.getName())
                    .build();

            task.getBuilder().setTaskType(DBConvertEnum(dbTask.getType()));

            if (task.getBuilder().getTaskType() == DLTaskType.DOWNLOADING ||
                    task.getBuilder().getTaskType() == DLTaskType.WAIT)
                enqueue(task);
        }
    }

    private DLTaskType DBConvertEnum(int type) {
        DLTaskType[] taskTypes = DLTaskType.values();
        for (DLTaskType taskType : taskTypes) {
            if (type == taskType.ordinal()) {
                return taskType;
            }
        }
        return null;
    }
}