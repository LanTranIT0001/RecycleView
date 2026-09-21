package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<CountryViewHolder>{
    private List countrylist;
    LayoutInflater mInflater;
    public MyAdapter(Context context, List list){
        mInflater = LayoutInflater.from(context);
        this.countrylist = list;
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.country_layout, parent, false );
        CountryViewHolder holder = new CountryViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        String country = (String) countrylist.get(position);
        holder.tid.setText(String.valueOf(position + 1) + "");
        holder.tcountry.setText(country);
    }

    @Override
    public int getItemCount() { return countrylist.size();}


}