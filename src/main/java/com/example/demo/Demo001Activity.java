package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.chazuo.czlib.module.impl.CZKernel;

/**
 * Created by LiQiong on 2016/10/27.
 */

public class Demo001Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        CZKernel.getInstance().onStart(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Demo001Activity.this, DemoActivity.class));
            }
        });
    }
}
