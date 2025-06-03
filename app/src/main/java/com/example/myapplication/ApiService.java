package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final String API_URL = "https://api.example.com/rates"; // 替换为实际API地址

    public static List<ExchangeRate> fetchRates(UpdatedDaily context) {
        List<ExchangeRate> rates = new ArrayList<>();
        try {
            // 模拟API响应数据（实际项目中替换为真实网络请求）
            String jsonResponse = "{\"rates\":[" +
                    "{\"currency\":\"阿联酋迪拉姆\",\"rate\":84.22}," +
                    "{\"currency\":\"澳大利亚元\",\"rate\":504.4}," +
                    "{\"currency\":\"巴西里亚尔\",\"rate\":182.89}," +
                    "{\"currency\":\"加拿大元\",\"rate\":518.99}," +
                    "{\"currency\":\"瑞士法郎\",\"rate\":682.74}," +
                    "{\"currency\":\"丹麦克朗\",\"rate\":106.55}," +
                    "{\"currency\":\"欧元\",\"rate\":794.03}," +
                    "{\"currency\":\"英镑\",\"rate\":892.97}," +
                    "{\"currency\":\"港币\",\"rate\":86.25}," +
                    "{\"currency\":\"印尼卢比\",\"rate\":0.0467}," +
                    "{\"currency\":\"印度卢比\",\"rate\":9.8138}," +
                    "{\"currency\":\"日元\",\"rate\":6.1029}," +
                    "{\"currency\":\"韩国元\",\"rate\":0.6031}" +
                    "]}";

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray ratesArray = jsonObject.getJSONArray("rates");

            for (int i = 0; i < ratesArray.length(); i++) {
                JSONObject rateObj = ratesArray.getJSONObject(i);
                rates.add(new ExchangeRate(
                        rateObj.getString("currency"),
                        rateObj.getDouble("rate")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rates;
    }
}