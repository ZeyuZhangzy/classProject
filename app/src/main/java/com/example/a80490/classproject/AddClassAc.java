package com.example.a80490.classproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 李洵枫 on 2018/6/3 0003.
 */

public class AddClassAc extends AppCompatActivity {

    CourseDataBase courseDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass_layout);


        //去除标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }

        //返回按钮事件
        Button backButton=(Button)findViewById(R.id.back_addclass_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                finish();
            }
        });



        //添加课程的按钮事件
        Button addButton=(Button)findViewById(R.id.add_addclass_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent();

                EditText editText1=(EditText)findViewById(R.id.name_addclass_edit);
                EditText editText2=(EditText)findViewById(R.id.place_addclass_edit);
                EditText editText3=(EditText)findViewById(R.id.whichday_addclass_edit);
                EditText editText4=(EditText)findViewById(R.id.begin_addclass_edit);
                EditText editText5=(EditText)findViewById(R.id.lasts_addclass_edit);

                String name=editText1.getText().toString();
                String place=editText2.getText().toString();
                String whichday=editText3.getText().toString();
                String begin=editText4.getText().toString();
                String lasts=editText5.getText().toString();

                try{//tyr用于判断是否正确输入了数字
                    int test1=Integer.parseInt(begin);
                    int test2=Integer.parseInt(lasts);
                    boolean test3=false;

                    //下列if用于判断是否规范输入
                    if(test1>12) test3=false;
                    if(test2>4) test3=false;
                    if(whichday.equals("monday")) test3=true;
                    if(whichday.equals("tuesday")) test3=true;
                    if(whichday.equals("wednesday")) test3=true;
                    if(whichday.equals("thursday")) test3=true;
                    if(whichday.equals("friday")) test3=true;
                    if(whichday.equals("saturday")) test3=true;
                    if(whichday.equals("sunday")) test3=true;

                    if(test3==true) {
                        intent.putExtra("name", name);
                        intent.putExtra("place", place);
                        intent.putExtra("whichday", whichday);
                        intent.putExtra("begin", begin);
                        intent.putExtra("lasts", lasts);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else{
                        Toast.makeText(AddClassAc.this,"请按小写正确输入周几，或检查你的课程是否符合逻辑",
                                Toast.LENGTH_LONG).show();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(AddClassAc.this,"请在第几节课与有几节课里输入数字",
                            Toast.LENGTH_LONG).show();
                }



            }
        });

    }
}
