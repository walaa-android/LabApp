package com.example.labmedixapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.ListCondition;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewFirestoreTestAdapter extends FirestoreRecyclerAdapter<ListCondition , RecycleViewFirestoreTestAdapter.DataHolder> {

    private  OnItemClickListener listener;

    public RecycleViewFirestoreTestAdapter(@NonNull FirestoreRecyclerOptions<ListCondition> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DataHolder holder, int position, @NonNull ListCondition model) {
        holder.text_view_type.setText(model.getType());
        holder.text_view_des.setText(model.getDescribtion());
        holder.text_view_des.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_condition, parent, false);
        return new DataHolder(v);
    }

    public class DataHolder extends RecyclerView.ViewHolder {


        TextView text_view_type;
        ImageView image_view;
        TextView text_view_des;
        ImageView imageViewNext;

        public DataHolder(@NonNull final View itemView) {
            super(itemView);
            text_view_type= itemView.findViewById(R.id.textView);
            image_view = itemView.findViewById(R.id.imageView);
            text_view_des=itemView.findViewById(R.id.text_view_des);
            imageViewNext=itemView.findViewById(R.id.imageViewNext);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();


                  boolean swap = true;

                    if (swap)
                    {
                        image_view.setVisibility(View.GONE);
                        imageViewNext.setVisibility(View.VISIBLE);
                        text_view_des.setVisibility(View.VISIBLE);
                        swap=false;
                    }
                    else
                    {
                        swap=true;
                        image_view.setVisibility(View.VISIBLE);
                        imageViewNext.setVisibility(View.GONE);
                        text_view_des.setVisibility(View.GONE);

                    }

                    if(position != RecyclerView.NO_POSITION  && listener != null){
                     listener.onItemClick(getSnapshots().getSnapshot(position) , position);
                    }
                }
            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public  void setOnItemClickListener (OnItemClickListener listener){
     this.listener = listener;
    }



}