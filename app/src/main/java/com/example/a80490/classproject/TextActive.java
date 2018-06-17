package com.example.a80490.classproject;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import static android.R.attr.id;
import static android.R.attr.shrinkColumns;
import static java.lang.Integer.parseInt;

/**
 * Created by 李洵枫 on 2018/6/6 0006.
 */

public class TextActive extends AppCompatActivity {




    private int colors[] = {
            Color.rgb(0xee,0xff,0xff),
            Color.rgb(0xf0,0x96,0x09),
            Color.rgb(0x8c,0xbf,0x26),
            Color.rgb(0x00,0xab,0xa9),
            Color.rgb(0x99,0x6c,0x33),
            Color.rgb(0x3b,0x92,0xbc),
            Color.rgb(0xd5,0x4d,0x34),
            Color.rgb(0xcc,0xcc,0xcc)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_layout);
        //去除标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }



       /* setClass("大学物理实验","二基楼A317","1","4","monday");
        setClass("数字逻辑","二基楼A317","5","3","monday");
        setClass("微积分","二基楼A317","8","2","monday");
        setClass("面向对象程序设计","二基楼A317","1","4","tuesday");
        setClass("体育","二基楼A317","5","2","tuesday");
        setClass("大学英语","二基楼A317","1","2","wednesday");
        setClass("概率统计","二基楼A317","3","2","wednesday");
        setClass("微积分","二基楼A317","5","3","wednesday");
        setClass("中华文化","二基楼A317","10","2","wednesday");
        setClass("中国近代史纲要","二基楼A317","1","2","thursday");
        setClass("军事理论","二基楼A317","3","2","thursday");
        setClass("大学物理","二基楼A317","8","2","thursday");
        setClass("概率统计","二基楼A317","1","2","friday");
        setClass("大学口语","二基楼A317","5","1","friday");*/

    }

    void setClass(String name, String place, String begin,
                  String lasts, String whichday){

        //设置颜色参数
        Random random=new Random();
        int rand=random.nextInt(6)+1;
        int color=rand;

        //id资源参数
        FindID findID=new FindID();
        findID.initiID();
        int[][] IDs=findID.getRID();

        int lie=0;//判断是星期几,兼表列
        if(whichday.equals("monday")) lie=0;
        if(whichday.equals("tuesday")) lie=1;
        if(whichday.equals("wednesday")) lie=2;
        if(whichday.equals("thursday")) lie=3;
        if(whichday.equals("friday")) lie=4;
        if(whichday.equals("saturday")) lie=5;
        if(whichday.equals("sunday")) lie=6;

        int hang=parseInt(begin)-1;

        int canshu=0;//控制while
        int i=hang;
        while (canshu<parseInt(lasts)){
            TextView textView=(TextView)findViewById(IDs[i][lie]);
            textView.setBackgroundColor(colors[color]);
            i++;
            canshu++;
        }




        TextView textView1=(TextView)findViewById(IDs[hang][lie]);
        TextView textView2=(TextView)findViewById(IDs[hang+1][lie]);

        textView1.setText(name);
        textView2.setText(place);

    }
}
