package com.example.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.chazuo.czlib.module.impl.CZController;
import com.chazuo.czlib.module.impl.CZKernel;
import com.example.demo.download.DLTask;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar0, seekBar1, seekBar2, seekBar3, seekBar4, seekBar5, seekBar6, seekBar7, seekBar8, seekBar9, seekBar10, seekBar11, seekBar12,
            seekBar13,
            seekBar14,
            seekBar15,
            seekBar16,
            seekBar17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitApp.getInstance().initApplication(this);


        CZKernel.getInstance().onStart(this);
        seekBar0 = (SeekBar) findViewById(R.id.seekBar0);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar) findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar) findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar) findViewById(R.id.seekBar7);
        seekBar8 = (SeekBar) findViewById(R.id.seekBar8);
        seekBar9 = (SeekBar) findViewById(R.id.seekBar9);
        seekBar10 = (SeekBar) findViewById(R.id.seekBar10);
        seekBar11 = (SeekBar) findViewById(R.id.seekBar11);
        seekBar12 = (SeekBar) findViewById(R.id.seekBar12);
        seekBar13 = (SeekBar) findViewById(R.id.seekBar13);
        seekBar14 = (SeekBar) findViewById(R.id.seekBar14);
        seekBar15 = (SeekBar) findViewById(R.id.seekBar15);
        seekBar16 = (SeekBar) findViewById(R.id.seekBar16);
        seekBar17 = (SeekBar) findViewById(R.id.seekBar17);


        Button btn = (Button) findViewById(R.id.btn);


        List<String> s=new LinkedList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DClient client=new DClient();
//
//                String path = "http://192.168.1.87:8080/demo-debug.apk";
//                DTask task0=new DTask.Builder()
//                        .netUrl(path)
//                        .name("0.apk")
//                        .build();
//                client
//                        .newCall(task0)
//                        .enqueue();
//
//                repeat(task0,seekBar0);
//                DTask task1=new DTask.Builder()
//                        .netUrl(path)
//                        .name("1.apk")
//                        .build();
//                client
//                        .newCall(task1)
//                        .enqueue();
//                repeat(task1,seekBar1);
//                DTask task2=new DTask.Builder()
//                        .netUrl(path)
//                        .name("2.apk")
//                        .build();
//                client
//                        .newCall(task2)
//                        .enqueue();
//                repeat(task2,seekBar2);
//                DTask task3=new DTask.Builder()
//                        .netUrl(path)
//                        .name("3.apk")
//                        .build();
//                client
//                        .newCall(task3)
//                        .enqueue();
//                repeat(task3,seekBar3);
//                DTask task4=new DTask.Builder()
//                        .netUrl(path)
//                        .name("4.apk")
//                        .build();
//                client
//                        .newCall(task4)
//                        .enqueue();
//                repeat(task4,seekBar4);
//                DTask task5=new DTask.Builder()
//                        .netUrl(path)
//                        .name("5.apk")
//                        .build();
//                client
//                        .newCall(task5)
//                        .enqueue();
//                repeat(task5,seekBar5);
//                DTask task6=new DTask.Builder()
//                        .netUrl(path)
//                        .name("6.apk")
//                        .build();
//                client
//                        .newCall(task6)
//                        .enqueue();
//                repeat(task6,seekBar6);
//                DTask task7=new DTask.Builder()
//                        .netUrl(path)
//                        .name("7.apk")
//                        .build();
//                client
//                        .newCall(task7)
//                        .enqueue();
//                repeat(task7,seekBar7);
//                DTask task8=new DTask.Builder()
//                        .netUrl(path)
//                        .name("8.apk")
//                        .build();
//                client
//                        .newCall(task8)
//                        .enqueue();
//                repeat(task8,seekBar8);
//                DTask task9=new DTask.Builder()
//                        .netUrl(path)
//                        .name("9.apk")
//                        .build();
//                client
//                        .newCall(task9)
//                        .enqueue();
//                repeat(task9,seekBar9);
//                DTask task10=new DTask.Builder()
//                        .netUrl(path)
//                        .name("10.apk")
//                        .build();
//                client
//                        .newCall(task10)
//                        .enqueue();
//                repeat(task10,seekBar10);
//                DTask task11=new DTask.Builder()
//                        .netUrl(path)
//                        .name("11.apk")
//                        .build();
//                client
//                        .newCall(task11)
//                        .enqueue();
//                repeat(task11,seekBar11);
//                DTask task12=new DTask.Builder()
//                        .netUrl(path)
//                        .name("12.apk")
//                        .build();
//                client
//                        .newCall(task12)
//                        .enqueue();
//                repeat(task12,seekBar12);
//                DTask task13=new DTask.Builder()
//                        .netUrl(path)
//                        .name("13.apk")
//                        .build();
//                client
//                        .newCall(task13)
//                        .enqueue();
//                repeat(task13,seekBar13);
//                DTask task14=new DTask.Builder()
//                        .netUrl(path)
//                        .name("14.apk")
//                        .build();
//                client
//                        .newCall(task14)
//                        .enqueue();
//                repeat(task14,seekBar14);
//                DTask task15=new DTask.Builder()
//                        .netUrl(path)
//                        .name("15.apk")
//                        .build();
//                client
//                        .newCall(task15)
//                        .enqueue();
//                repeat(task15,seekBar15);
//                DTask task16=new DTask.Builder()
//                        .netUrl(path)
//                        .name("16.apk")
//                        .build();
//                client
//                        .newCall(task16)
//                        .enqueue();
//                repeat(task16,seekBar16);
//                DTask task17=new DTask.Builder()
//                        .netUrl(path)
//                        .name("17.apk")
//                        .build();
//                client
//                        .newCall(task17)
//                        .enqueue();
//                repeat(task17,seekBar17);
            }
        });
    }

    private void repeat(final DLTask task, final SeekBar seekBar) {
        CZController.uiHelp.postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBar.setMax((int) task.getBuilder().getLength());
                seekBar.setProgress((int) task.getBuilder().getCurrentLength());
                repeat(task, seekBar);
            }
        }, 1000);
    }


//    private void repeat() {
//        CZController.uiHelp.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (DDataManage.task.getLength() != 0) {
//                    seekBar2.setMax(DDataManage.task.getLength());
//                    if (DDataManage.task.getCurrentLength() != 0) {
//                        seekBar2.setProgress(DDataManage.task.getCurrentLength());
//                    }
//                }
//                CZController.uiHelp.toast(DDataManage.task.getLength() + "-----" + DDataManage.task.getCurrentLength());
//                if (DDataManage.task.getLength() != DDataManage.task.getCurrentLength())
//                    repeat();
//                else if(DDataManage.task.getLength()!=0&&DDataManage.task.getLength()==DDataManage.task.getCurrentLength())
//                    CZController.uiHelp.toast("File download success!!!");
//            }
//        }, 1000);
//    }
}
