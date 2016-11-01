package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.chazuo.czlib.module.impl.CZKernel;

/**
 * Created by LiQiong on 2016/10/27.
 */

public class Demo002Activity extends Activity {
    private SeekBar seekBar0, seekBar1, seekBar2,
            seekBar3, seekBar4, seekBar5, seekBar6, seekBar7,
            seekBar8, seekBar9, seekBar10, seekBar11, seekBar12,
            seekBar13,seekBar14, seekBar15,seekBar16,seekBar17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

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

    }
}
