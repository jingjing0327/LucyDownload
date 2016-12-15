package com.chazuo.college.enterprise.download;

import android.text.TextUtils;

import com.chazuo.czlib.db.DatabaseUtil;
import com.chazuo.czlib.module.impl.CZController;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by LiQiong on 2016/10/31.
 */

class DBClientImpl extends DBClient {

    @Override
    public void taskSaveNewOnly(DBTask task) {
        if (!TextUtils.isEmpty(task.getName()) && !TextUtils.isEmpty(task.getNetUrl())) {
            List<DBTask> findTask = taskFind("name=? and netUrl=?", new String[]{task.getName(), task.getNetUrl()});
            if (findTask.size() == 0) {
                CZController.dbHelp.save(task);
            } else if (findTask.size() >= 1) {
                taskUpdate(task.getName(),task.getNetUrl(),task.getType());
            }
        } else {
            CZController.uiHelp.toast("save fail!");
        }
    }

    @Override
    public void taskUpdate(DBTask task) {
        String[] whereArgs = {task.getName(), task.getNetUrl()};
        CZController.dbHelp.update(task, "name=? and netUrl=?", whereArgs);
    }

    @Override
    public void taskDelete(DBTask task) {
//        String where = "name=? and netUrl=? ";
//        String[] whereArgs = {task.getName(), task.getNetUrl()};
        String where = "netUrl=? ";
        String[] whereArgs = {task.getNetUrl()};
        int row=CZController.dbHelp.delete(DBTask.class, where, whereArgs);
    }

    @Override
    public List<DBTask> taskFind(String where, String[] whereArgs) {
        List<DBTask> findTask = CZController.dbHelp.find(DBTask.class, where, whereArgs);
        return findTask;
    }

    @Override
    public void taskUpdate(String name, String netUrl, int type) {
        String tableName = DatabaseUtil.getTableName(DBTask.class);
        String sql = "update "
                + tableName
                + " set type="
                + type
                + " where name ='"
                + name
                + "' and netUrl='"
                + netUrl
                +"'";
        CZController.dbHelp.update(sql);
    }
}
