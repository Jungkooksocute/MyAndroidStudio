package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NetActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "NetActivity";
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_net);
        handler=new Handler(Looper.myLooper()){
            public void handlerMessage(Message msg){
                if(msg.what==6){String s =(String)msg.obj;
                    Log.i(TAG, "handlerMessage: str="+s);}
            }
        };
        Log.i(TAG, "onCreate: mainThread:executing");



        Thread thread = new Thread();
        thread.start();


    }


    @Override
    public void run() {
        Log.i(TAG, "onCreate: sonThread:executing");
        Message msg = handler.obtainMessage(6, "imjuliesl");
        handler.sendMessage(msg);
    }
}