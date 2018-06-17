package com.example.a80490.classproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class LoginActivity extends AppCompatActivity {
    public static Vector<String> names = new Vector<String>();
    public static Vector<Double> scores = new Vector<Double>();
    public static Vector<Double> credits = new Vector<Double>();
    public static Vector<String> types = new Vector<String>();
    public static int All_Num=0;//课程数目
    public static double All_Credit=0;//学分总数
    public static int Major_Num=0;//必修课程数目
    public static double Major_Credit=0;//必修学分总数
    public static int Option_Num=0;//选修课程数目
    public static double Option_Credit=0;//选修学分总数
    private Handler handler;
    private EditText account;//学号输入框
    private EditText password;//密码输入框
    private ProgressDialog progressDialog;//进度框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();
        account= (EditText) findViewById(R.id.account);//绑定控件
        password=(EditText)findViewById(R.id.password);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == -2) {
                    // 登陆失败
                    Toast.makeText(LoginActivity.this, "登陆失败，请检查学号或密码", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();//关闭
                    return;
                }
                if (msg.what == -1) {
                    // 连接失败
                    Toast.makeText(LoginActivity.this, "连接失败，联网后重试", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();//关闭
                    return;
                }
                progressDialog.dismiss();//关闭
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,GradeShowActivity.class);
                startActivity(intent);
            }
        };//回调监听
    }

    public void query(View v) {
        showProcessDialog(LoginActivity.this,"请稍等");
        Account.ACCOUNT=account.getText().toString();//获取输入的学号的值
        Account.PASSWORD=password.getText().toString();//获取输入的密码的值
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clawer.run(names, scores, credits, types, handler);
                } catch (Exception e) {
                    System.err.println("GG");
                }
            }
        }).start();//网络异步处理
    }//查询按钮

    public static double getAllGrade() {
        int total = names.size();
        double allScore = 0;
        double allCredit = 0;
        double average = 0;
        for (int i = 0; i < total; i++) {
            allScore += scores.get(i) * credits.get(i);
            allCredit += credits.get(i);
        }
        All_Num=total;
        All_Credit=allCredit;
        average = allScore / allCredit;
        return average;
    }//返回所有课程加权平均分

    public static double getAllGPA() {
        int total = names.size();
        double allGPA = 0;
        double allCredit = 0;
        double average = 0;
        for (int i = 0; i < total; i++) {
            allGPA += GPA(scores.get(i)) * credits.get(i);
            allCredit += credits.get(i);
        }
        average = allGPA / allCredit;
        return average;
    }//返回所有课程绩点

    public static double getRequiredGrade() {
        int total = names.size();
        double requiredScore = 0;
        double requiredCredit = 0;
        double average = 0;
        for (int i = 0; i < total; i++) {
            if (types.get(i).equals("必修")) {
                requiredScore += scores.get(i) * credits.get(i);
                requiredCredit += credits.get(i);
                Major_Num++;
            }
        }
        Major_Credit=requiredCredit;
        average = requiredScore / requiredCredit;
        return average;
    }//返回必修课程加权平均分

    public static double getRequiredGPA() {
        int total = names.size();
        double requiredGPA = 0;
        double requiredCredit = 0;
        double average = 0;
        for (int i = 0; i < total; i++) {
            if (types.get(i).equals("必修")) {
                requiredGPA += GPA(scores.get(i)) * credits.get(i);
                requiredCredit += credits.get(i);
            }
        }
        average = requiredGPA / requiredCredit;
        return average;
    }//返回必修课程绩点

    public static double getOptionGrade() {
        int total = names.size();
        double optionScore = 0;
        double optionCredit = 0;
        double average = 0;
        for (int i = 0; i < total; i++) {
            if (types.get(i).equals("选修")||types.get(i).equals("任选")) {
                optionScore += scores.get(i) * credits.get(i);
                optionCredit += credits.get(i);
                Option_Num++;
            }
        }
        Option_Credit=optionCredit;
        average = optionScore / optionCredit;
        return average;
    }//返回选修课程加权平均分


    public static double getOptionGPA() {
        int total = names.size();
        double optionScore = 0;
        double optionCredit = 0;
        double average = 0;
        for (int i = 0; i < total; i++) {
            if (types.get(i).equals("选修")||types.get(i).equals("任选")) {
                optionScore += GPA(scores.get(i)) * credits.get(i);
                optionCredit += credits.get(i);
            }
        }
        average = optionScore / optionCredit;
        return average;
    }//返回必修课程绩点

    private static double GPA(double score){
        if(score>=90)
            return 4.0;
        else if(score>=85)
            return 3.7;
        else if(score>=80)
            return 3.3;
        else if(score>=76)
            return 3.0;
        else if(score>=73)
            return 2.7;
        else if(score>=70)
            return 2.3;
        else if(score>=66)
            return 2.0;
        else if(score>=63)
            return 1.7;
        else if(score>=61)
            return 1.3;
        else if(score==60)
            return 1.0;
        else
            return 0;
    }//返回分数对应的绩点


    private  void showProcessDialog(Context mContext, String text)
    {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(true);//可取消
        progressDialog.show();//弹出
        progressDialog.setMessage(text);//信息
    }


}
