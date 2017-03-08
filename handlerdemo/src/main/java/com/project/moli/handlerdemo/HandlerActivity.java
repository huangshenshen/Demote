package com.project.moli.handlerdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
    Handler handler;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = (TextView) this.findViewById(R.id.tv);
        final ProgressDialog dialog = new ProgressDialog(HandlerActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setIcon(R.mipmap.ic_launcher);// 设置提示的title的图标，默认是没有的
        dialog.setTitle("提示");
        dialog.setMax(100);
        dialog.setMessage("正在上传...");
        dialog.show();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dialog.setProgress(msg.arg1);
                if (msg.arg1==100){
                    dialog.dismiss();
                }
                //textView.setText(String.valueOf(msg.arg1));

            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                int num = -1;
                for (int i=0;i<101;i++) {

                        Message message = Message.obtain();
                        message.arg1 = i;
                        handler.sendMessage(message);
                        SystemClock.sleep(1000);
                }
            }
        }).start();



    }
}
