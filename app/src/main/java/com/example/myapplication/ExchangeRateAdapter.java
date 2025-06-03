package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ExchangeRateAdapter extends ArrayAdapter<ExchangeRate> {
    public ExchangeRateAdapter(Context context, List<ExchangeRate> rates) {
        super(context, 0, rates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExchangeRate rate = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_currency, parent, false);
        }

        TextView currencyName = convertView.findViewById(R.id.currencyName);
        TextView currencyRate = convertView.findViewById(R.id.currencyRate);

        currencyName.setText(rate.getCurrencyName());
        currencyRate.setText(String.format(Locale.getDefault(), "%.4f", rate.getRate()));

        return convertView;
    }
}