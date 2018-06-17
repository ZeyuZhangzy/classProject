package com.example.a80490.classproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import static android.R.attr.name;
import static com.example.a80490.classproject.R.id.beginning;
import static com.example.a80490.classproject.R.id.firday_1;
import static com.example.a80490.classproject.R.id.place;
import static com.example.a80490.classproject.R.id.whichday_addclass_edit;
import static java.lang.Integer.parseInt;

/**
 * Created by 李洵枫 on 2018/6/3 0003.
 */

public class ClassShowAc extends AppCompatActivity {

    CourseDataBase courseDB = new CourseDataBase();//建立一个Course的集合


    private int colors[] = {
            Color.rgb(0xee, 0xff, 0xff),
            Color.rgb(0xf0, 0x96, 0x09),
            Color.rgb(0x8c, 0xbf, 0x26),
            Color.rgb(0x00, 0xab, 0xa9),
            Color.rgb(0x99, 0x6c, 0x33),
            Color.rgb(0x3b, 0x92, 0xbc),
            Color.rgb(0xd5, 0x4d, 0x34),
            Color.rgb(0xcc, 0xcc, 0xcc)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.classshow_layout);

        //去除标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        //按钮事件
        Button addTitle = (Button) findViewById(R.id.add_classshow_button);//加入课程按钮
        addTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ClassShowAc.this, AddClassAc.class);
                startActivityForResult(intent, 1);
            }
        });

        Button backTitle = (Button) findViewById(R.id.back_classshow_button);//返回上一层按钮
        backTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        Button deletButton = (Button) findViewById(R.id.delet_classshow_button);//删除课程按钮
        deletButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ClassShowAc.this, DeletClassAc.class);
                startActivityForResult(intent, 2);
            }
        });

        Button saveButton=(Button)findViewById(R.id.save_classshow_button);//保存按钮
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                AlertDialog.Builder dialog=new AlertDialog.
                        Builder(ClassShowAc.this);
                dialog.setTitle("提示");
                dialog.setMessage("你是否要保存");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveFile(courseDB.toString(),"YourCourse");
                        Toast.makeText(ClassShowAc.this, "保存成功", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ClassShowAc.this, "保存失败", Toast.LENGTH_LONG).show();

                    }
                });
                dialog.show();


            }
        });


        //对文件写入进行处理
        Intent intent=getIntent();
        String fileNmame=intent.getStringExtra("data");
        String AllCourseData=loadFile(fileNmame);
        StringTokenizer STK1=new StringTokenizer(AllCourseData,"*");
        int size=STK1.countTokens();
        String[] EveryCourse=new String[size];
        int canshu=0;
        while (STK1.hasMoreTokens()){
            EveryCourse[canshu]=STK1.nextToken();
            canshu++;
        }

        for(int i=0;i<size;i++){
            StringTokenizer STK2=new StringTokenizer(EveryCourse[i],"_");
            Course course=new Course(STK2.nextToken(),STK2.nextToken(),STK2.nextToken(),
                    STK2.nextToken(),STK2.nextToken());
            courseDB.addCourse(course);
        }


        Iterator<Course> Ite = courseDB.getCourseIterator();

        while (Ite.hasNext()) {
            setClass(Ite.next());
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            setContentView(R.layout.classshow_layout);
            Button addTitle = (Button) findViewById(R.id.add_classshow_button);//加入课程按钮
            Button backTitle = (Button) findViewById(R.id.back_classshow_button);//返回上一层按钮
            Button deletButton = (Button) findViewById(R.id.delet_classshow_button);//删除课程按钮
            Button saveButton=(Button)findViewById(R.id.save_classshow_button);//保存按钮

            addTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ClassShowAc.this, AddClassAc.class);
                    startActivityForResult(intent, 1);
                }
            });

            backTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    finish();
                }
            });

            deletButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ClassShowAc.this, DeletClassAc.class);
                    startActivityForResult(intent, 2);
                }
            });
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    AlertDialog.Builder dialog=new AlertDialog.
                            Builder(ClassShowAc.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("你是否要保存");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveFile(courseDB.toString(),"YourCourse");
                            Toast.makeText(ClassShowAc.this, "保存成功", Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ClassShowAc.this, "保存失败", Toast.LENGTH_LONG).show();

                        }
                    });
                    dialog.show();
                }
            });

            switch (requestCode) {
                case 1:
                    if ((resultCode == RESULT_OK)) {
                        String name = data.getStringExtra("name");
                        String place = data.getStringExtra("place");
                        String whichday = data.getStringExtra("whichday");
                        String begin = data.getStringExtra("begin");
                        String lasts = data.getStringExtra("lasts");

                        courseDB.addCourse(name, place, whichday, begin, lasts);

                        Iterator<Course> Ite = courseDB.getCourseIterator();
                        while (Ite.hasNext()) {
                            setClass(Ite.next());
                        }


                    }
                    break;
                case 2:

                    if (resultCode == RESULT_OK) {
                        String whichday = data.getStringExtra("whichday");
                        String begin = data.getStringExtra("begin");

                        courseDB.removetCourse(whichday, begin);

                        Iterator<Course> Ite = courseDB.getCourseIterator();
                        while (Ite.hasNext()) {
                            setClass(Ite.next());
                        }
                    }
                    break;
            }
        }
    }


    void setClass(String name, String place, String whichday, String begin,
                  String lasts) {

        //设置颜色参数
        Random random = new Random();
        int rand = random.nextInt(6) + 1;
        int color = rand;

        //id资源参数
        FindID findID = new FindID();
        findID.initiID();
        int[][] IDs = findID.getRID();

        int lie = 0;//判断是星期几,兼表列
        if (whichday.equals("monday")) lie = 0;
        if (whichday.equals("tuesday")) lie = 1;
        if (whichday.equals("wednesday")) lie = 2;
        if (whichday.equals("thursday")) lie = 3;
        if (whichday.equals("friday")) lie = 4;
        if (whichday.equals("saturday")) lie = 5;
        if (whichday.equals("sunday")) lie = 6;

        int hang = parseInt(begin) - 1;

        int canshu = 0;//控制while
        int i = hang;

        //设置名称
        TextView textView1 = (TextView) findViewById(IDs[hang][lie]);
        TextView textView2 = (TextView) findViewById(IDs[hang + 1][lie]);
        textView1.setText(name);
        textView2.setText(place);

        //设置颜色
        while (canshu < parseInt(lasts)) {
            TextView textView = (TextView) findViewById(IDs[i][lie]);
            textView.setBackgroundColor(colors[color]);
            i++;
            canshu++;
        }

    }


    void setClass(Course course) {

        //设置颜色参数
        Random random = new Random();
        int rand = random.nextInt(6) + 1;
        int color = rand;

        //id资源参数
        FindID findID = new FindID();
        findID.initiID();
        int[][] IDs = findID.getRID();

        int lie = 0;//判断是星期几,兼表列
        if (course.getWhichday().equals("monday")) lie = 0;
        if (course.getWhichday().equals("tuesday")) lie = 1;
        if (course.getWhichday().equals("wednesday")) lie = 2;
        if (course.getWhichday().equals("thursday")) lie = 3;
        if (course.getWhichday().equals("friday")) lie = 4;
        if (course.getWhichday().equals("saturday")) lie = 5;
        if (course.getWhichday().equals("sunday")) lie = 6;

        int hang = parseInt(course.getBegin()) - 1;

        int canshu = 0;//控制while
        int i = hang;

        //设置名称
        TextView textView1 = (TextView) findViewById(IDs[hang][lie]);
        TextView textView2 = (TextView) findViewById(IDs[hang + 1][lie]);
        textView1.setText(course.getName());
        textView2.setText(course.getPlace());

        //设置颜色
        while (canshu < parseInt(course.getLasts())) {
            TextView textView = (TextView) findViewById(IDs[i][lie]);
            textView.setBackgroundColor(colors[color]);
            i++;
            canshu++;
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
            Toast.makeText(ClassShowAc.this, "文件输入错误", Toast.LENGTH_LONG).show();
        }
    }


    public String loadFile(String fileName){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();

        try{
            in=openFileInput(fileName);
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        }
        catch (IOException e){
            Toast.makeText(ClassShowAc.this, "文件读取错误", Toast.LENGTH_LONG).show();
        }
        finally {
            if(reader!=null){
                try {
                    reader.close();
                }
                catch (IOException e){
                    Toast.makeText(ClassShowAc.this, "文件读取错误", Toast.LENGTH_LONG).show();
                }
            }
        }
        return content.toString();
    }
}
