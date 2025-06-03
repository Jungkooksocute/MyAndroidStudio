package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class CalculateUpdatedDaily extends AppCompatActivity {


        private TextView currencyText;
        private EditText rmbInput;
        private Button calculateBtn;
        private TextView resultText;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calculate);

            currencyText = findViewById(R.id.currencyText);
            rmbInput = findViewById(R.id.rmbInput);
            calculateBtn = findViewById(R.id.calculateBtn);
            resultText = findViewById(R.id.resultText);

            // 获取传递的汇率数据
            String currency = getIntent().getStringExtra("currency");
            double rate = getIntent().getDoubleExtra("rate", 0);

            currencyText.setText(currency);

            calculateBtn.setOnClickListener(v -> calculateConversion(rate));
        }

        private void calculateConversion(double rate) {
            String inputStr = rmbInput.getText().toString();
            if (inputStr.isEmpty()) {
                Toast.makeText(this, "请输入人民币金额", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double rmb = Double.parseDouble(inputStr);
                double result = rmb * (100 / rate); // 根据示例数据格式转换

                resultText.setText(String.format(Locale.getDefault(),
                        "兑换结果: %.2f %s", result, currencyText.getText()));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "请输入有效数字", Toast.LENGTH_SHORT).show();
            }
        }
    }