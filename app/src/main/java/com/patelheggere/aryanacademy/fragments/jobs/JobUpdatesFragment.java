package com.patelheggere.aryanacademy.fragments.jobs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.aryanacademy.AryanAcademyApplication;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.adapter.CurrentAffairsAdapter;
import com.patelheggere.aryanacademy.adapter.JobUpdateAdapter;
import com.patelheggere.aryanacademy.base.BaseFragment;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.model.CurrentAffairsModel;
import com.patelheggere.aryanacademy.model.JobUpdatesModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.JOB;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;

public class JobUpdatesFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mView;
    private RecyclerView recyclerViewJobs;
    private ProgressBar mProgressBar;
    private List<JobUpdatesModel> jobUpdatesModelList;
    private DatabaseReference databaseReference;

    private OnFragmentInteractionListener mListener;
    private JobUpdateAdapter adapter;
    private FirebaseDatabase firebaseDatabase;

    public JobUpdatesFragment() {
        // Required empty public constructor
    }

    public static JobUpdatesFragment newInstance(String param1, String param2) {
        JobUpdatesFragment fragment = new JobUpdatesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_job_updates, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        initViews();
        getJobDetails();
        return mView;
    }

    private void initViews()
    {
        jobUpdatesModelList = new ArrayList<>();
        mProgressBar = mView.findViewById(R.id.progress_bar);
        recyclerViewJobs = mView.findViewById(R.id.rv_jobs);
    }

    private void getJobDetails() {
        mProgressBar.setVisibility(View.VISIBLE);
        String lang = SharedPrefsHelper.getInstance().get(LANGUAGE, "ka");

        databaseReference = AryanAcademyApplication.getFireBaseRef();
        databaseReference = databaseReference.child(lang).child(JOB);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    jobUpdatesModelList.add(snapshot.getValue(JobUpdatesModel.class));
                }
                Collections.reverse(jobUpdatesModelList);
                adapter = new JobUpdateAdapter(mActivity, jobUpdatesModelList);
                recyclerViewJobs.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
                recyclerViewJobs.setAdapter(adapter);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
