package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateConversion_ConfigActivity extends AppCompatActivity {
    private final String TAG="Config";

    private TextView dollar;
    private TextView euro;
    private TextView krw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_conversion_config);

        Intent intent = getIntent();
        float dollarRate = intent.getFloatExtra("dollar_r", 1.0f);
        float euroRate = intent.getFloatExtra("euro_r", 2.0f);
        float krwRate = intent.getFloatExtra("krw_r", 3.0f);

        Log.i(TAG, "onCreate: dollar:"+dollarRate);
        Log.i(TAG, "onCreate: euro:"+euroRate);
        Log.i(TAG, "onCreate: krw:"+krwRate);

//只能获取点击按钮之前的数据
    }

    public void save(View b){
        //获取点击按钮之后的数据
        Log.i(TAG, "save: ");
        String ds = dollar.getText().toString();
        String es = euro.getText().toString();
        String ks = krw.getText().toString();


        Intent intent = getIntent();
        Log.i(TAG, "save: dollar:"+ds);
        Log.i(TAG, "save: euro:"+es);
        Log.i(TAG, "save: krw:"+ks);


        Bundle bundle = new Bundle();
        bundle.putFloat("save_dollar",Float.parseFloat(ds));
        bundle.putFloat("save_euro",Float.parseFloat(es));
        bundle.putFloat("save_krw",Float.parseFloat(ks));
        intent.putExtras(bundle);


        SharedPreferences sh = getSharedPreferences("rate_conversion", AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sh.edit();
        edit.putFloat("dollar",Float.parseFloat(ds));
        edit.putFloat("euro",Float.parseFloat(es));
        edit.putFloat("krw",Float.parseFloat(ks));

        edit.apply();


        setResult(7,intent);
        finish();
    }
}