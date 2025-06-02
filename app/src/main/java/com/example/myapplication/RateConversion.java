package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RateConversion extends AppCompatActivity {
    private static final String TAG = "RateConversion";
    private TextView inputRmb;
    private TextView tvResult;
    float dollarRate = 34.5f;
    float euroRate = 666.66f;
    float krwRate = 345f;

    private float dollarV;
    private float euroV;
    private float krwV;


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

        //startActivity(intent);
        startActivityForResult(intent,13);
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
         if (requestCode == 13 && resultCode == 7) {
             // 从data获取带回的数据
             Bundle ret = data.getExtras();
             dollarV = ret.getFloat("save_dollar");
             euroV = ret.getFloat("save_euro");
             krwV = ret.getFloat("save_krw");
             Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
             Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
             Log.i(TAG, "onActivityResult: wonRate=" + krwRate);
         }
         super.onActivityResult(requestCode, resultCode, data);
     }

}