package com.patelheggere.aryanacademy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.model.EventDetailsModel;
import com.patelheggere.aryanacademy.model.EventDetailsModel;

import java.util.List;

public class EventDetailsAdapter extends RecyclerView.Adapter<EventDetailsAdapter.CurrentaffairsViewHolder>{

    List<EventDetailsModel> jobList;
    private Context mContext;

    public EventDetailsAdapter(Context mContext, List<EventDetailsModel> jobList) {
        this.jobList = jobList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CurrentaffairsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.events_item, viewGroup, false);
        return new CurrentaffairsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentaffairsViewHolder currentaffairsViewHolder, int i) {
       EventDetailsModel eventDeatilsModel = jobList.get(i);
       if(eventDeatilsModel.getTitle()!=null)
           currentaffairsViewHolder.title.setText(eventDeatilsModel.getTitle());

        if(eventDeatilsModel.getDesc()!=null)
            currentaffairsViewHolder.desc.setText(eventDeatilsModel.getDesc());

        if(eventDeatilsModel.getDate()!=null)
            currentaffairsViewHolder.date.setText(eventDeatilsModel.getDate());

        if(eventDeatilsModel.getPlace()!=null)
            currentaffairsViewHolder.place.setText(eventDeatilsModel.getPlace());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class CurrentaffairsViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, date, place;
        public CurrentaffairsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_event_title);
            desc = itemView.findViewById(R.id.tv_event_desc);

            date = itemView.findViewById(R.id.tv_date_value);
            place = itemView.findViewById(R.id.tv_place_value);

        }
    }
}
