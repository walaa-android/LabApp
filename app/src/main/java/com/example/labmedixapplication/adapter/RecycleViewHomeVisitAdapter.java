package com.example.labmedixapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.Lists;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewHomeVisitAdapter extends   RecyclerView.Adapter<RecycleViewHomeVisitAdapter.ViewHolder>{

   private List<Lists> data;
    private Activity activity;
    View view ;

    public RecycleViewHomeVisitAdapter(List<Lists> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_lab_visit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder listViewHolder = (ViewHolder) holder;
        listViewHolder. lab_name.setText(data.get(position).getType());
        listViewHolder.time_visit.setText(data.get(position).getNo());


    }

    @Override
    public int getItemCount() {
         return data.size();

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView lab_name;
        TextView time_visit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lab_name= itemView.findViewById(R.id.lab_name);
            time_visit = itemView.findViewById(R.id.time_visit);

        }
    }
        }