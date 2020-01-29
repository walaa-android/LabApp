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

    public class  RecycleViewCheckPanelAdapter extends FirestoreRecyclerAdapter<Lists,  RecycleViewCheckPanelAdapter.DataHolder> {


        private  RecycleViewCheckPanelAdapter.OnItemClickListener listener;

        public  RecycleViewCheckPanelAdapter(@NonNull FirestoreRecyclerOptions<Lists> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull  RecycleViewCheckPanelAdapter.DataHolder holder, int position, @NonNull Lists model) {
            holder. text_view_type.setText(model.getType());

        }

        @NonNull
        @Override
        public RecycleViewCheckPanelAdapter.DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkup_layout, parent, false);
            return new  RecycleViewCheckPanelAdapter.DataHolder(v);
        }

        public class DataHolder extends RecyclerView.ViewHolder {


            TextView text_view_type;
            ImageView image_view_next;

            public DataHolder(@NonNull View itemView) {
                super(itemView);
                text_view_type= itemView.findViewById(R.id.text_view_type);

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

        public  void setOnItemClickListener (RecycleViewCheckPanelAdapter.OnItemClickListener listener){
            this.listener = listener;
        }
    }

