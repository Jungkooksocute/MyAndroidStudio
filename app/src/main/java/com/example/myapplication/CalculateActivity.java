package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {
    private TextView currencyNameView;
    private EditText rmbInput;
    private Button calculateButton;
    private TextView resultView;

    private String currencyName;
    private double exchangeRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        currencyName = getIntent().getStringExtra("currency_name");
        String rateStr = getIntent().getStringExtra("exchange_rate");

        try {
            exchangeRate = Double.parseDouble(rateStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "无效的汇率值", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        currencyNameView = findViewById(R.id.currencyName);
        rmbInput = findViewById(R.id.rmbInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultView = findViewById(R.id.resultText);


        currencyNameView.setText(currencyName);

        calculateButton.setOnClickListener(v -> calculateConversion());
    }

    private void calculateConversion() {
        String rmbStr = rmbInput.getText().toString().trim();

        if (rmbStr.isEmpty()) {
            Toast.makeText(this, "请输入人民币金额", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double rmbAmount = Double.parseDouble(rmbStr);
            double result = (rmbAmount * 100) / exchangeRate;

            resultView.setText(String.format("%.2f %s", result, currencyName));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入有效的数字", Toast.LENGTH_SHORT).show();
        }
    }
}