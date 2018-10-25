package com.patelheggere.aryanacademy.view.gallery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.adapter.SlidingImage_Adapter;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.helper.AppUtils;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.model.CurrentAffairsModel;
import com.patelheggere.aryanacademy.model.GalleryModel;
import com.patelheggere.aryanacademy.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.GALLERY;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;

public class GalleryActivity extends BaseActivity {
    private static final String TAG = "GalleryActivity";

    private ActionBar mActionBar;
    private ViewPager mPager;
    private DatabaseReference databaseReference;
    private List<GalleryModel> galleryModelList;
    private ProgressBar mProgressBar;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected int getContentView() {
        return R.layout.activity_gallery;
    }

    @Override
    protected void initView() {
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setTitle(getString(R.string.gallery));
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        mPager = findViewById(R.id.image_pager);
        mProgressBar = findViewById(R.id.progress_bar);

    }

    @Override
    protected void initData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        getImageDate();
    }

    private void getImageDate() {
        mProgressBar.setVisibility(View.VISIBLE);
        String lang = SharedPrefsHelper.getInstance().get(LANGUAGE, "kn");
        databaseReference  = firebaseDatabase.getReference();
        databaseReference = databaseReference.child(GALLERY).child(lang);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                galleryModelList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    GalleryModel ob = new GalleryModel();
                    ob = snapshot.getValue(GalleryModel.class);
                    galleryModelList.add(ob);
                }
                mPager.setAdapter(new SlidingImage_Adapter(context,galleryModelList));
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressBar.setVisibility(View.GONE);
                AppUtils.showSnackBar(activity,getString(R.string.some_thing_wrong));
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
