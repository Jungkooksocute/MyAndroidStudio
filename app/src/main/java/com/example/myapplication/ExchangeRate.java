package com.example.myapplication;

public class ExchangeRate {
    private String currencyName;
    private double rate;

    public ExchangeRate(String currencyName, double rate) {
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getRate() {
        return rate;
    }
}