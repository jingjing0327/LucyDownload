package com.chazuo.college.enterprise.download;

import android.os.Environment;

import com.chazuo.college.enterprise.ui.download.ex.entity.CourseItem;
import com.chazuo.college.enterprise.util.NetUtil;
import com.chazuo.czlib.module.impl.CZController;
import com.chazuo.czlib.module.impl.CZKernel;

import java.io.File;
import java.util.List;

/**
 * Created by LiQiong on 2016/12/4 18:28
 */

public class DLDownloadTool {

    private static boolean isWifi;

    /**
     * 如果再手机网络下，切换到wifi下，下载继续
     * 如果是wifi下，切换到手机网络下，下载全部暂停,再次点击全部下载，便可再手机网络下下载file.
     */
    private static void handlerDownloadState() {
        isWifi = NetUtil.isWifi();
        if (!isWifi) {
            DLQueue.getInstance().pauseAll();
        }
    }

    /**
     *
     */
    private static final String localUrl = CZKernel.getInstance().getContext()
            .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/media")
            .getPath();

    /**
     * @param courseItemName
     * @return boolean
     */
    public static boolean isDownloadedByName(String courseItemName) {
        String path = getLocalFileByName(courseItemName);
        return new File(path).exists();
    }

    /**
     * @param courseItemName
     * @return
     */
    public static String getLocalFileByName(String courseItemName) {
        String path = localUrl + "/" + replaceSeparatorByName(courseItemName);
        return path;
    }

    /**
     * @param id
     */
    public static void updateCourseRadPoint(String id) {
        List<CourseItem> itemList = CZController.dbHelp.find(CourseItem.class, "id=?", new String[]{id});
        if (itemList.size() > 0) {
            CourseItem courseItem = itemList.get(0);
            courseItem.setClicked(true);
            CZController.dbHelp.update(courseItem, "id=?", new String[]{courseItem.getId() + ""});
        }
    }

    /**
     * @param path
     * @return
     */
    public static boolean isLocalMedia(String path) {
        if (path.contains(Environment.getExternalStorageDirectory().getPath())) {
            return true;
        }
        return false;
    }

    /**
     * 檢查是否又下載
     *
     * @param id
     * @return
     */
    public static boolean isClickDownload(String id) {
        int count = CZController.dbHelp.getCount(CourseItem.class, "id=?", new String[]{id});
        if (count <= 0)
            return false;
        else
            return true;
    }

    public static String replaceSeparatorByName(String name) {
        return name.replace("/", "");
    }
}
