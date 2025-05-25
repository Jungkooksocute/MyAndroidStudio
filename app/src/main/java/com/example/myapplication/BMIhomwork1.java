package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BMIhomwork1 extends AppCompatActivity {

    private EditText weightEditText, heightEditText;
    private TextView resultTextView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bmi_homework1);

        weightEditText = findViewById(R.id.weight_edittext);
        heightEditText = findViewById(R.id.height_edittext);
        Button calculateButton = findViewById(R.id.calculate_button);
        resultTextView = findViewById(R.id.result_textview);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            double height = Double.parseDouble(heightEditText.getText().toString());

            if (weight <= 0 || height <= 0) {
                resultTextView.setText("请输入有效的体重和身高数值。");
                return;
            }

            double bmi = weight / (height * height);
            String bmiResult = String.format("BMI: %.2f", bmi);
            String advice = getHealthAdvice(bmi);

            resultTextView.setText(bmiResult + "\n" + advice);
        } catch (NumberFormatException e) {
            resultTextView.setText("请输入有效的数字。");
        }
    }

    private String getHealthAdvice(double bmi) {
        if (bmi < 18.5) {
            return "体重过轻，建议增加营养摄入。";
        } else if (bmi < 24) {
            return "体重正常，继续保持。";
        } else if (bmi < 28) {
            return "过重，建议适当运动。";
        } else {
            return "肥胖，建议控制饮食并加强锻炼。";
        }
    }


}