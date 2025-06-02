package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ABteamScores extends AppCompatActivity {
    private TextView scoreTeamA, scoreTeamB;
    private int scoreA = 0, scoreB = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        scoreTeamA = findViewById(R.id.score_team_a);
        scoreTeamB = findViewById(R.id.score_team_b);
        Button btnA1 = findViewById(R.id.btn_a1);
        Button btnA2 = findViewById(R.id.btn_a2);
        Button btnA3 = findViewById(R.id.btn_a3);
        Button btnB1 = findViewById(R.id.btn_b1);
        Button btnB2 = findViewById(R.id.btn_b2);
        Button btnB3 = findViewById(R.id.btn_b3);
        Button btnReset = findViewById(R.id.btn_reset);

        // TeamA 按钮点击事件
        btnA1.setOnClickListener(v -> updateScore(1, true));
        btnA2.setOnClickListener(v -> updateScore(2, true));
        btnA3.setOnClickListener(v -> updateScore(3, true));

        // TeamB 按钮点击事件
        btnB1.setOnClickListener(v -> updateScore(1, false));
        btnB2.setOnClickListener(v -> updateScore(2, false));
        btnB3.setOnClickListener(v -> updateScore(3, false));

        // 重置按钮
        btnReset.setOnClickListener(v -> resetScores());
    }

    // 更新分数
    private void updateScore(int points, boolean isTeamA) {
        if (isTeamA) {
            scoreA += points;
            scoreTeamA.setText(String.valueOf(scoreA));
        } else {
            scoreB += points;
            scoreTeamB.setText(String.valueOf(scoreB));
        }
    }

    // 重置分数
    private void resetScores() {
        scoreA = 0;
        scoreB = 0;
        scoreTeamA.setText("0");
        scoreTeamB.setText("0");
    }
}