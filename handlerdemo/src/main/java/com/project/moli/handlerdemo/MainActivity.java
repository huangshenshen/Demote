package com.project.moli.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) this.findViewById(R.id.tv);
        init();
    }
    public void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                myHandler = new MyHandler(Looper.myLooper());
                Message message = new Message();
                message.obj = "子线程发送的消息Hi~Hi";
                myHandler.sendMessage(message);
                Looper.loop();
            }
        }).start();
    }

    class MyHandler extends Handler{
        private MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("子线程收到:" + msg.obj);
            //2  收到消息后可再发消息到主线程
            myHandler=new MyHandler(getMainLooper());
            Message message = new Message();
            message.obj = "O(∩_∩)O";
            myHandler.sendMessage(message);

        }
    }
}
