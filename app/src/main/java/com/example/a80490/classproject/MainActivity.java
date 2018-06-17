package com.example.a80490.classproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Context;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
/**
 * Created by 李洵枫 on 2018/6/3 0003.
 */

public class MainActivity extends AppCompatActivity {

    CourseDataBase courseDB;
    private static int control=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiCourseDB();//数据初始化
        intiClassFile();//文件初始化


        //去除标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
        //对按钮的一些事件
        Button button2 = (Button) findViewById(R.id.Egrade_main_button);//计算GPA
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        //载入预置课程
        Button mainOtherButton=(Button) findViewById(R.id.Eother_main_button);
        mainOtherButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String data="MyCourse";
                Intent intent=new Intent(MainActivity.this,ClassShowAc.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        //新建课程
        Button mainNewButton=(Button)findViewById(R.id.Enew_main_button);
        mainNewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String data="NewCourse";
                Intent intent=new Intent(MainActivity.this,ClassShowAc.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        //载入课程
        Button mainYouButton=(Button) findViewById(R.id.Eyours_main_button);
        mainYouButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String data="YourCourse";
                Intent intent=new Intent(MainActivity.this,ClassShowAc.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }



    public void intiClassFile(){
        if (control==0){
            saveFile("","YourCourse");
            saveFile("","NewCourse");
            saveFile(courseDB.toString(),"MyCourse");
            control++;
        }

    }


    public void saveFile(String data, String fileName) {
        FileOutputStream out = null;
        PrintWriter writer = null;
        try {
            out = openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new PrintWriter(new OutputStreamWriter(out));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "文件输入错误", Toast.LENGTH_LONG).show();
        }
    }

    public void intiCourseDB(){
        courseDB=new CourseDataBase();
        courseDB.addCourse("大学物理实验", "二基楼A317", "monday", "1", "4");
        courseDB.addCourse("数字逻辑", "二基楼A317", "monday", "5", "3");
        courseDB.addCourse("微积分", "二基楼A317", "monday", "8", "2");
        courseDB.addCourse("面向对象程序设计", "二基楼A317", "tuesday", "1", "4");
        courseDB.addCourse("体育", "二基楼A317", "tuesday", "5", "2");
        courseDB.addCourse("大学英语", "二基楼A317", "wednesday", "1", "2");
        courseDB.addCourse("概率统计", "二基楼A317", "wednesday", "3", "2");
        courseDB.addCourse("微积分", "二基楼A317", "wednesday", "5", "3");
        courseDB.addCourse("中华文化", "二基楼A317", "wednesday", "10", "2");
        courseDB.addCourse("中国近代史纲要", "二基楼A317", "thursday", "1", "2");
        courseDB.addCourse("军事理论", "二基楼A317", "thursday", "3", "2");
        courseDB.addCourse("大学物理", "二基楼A317", "thursday", "8", "2");
        courseDB.addCourse("概率统计", "二基楼A317", "friday", "1", "2");
        courseDB.addCourse("大学口语", "二基楼A317", "friday", "5", "1");
    }
}

