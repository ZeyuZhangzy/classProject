package com.example.a80490.classproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 唐品 on 2018/6/3 0003.
 */

public class GPA_Activity extends Activity {
    public static int type=0;//请求类型，0为全部，1为必修，2为选修
    private TextView credit;//总学分
    private TextView sum;//数量
    private TextView average;//平均分
    private TextView GPA;//绩点
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.info);
        initData();//初始化
        setTV();//设置text
    }

    private void initData(){
        credit= (TextView) findViewById(R.id.credit);
        sum= (TextView) findViewById(R.id.sum);
        average= (TextView) findViewById(R.id.average);
        GPA= (TextView) findViewById(R.id.GPA);
    }

    private void setTV(){
        if(type==0)//总学分和课程数量应该放在平均分后，不然是两个0
        {
            average.setText("加权平均分："+LoginActivity.getAllGrade());
            GPA.setText("平均绩点："+LoginActivity.getAllGPA());
            credit.setText("全部课程总学分："+LoginActivity.All_Credit);
            sum.setText("全部课程数："+LoginActivity.All_Num);
        }
        else if(type==1)
        {
            average.setText("加权平均分："+LoginActivity.getRequiredGrade());
            GPA.setText("平均绩点："+LoginActivity.getRequiredGPA());
            credit.setText("必修课程总学分："+LoginActivity.Major_Credit);
            sum.setText("必修课程数："+LoginActivity.Major_Num);
            LoginActivity.Major_Credit=0;
            LoginActivity.Major_Num=0;
        }
        else
        {
            average.setText("加权平均分："+LoginActivity.getOptionGrade());
            GPA.setText("平均绩点："+LoginActivity.getOptionGPA());
            credit.setText("选修课程总学分："+LoginActivity.Option_Credit);
            sum.setText("选修课程数："+LoginActivity.Option_Num);
            LoginActivity.Option_Credit=0;
            LoginActivity.Option_Num=0;
        }
    }

    public void Back(View v){
        finish();
    }//返回按钮
}
