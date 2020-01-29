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

public class RecycleViewTimeAdapter extends   RecyclerView.Adapter<RecycleViewTimeAdapter.ViewHolder>{

   private List<Lists> data;
    private Activity activity;
    View view ;

    public RecycleViewTimeAdapter(List<Lists> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_time_visit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder listViewHolder = (ViewHolder) holder;
        listViewHolder. text_view_time.setText(data.get(position).getType());
        listViewHolder.textViewDay.setText(data.get(position).getNo());
        listViewHolder.imageView.setImageResource(data.get(position).getImage());
        listViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent( ListPanelActivity.class);
              //  startActivity(intent);
            }
        });

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


        TextView text_view_time;
        TextView textViewDay;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_time= itemView.findViewById(R.id.text_view_time);
            textViewDay = itemView.findViewById(R.id.textViewDay);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
        }