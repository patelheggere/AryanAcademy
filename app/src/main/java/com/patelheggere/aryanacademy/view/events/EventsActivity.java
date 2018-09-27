package com.patelheggere.aryanacademy.view.events;


import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.adapter.EventDetailsAdapter;
import com.patelheggere.aryanacademy.adapter.SlidingImage_Adapter;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.helper.AppUtils;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.model.EventDetailsModel;
import com.patelheggere.aryanacademy.model.GalleryModel;

import java.util.ArrayList;
import java.util.List;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.EVENTS;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;

public class EventsActivity extends BaseActivity {

    private static final String TAG = "EventsActivity";

    private RecyclerView recyclerViewJobs;
    private ProgressBar mProgressBar;
    private List<EventDetailsModel> eventsModelList;
    private DatabaseReference databaseReference;
    private ActionBar mActionBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_events;
    }

    @Override
    protected void initView() {
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setTitle(getString(R.string.events));
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerViewJobs = findViewById(R.id.rv_events);
        mProgressBar = findViewById(R.id.progress_bar);

    }

    @Override
    protected void initData() {
        getEvents();
    }

    private void getEvents() {
        mProgressBar.setVisibility(View.VISIBLE);
        String lang = SharedPrefsHelper.getInstance().get(LANGUAGE, "en");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference = databaseReference.child(EVENTS).child(lang);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventsModelList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    EventDetailsModel ob = new EventDetailsModel();
                    ob = snapshot.getValue(EventDetailsModel.class);
                    eventsModelList.add(ob);
                }
                recyclerViewJobs.setAdapter(new EventDetailsAdapter(context,eventsModelList));
                recyclerViewJobs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressBar.setVisibility(View.GONE);
                AppUtils.showSnackBar(activity, getString(R.string.some_thing_wrong));
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
