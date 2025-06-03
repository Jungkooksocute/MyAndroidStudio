package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class UpdatedDaily extends AppCompatActivity {

    private ListView listView;
    private ExchangeRateAdapter adapter;
    private List<ExchangeRate> exchangeRates = new ArrayList<>();
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        adapter = new ExchangeRateAdapter(this, exchangeRates);
        listView.setAdapter(adapter);

        // 检查数据库是否有今日数据
        if (dbHelper.hasTodayData()) {
            // 从数据库加载数据
            exchangeRates.clear();
            exchangeRates.addAll(dbHelper.getTodayRates());
            adapter.notifyDataSetChanged();
        } else {
            // 从网络获取数据
            fetchDataFromNetwork();
        }

        // 设置列表点击事件
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ExchangeRate selectedRate = exchangeRates.get(position);
            Intent intent = new Intent(UpdatedDaily.this, CalculateActivity.class);
            intent.putExtra("currency", selectedRate.getCurrencyName());
            intent.putExtra("rate", selectedRate.getRate());
            startActivity(intent);
        });
    }

    private void fetchDataFromNetwork() {
        new Thread(() -> {
            List<ExchangeRate> rates = ApiService.fetchRates(UpdatedDaily.this);
            runOnUiThread(() -> {
                if (!rates.isEmpty()) {
                    exchangeRates.clear();
                    exchangeRates.addAll(rates);
                    adapter.notifyDataSetChanged();
                    // 保存到数据库
                    dbHelper.saveRates(rates);
                }
            });
        }).start();
    }
}
