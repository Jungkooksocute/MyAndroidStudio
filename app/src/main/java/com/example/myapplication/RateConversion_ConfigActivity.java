package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateConversion_ConfigActivity extends AppCompatActivity {
    private final String TAG="Config";

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

    }
}