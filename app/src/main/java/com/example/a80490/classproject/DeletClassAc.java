package com.example.a80490.classproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by 李洵枫 on 2018/6/3 0003.
 */
public class DeletClassAc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletclass_layout);

        //去除标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }
        //返回按钮事件
        Button backButton=(Button)findViewById(R.id.back_deletclass_button);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                finish();
            }
        });

        //删除课程按钮事件
        Button deletButton=(Button)findViewById(R.id.delet_deletclass_button);
        deletButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent();

                RadioGroup radioGroupwhichday=
                        (RadioGroup)findViewById(R.id.groupwhichday_deletclass);
                RadioGroup radioGroupbegin=
                        (RadioGroup)findViewById(R.id.groupbegin_deletclass);

                int IdWh=radioGroupwhichday.getCheckedRadioButtonId();
                int IdBe=radioGroupbegin.getCheckedRadioButtonId();
                if(IdWh==-1||IdBe==-1){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(DeletClassAc.this);
                    dialog.setTitle("Error");
                    dialog.setMessage("You do not choose the button");
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }

                else {
                    RadioButton radioWhichday = (RadioButton) findViewById(IdWh);
                    RadioButton radioBegin = (RadioButton) findViewById(IdBe);


                    String whichday = radioWhichday.getText().toString();
                    String begin = radioBegin.getText().toString();

                    intent.putExtra("whichday", whichday);
                    intent.putExtra("begin", begin);

                    setResult(RESULT_OK, intent);

                    finish();
                }
            }
        });

    }

}
