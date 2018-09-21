package com.patelheggere.aryanacademy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patelheggere.aryanacademy.R;

import java.util.List;

public class CurrentAffairsAdapter extends RecyclerView.Adapter<CurrentAffairsAdapter.CurrentaffairsViewHolder>{

    List<String> dateList;
    private List<String> messageList;
    private Context mContext;

    public CurrentAffairsAdapter(Context mContext, List<String> dateList, List<String> messageList) {
        this.dateList = dateList;
        this.messageList = messageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CurrentaffairsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.current_affairs_item, viewGroup, false);
        return new CurrentaffairsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentaffairsViewHolder currentaffairsViewHolder, int i) {
        String date = dateList.get(i);
        if(date!=null)
        {
            currentaffairsViewHolder.textViewDate.setText(date);
        }
        String details = messageList.get(i);
        if(details!=null)
        {
            currentaffairsViewHolder.textViewDetails.setText(details);
        }
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class CurrentaffairsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDate;
        TextView textViewDetails;
        public CurrentaffairsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.tv_date);
            textViewDetails = itemView.findViewById(R.id.tv_details);
        }
    }
}
