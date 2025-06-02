package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateConversion extends AppCompatActivity {
    private static final String TAG = "RateConversion";
    private TextView inputRmb;
    private TextView tvResult;
    float dollarRate = 34.5f;
    float euroRate = 666.66f;
    float krwRate = 345f;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_conversion);




    }

    public void click(View vw){
        inputRmb = findViewById(R.id.inputMoney);
        String string = inputRmb.getText().toString();

        try {
            float v = Float.parseFloat(string);
            float re = 0.0f;

            if (vw.getId() == R.id.dollar_rate) {
                re = v * dollarRate;
            } else if (vw.getId() == R.id.euro_rate) {
                re = v * euroRate;
            } else if (vw.getId() == R.id.krm_rate) {
                re = v * krwRate;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this,"请输入正确数据：",Toast.LENGTH_SHORT).show();
        }


    }//
    public void clickOpen(View n){
        Intent intent = new Intent(this, RateConversion_ConfigActivity.class);
        intent.putExtra("dollar_r",dollarRate);
        intent.putExtra("euro_r",euroRate);
        intent.putExtra("krw_r",krwRate);

        Log.i(TAG, "clickOpen: dollar:"+dollarRate);
        Log.i(TAG, "clickOpen: euro:"+euroRate);
        Log.i(TAG, "clickOpen: krw:"+krwRate);

        startActivity(intent);
    }
}