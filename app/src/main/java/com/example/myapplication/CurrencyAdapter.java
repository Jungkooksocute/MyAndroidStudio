package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private final List<Currency> currencies;

    public CurrencyAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_simple2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        holder.currencyName.setText(currency.getName());
        holder.currencyRate.setText(currency.getRate());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView currencyName;
        public TextView currencyRate;

        public ViewHolder(View view) {
            super(view);
            currencyName = view.findViewById(R.id.currencyName);
            currencyRate = view.findViewById(R.id.currencyRate);
        }
    }
}