package com.chazuo.college.enterprise.download.ex;

import android.os.Environment;

import com.chazuo.czlib.module.impl.CZKernel;

/**
 * Created by LiQiong on 2016/12/25 15:03
 */

public class PDFDownloadAdapter implements DownloadAdapter {

    public static DownloadAdapter builder(){
        return new PDFDownloadAdapter();
    }

    @Override
    public String defaultLocalUrl(String url) {
        String rootLocalPDFUrl = CZKernel.getInstance().getContext()
                .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + "/pdf")
                .getPath() + "/";
        return rootLocalPDFUrl
                + url.substring(url.lastIndexOf("/") + 1, url.length())
                + ".cz";
    }
}
