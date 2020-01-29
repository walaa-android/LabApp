package com.example.labmedixapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.ReminderActivity;
import com.example.labmedixapplication.activities.UserVisitActivity;
import com.example.labmedixapplication.fragment.HomeFragment;
import com.example.labmedixapplication.model.Lists;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewProfileAdapter extends   RecyclerView.Adapter<RecycleViewProfileAdapter.ViewHolder>   implements View.OnClickListener {

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);

    }

    public interface OnNextClickListener {
        void onNextClick(Lists lists);

    }

    private OnNextClickListener onNextClickListener;
    private OnItemClickListener onItemClickListener;
    private List<Lists> contentProfile;
    private Activity activity;
    View view;

    public RecycleViewProfileAdapter(List<Lists> contentProfile, Activity activity) {
        this.contentProfile = contentProfile;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ViewHolder listViewHolder = (ViewHolder) holder;
        listViewHolder.text_view_name.setText(contentProfile.get(position).getType());
        listViewHolder.mage_view_service.setImageResource(contentProfile.get(position).getImage());
        listViewHolder.image_view_next.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return contentProfile.size();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public void setOnNextClickListener(OnNextClickListener onNextClickListener) {
        this.onNextClickListener = onNextClickListener;
    }

    @Override
    public void onClick(View v) {
        //   Toast.makeText(view.getContext(), "position = " + getItemId(0), Toast.LENGTH_SHORT).show();

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


        TextView text_view_name;
        ImageView mage_view_service;
        ImageView image_view_next;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_name = itemView.findViewById(R.id.text_view_name);
            mage_view_service = itemView.findViewById(R.id.image_view_service);
            image_view_next = itemView.findViewById(R.id.image_view_next);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();


                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(v, position);
                        switch (position){
                            case 0:
                                Intent intent1 = new Intent(view.getContext() , UserVisitActivity.class);
                                view.getContext().startActivity(intent1);

                                break;
                            case 1:
                                Intent intent = new Intent(view.getContext() , ReminderActivity.class);
                                view.getContext().startActivity(intent);

                                break;

                            case 2:

                                final Dialog dialog = new Dialog(view.getContext());
                                dialog.setContentView(R.layout.langouage_dialog);
                                TextView text_view_ok = dialog.findViewById(R.id.text_view_ok);
                                text_view_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();

                                    }
                                });
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
                }
            });
        }
    }}