package com.example.labmedixapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.labmedixapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserVisitAdapter   extends   RecyclerView.Adapter<UserVisitAdapter.ViewHolder> {
    @NonNull
    @Override
    public UserVisitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserVisitAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_view_day;
        TextView text_view_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_day = itemView.findViewById(R.id.text_view_date);
            text_view_time = itemView.findViewById(R.id.text_view_time);
        }
    }

}
