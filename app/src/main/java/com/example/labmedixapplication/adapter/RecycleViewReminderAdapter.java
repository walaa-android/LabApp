package com.example.labmedixapplication.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.ReminderActivity;
import com.example.labmedixapplication.activities.UserVisitActivity;
import com.example.labmedixapplication.model.Lists;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewReminderAdapter extends   RecyclerView.Adapter<RecycleViewReminderAdapter.ViewHolder>   implements View.OnClickListener {

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);

    }

    public interface OnNextClickListener {
        void onNextClick(Lists lists);

    }

    private OnNextClickListener onNextClickListener;
    private OnItemClickListener onItemClickListener;
    private List<Lists> content;
    private Activity activity;
    View view;

    public RecycleViewReminderAdapter(List<Lists> content, Activity activity) {
        this.content = content;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reminder_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ViewHolder listViewHolder = (ViewHolder) holder;
        listViewHolder.text_view_time.setText(content.get(position).getType());
        listViewHolder.text_view_date.setText(content.get(position).getType());


    }

    @Override
    public int getItemCount() {
        return content.size();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                Toast.makeText(view.getContext(), " select 0", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(view.getContext(), " select 1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.langouage_dialog);
                TextView text_view_ok = dialog.findViewById(R.id.text_view_ok);
                TextView text_view_cancle = dialog.findViewById(R.id.text_view_cancle);
                text_view_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.setCancelable(false);
                break;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_view_date;
        TextView text_view_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_date = itemView.findViewById(R.id.text_view_date);
            text_view_time = itemView.findViewById(R.id.text_view_time);
        }
    }

}