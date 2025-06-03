package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CurrencyAdapter adapter;
    private final List<Currency> currencyList = new ArrayList<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.currencyRecyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrencyAdapter(currencyList, this);
        recyclerView.setAdapter(adapter);

        loadExchangeRates();
    }

    private void loadExchangeRates() {
        progressBar.setVisibility(View.VISIBLE);

        executorService.execute(() -> {
            try {
                List<Currency> currencies = fetchExchangeRates();
                mainHandler.post(() -> {
                    currencyList.clear();
                    currencyList.addAll(currencies);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                });
            } catch (Exception e) {
                Log.e("ExchangeRate", "Error fetching data", e);
                mainHandler.post(() -> {
                    Toast.makeText(SimpleList.this,
                            "获取汇率失败: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                });
            }
        });
    }

    private List<Currency> fetchExchangeRates() throws IOException {
        String url = "https://www.huilvbiao.com/bank/spdb";
        List<Currency> currencies = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String html = response.body().string();
            Document doc = Jsoup.parse(html);

            // 查找包含汇率的表格
            Element table = doc.select("table.table.table-hover").first();
            if (table != null) {
                Elements rows = table.select("tr");

                for (int i = 1; i < rows.size(); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    if (columns.size() >= 5) {
                        String name = columns.get(0).text().trim();
                        String rate = columns.get(4).text().trim();

                        if (!name.isEmpty() && !rate.isEmpty()) {
                            currencies.add(new Currency(name, rate));
                        }
                    }
                }
            }
        }

        return currencies;
    }


    public void onItemClick(Currency currency) {
        // 跳转到计算页面
        Intent intent = new Intent(this, CalculateActivity.class);
        intent.putExtra("currency_name", currency.getName());
        intent.putExtra("exchange_rate", currency.getRate());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}