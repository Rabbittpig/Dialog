package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 单击按钮将会调用该方法，弹出常规对话框
     * @param view
     */
    public void startNormalDialog(View view){
        new AlertDialog.Builder(this)
                .setTitle("删除记录确认")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("是否确认删除该条记录")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"删除记录成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消删除记录",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    /**
     * 启动一个带单选列表的对话框
     * @param view
     */
    public void startListDialog(View view){
        final String[] colors = {"Green","Blue","Red","Orange","Purple"};
        new AlertDialog.Builder(this)
                .setTitle("请选择一种颜色")
                .setCancelable(false)
//                .setMultiChoiceItems() 实现多选列表
                .setSingleChoiceItems(colors, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,colors[which],Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK",null)
                .show();
    }

    /**
     * 启动一个带自定义视图的对话框
     * @param view
     */
    public void startCustomDialog(View view){
        View v = View.inflate(this,R.layout.dialog_view,null);
        final EditText etUser = v.findViewById(R.id.et_name);
        final EditText etPassword = v.findViewById(R.id.et_password);
        new AlertDialog.Builder(this)
                .setTitle("自定义对话框")
                .setCancelable(false)
                .setView(v)
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    String username = etUser.getText().toString().trim();
                    String pwd = etPassword.getText().toString().trim();
                    Toast.makeText(MainActivity.this,"用户名："+username +"密码："+pwd,Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    /**
     *启动带进度条的对话框
     * @param view
     */
    public void startProgressDialog(View view){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("带进度条的对话框");
        progressDialog.setProgress(30);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =1;i<=20;i++){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDialog.setProgress(progressDialog.getProgress()+5);
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    /**
     * 启动带日期选择器的对话框
     * @param view
     */
    public void startDatePickerDialog(View view){
        Calendar calendar = Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Toast.makeText(MainActivity.this,"选中了："+year+"年"+(month+1)+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
            }
        },year,month,day).show();
    }
}