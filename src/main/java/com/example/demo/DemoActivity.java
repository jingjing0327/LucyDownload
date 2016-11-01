package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.chazuo.czlib.module.impl.CZController;
import com.chazuo.czlib.module.impl.CZKernel;
import com.example.demo.db.CRUDImpl;
import com.example.demo.db.ICRUD;
import com.example.demo.download.DBTaskPoint;
import com.example.demo.download.DLQueue;
import com.example.demo.download.DLTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiQiong on 2016/10/27.
 */

public class DemoActivity extends Activity {
    private SeekBar seekBar0, seekBar1, seekBar2,
            seekBar3, seekBar4, seekBar5, seekBar6, seekBar7,
            seekBar8, seekBar9, seekBar10, seekBar11, seekBar12,
            seekBar13,
            seekBar14,
            seekBar15,
            seekBar16,
            seekBar17;
    DLQueue queue = null;
    String path = "http://192.168.1.87:8080/demo-debug.apk";
    List<DLTask> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitApp.getInstance().initApplication(this);
        queue = DLQueue.getInstance();

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

        repeat();

        for (int i = 0; i < 18; i++) {
            DLTask task = new DLTask.Builder()
                    .netUrl(path)
                    .name("aa" + i + ".apk")
                    .build();
            tasks.add(task);
        }


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (DLTask task : tasks) {
                    queue.enqueue(task);
                }
            }
        });

        findViewById(R.id.button_pause_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(0);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(0);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(1);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(1);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(2);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(2);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(3);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(3);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(4);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(4);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(5);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(5);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(6);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(6);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(7);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(7);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(8);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(8);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(9);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(9);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(10);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(10);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(11);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(11);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(12);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(12);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(13);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(13);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(14);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(14);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(15);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(15);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(16);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(16);
                queue.enqueue(task);
            }
        });
        findViewById(R.id.button_pause_17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(17);
                queue.pause(task);
            }
        });
        findViewById(R.id.button_enqueue_17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLTask task = tasks.get(17);
                queue.enqueue(task);
            }
        });


        findViewById(R.id.button222).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.unfinishedTask();
            }
        });

        findViewById(R.id.button333).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICRUD xx = new CRUDImpl();
                String xxxx = "";
                CZController.uiHelp.toast(xxxx = xx.findAll(DBTaskPoint.class).toString());
                Log.e("liqiong", xxxx);

            }
        });
    }

    private void repeat() {
        CZController.uiHelp.postDelayed(new Runnable() {
            @Override
            public void run() {
//                seekBar.setMax((int) task.getBuilder().getLength());
//                seekBar.setProgress((int) task.getBuilder().getCurrentLength());
//                repeat(task, seekBar);

                DLTask task = DLQueue.getInstance().getCurrentTask();
                if (task != null) {
                    Log.e("liqiong", task.getBuilder().getName() + "---" + task.getBuilder().getLength() + "---" + task.getBuilder().getCurrentLength());
                    if (task.getBuilder().getName().contains("aa0.apk")) {
                        seekBar0.setMax((int) task.getBuilder().getLength());
                        seekBar0.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa1.apk")) {
                        seekBar1.setMax((int) task.getBuilder().getLength());
                        seekBar1.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa2.apk")) {
                        seekBar2.setMax((int) task.getBuilder().getLength());
                        seekBar2.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa3.apk")) {
                        seekBar3.setMax((int) task.getBuilder().getLength());
                        seekBar3.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa4.apk")) {
                        seekBar4.setMax((int) task.getBuilder().getLength());
                        seekBar4.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa5.apk")) {
                        seekBar5.setMax((int) task.getBuilder().getLength());
                        seekBar5.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa6.apk")) {
                        seekBar6.setMax((int) task.getBuilder().getLength());
                        seekBar6.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa7.apk")) {
                        seekBar7.setMax((int) task.getBuilder().getLength());
                        seekBar7.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa8.apk")) {
                        seekBar8.setMax((int) task.getBuilder().getLength());
                        seekBar8.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa9.apk")) {
                        seekBar9.setMax((int) task.getBuilder().getLength());
                        seekBar9.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa10.apk")) {
                        seekBar10.setMax((int) task.getBuilder().getLength());
                        seekBar10.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa11.apk")) {
                        seekBar11.setMax((int) task.getBuilder().getLength());
                        seekBar11.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa12.apk")) {
                        seekBar12.setMax((int) task.getBuilder().getLength());
                        seekBar12.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa13.apk")) {
                        seekBar13.setMax((int) task.getBuilder().getLength());
                        seekBar13.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa14.apk")) {
                        seekBar14.setMax((int) task.getBuilder().getLength());
                        seekBar14.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa15.apk")) {
                        seekBar15.setMax((int) task.getBuilder().getLength());
                        seekBar15.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa16.apk")) {
                        seekBar16.setMax((int) task.getBuilder().getLength());
                        seekBar16.setProgress((int) task.getBuilder().getCurrentLength());
                    } else if (task.getBuilder().getName().contains("aa17.apk")) {
                        seekBar17.setMax((int) task.getBuilder().getLength());
                        seekBar17.setProgress((int) task.getBuilder().getCurrentLength());
                    }
                }
                repeat();
            }
        }, 1000);
    }
}
