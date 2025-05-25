package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class chap3tempratureExchange extends AppCompatActivity {
    private EditText editTextCelsius;
    private TextView textViewResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCelsius = findViewById(R.id.editTextCelsius);
        Button buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCelsiusToFahrenheit();
            }
        });
    }

    private void convertCelsiusToFahrenheit() {
        String celsiusInput = editTextCelsius.getText().toString().trim();
        if (celsiusInput.isEmpty()) {
            Toast.makeText(this, "请输入有效的温度值", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double celsius = Double.parseDouble(celsiusInput);
            double fahrenheit = celsius * 9 / 5 + 32;
            String result = String.format("结果为：%.2f ℉", fahrenheit);
            textViewResult.setText(result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "输入的不是有效数字", Toast.LENGTH_SHORT).show();
        }
    }
}