package com.patelheggere.aryanacademy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class CurrentAffairsAdapter extends RecyclerView.Adapter<CurrentAffairsAdapter.CurrentaffairsViewHolder>{

    @NonNull
    @Override
    public CurrentaffairsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentaffairsViewHolder currentaffairsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CurrentaffairsViewHolder extends RecyclerView.ViewHolder {

        public CurrentaffairsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
