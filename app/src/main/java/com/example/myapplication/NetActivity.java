package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "NetActivity";
    private Handler handler;
    TextView t;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_net);
        t=findViewById(R.id.textq);
        handler=new Handler(Looper.myLooper()){
            public void handlerMessage(Message msg){
                if(msg.what==6){String s =(String)msg.obj;
                    Log.i(TAG, "handlerMessage: html="+s);
                    t.setText(s);
                }
            }
        };



    }

    public void oneClick(View v){
        Log.i(TAG, "onCreate: mainThread:executing");
        Thread thread = new Thread();
        thread.start();
    }

    @Override
    public void run() {
        URL url=null;
        String s;
        try {
            url = new URL("https://www.boc.cn/sourcedb/whpj/index.html");
            HttpURLConnection h=(HttpURLConnection)url.openConnection();
            InputStream in = h.getInputStream();
            s = inputS2Str(in);
            Log.i(TAG, "onCreate: sonThread:html"+s);



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Message msg = handler.obtainMessage(6, s);
        handler.sendMessage(msg);


    }

    public String inputS2Str(InputStream i) throws IOException {
     final int size=1024;
     final char[] chars = new char[size];
     final StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(i, "utf-8");
        while(true){
            int read = inputStreamReader.read(chars, 0, size);
            if(read<0)break;
            stringBuilder.append(chars,0,read);
        }

        return stringBuilder.toString();

    }
}