package com.example.labmedixapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.Lists;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewFirestoreAdapter extends FirestoreRecyclerAdapter<Lists , RecycleViewFirestoreAdapter.DataHolder> {

    private  OnItemClickListener listener;

    public RecycleViewFirestoreAdapter(@NonNull FirestoreRecyclerOptions<Lists> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DataHolder holder, int position, @NonNull Lists model) {
        holder. text_view_type.setText(model.getType());
        holder.text_view_no.setText(model.getNo());
       // holder.image_view.setImageResource(model.getImage());
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new DataHolder(v);
    }

    public class DataHolder extends RecyclerView.ViewHolder {


        TextView text_view_type;
        TextView text_view_no;
        ImageView image_view;
        ImageView image_view_next;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            text_view_type= itemView.findViewById(R.id.text_view_type);
            text_view_no = itemView.findViewById(R.id.text_view_no);
            image_view = itemView.findViewById(R.id.image_view);
            image_view_next= itemView.findViewById(R.id.image_view_next);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION  && listener != null){
                     listener.onItemClick(getSnapshots().getSnapshot(position) , position);
                    }
                }
            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick (DocumentSnapshot documentSnapshot , int position);
    }

    public  void setOnItemClickListener (OnItemClickListener listener){
     this.listener = listener;
    }
}