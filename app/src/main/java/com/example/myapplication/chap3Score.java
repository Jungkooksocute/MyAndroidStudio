package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class chap3Score extends AppCompatActivity {
    private TextView scoreTeamA;
    private int scoreA = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        scoreTeamA = findViewById(R.id.score_team_a);
        Button btnA1 = findViewById(R.id.btn_a1);
        Button btnA2 = findViewById(R.id.btn_a2);
        Button btnA3 = findViewById(R.id.btn_a3);
        Button btnReset = findViewById(R.id.btn_reset);

        // TeamA 按钮点击事件
        btnA1.setOnClickListener(v -> updateScore(1));
        btnA2.setOnClickListener(v -> updateScore(2));
        btnA3.setOnClickListener(v -> updateScore(3));

        // 重置按钮
        btnReset.setOnClickListener(v -> resetScore());
    }

    // 更新分数（仅TeamA）
    private void updateScore(int points) {
        scoreA += points;
        scoreTeamA.setText(String.valueOf(scoreA));
    }

    // 重置分数
    private void resetScore() {
        scoreA = 0;
        scoreTeamA.setText("0");
    }
}