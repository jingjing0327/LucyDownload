package com.chazuo.college.enterprise.download;

import android.os.Environment;
import android.util.Log;

import com.chazuo.college.enterprise.ui.download.ex.entity.CourseItem;
import com.chazuo.czlib.module.impl.CZController;
import com.chazuo.czlib.module.impl.CZKernel;

import java.io.File;
import java.util.List;

/**
 * Created by LiQiong on 2016/12/4 18:28
 */

public class DLDownloadTool {
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
//        return name;
    }
}
