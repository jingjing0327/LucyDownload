package com.example.demo;

import android.content.Context;

import com.chazuo.czlib.module.annotation.BuildService;
import com.chazuo.czlib.module.impl.CZController;
import com.chazuo.czlib.module.impl.CZKernel;

/**
 * Created by LiQiong on 2016/10/20.
 *  注意：
 *      getInstance() 方法名不可以修改，除非@BuildService所有的变量为static
 */

public class InitApp {

    @BuildService
    public MyConstantsService myConstantsService;
    @BuildService
    public MyConstantsService2 myConstantsService2;

    private static InitApp initApp = null;

    private InitApp() {
    }


    public static InitApp getInstance() {
        synchronized (InitApp.class) {
            if (initApp == null)
                initApp = new InitApp();
            return initApp;
        }
    }

    public void initApplication(Context context) {
        CZKernel.getInstance()
                .initialize(context)
                .addBuildService(InitApp.class)
                .build();
    }
}
