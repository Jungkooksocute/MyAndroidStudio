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
    private OnItemClickListener listener = null;

    public interface OnItemClickListener {
        void onItemClick(Currency currency);
    }

    public CurrencyAdapter(List<Currency> currencies, SimpleList simpleList) {
        this.currencies = currencies;
        this.listener = listener;
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
        holder.bind(currency, listener);
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

        public void bind(final Currency currency, final OnItemClickListener listener) {
            currencyName.setText(currency.getName());
            currencyRate.setText(currency.getRate());

            itemView.setOnClickListener(v -> listener.onItemClick(currency));
        }
    }
}