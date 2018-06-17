package com.example.a80490.classproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GradeShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_show2);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
        TableLayout table = (TableLayout) findViewById(R.id.table);//tableLayout继承自viewGroup
        for (int i = 0; i < LoginActivity.names.size(); i++) {
            TableRow row = new TableRow(this);//新建一个表格行
            TextView name = new TextView(this);//课程名
            name.setTextSize(12);
            name.setBackgroundResource(R.drawable.shape1);
            name.setText(LoginActivity.names.get(i));
            TextView credit = new TextView(this);//学分
            credit.setTextSize(12);
            credit.setBackgroundResource(R.drawable.shape1);
            credit.setText(LoginActivity.credits.get(i).toString());
            TextView type = new TextView(this);//属性
            type.setTextSize(12);
            type.setBackgroundResource(R.drawable.shape1);
            type.setText(LoginActivity.types.get(i));
            TextView score = new TextView(this);//成绩
            score.setTextSize(12);
            score.setBackgroundResource(R.drawable.shape1);
            score.setText(LoginActivity.scores.get(i).toString());
            //将4个TextView添加进一行里，注意顺序
            row.addView(name);
            row.addView(credit);
            row.addView(type);
            row.addView(score);
            //将一行添加进table里
            table.addView(row);
        }
    }

    private void go(){
        Intent intent=new Intent();
        intent.setClass(GradeShowActivity.this,GPA_Activity.class);
        startActivity(intent);
    }//跳转


    public void query_all(View v){
        GPA_Activity.type=0;
        go();
    }//所有
    public void query_major(View v){
        GPA_Activity.type=1;
        go();
    }//必修
    public void query_option(View v){
        GPA_Activity.type=2;
        go();
    }//选修

    public void Back(View v){
        finish();
    }//返回按钮
}
