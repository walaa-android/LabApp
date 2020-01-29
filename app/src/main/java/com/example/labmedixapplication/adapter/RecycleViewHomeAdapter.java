package com.example.labmedixapplication.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.ListPanelActivity;
import com.example.labmedixapplication.model.Lists;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewHomeAdapter  extends   RecyclerView.Adapter<RecycleViewHomeAdapter.ViewHolder>{

   private List<Lists> data;
    private Activity activity;
    View view ;

    public RecycleViewHomeAdapter(List<Lists> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder listViewHolder = (ViewHolder) holder;
        listViewHolder. text_view_type.setText(data.get(position).getType());
        listViewHolder.text_view_no.setText(data.get(position).getNo());
        listViewHolder.image_view.setImageResource(data.get(position).getImage());
       // listViewHolder.image_view_next.setImageResource(data.get(position).getImage());
        listViewHolder.image_view_next.setOnClickListener(new View.OnClickListener() {
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


        TextView text_view_type;
        TextView text_view_no;
        ImageView image_view;
        ImageView image_view_next;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_type= itemView.findViewById(R.id.text_view_type);
            text_view_no = itemView.findViewById(R.id.text_view_no);
            image_view = itemView.findViewById(R.id.image_view);
            image_view_next= itemView.findViewById(R.id.image_view_next);

        }
    }
        }