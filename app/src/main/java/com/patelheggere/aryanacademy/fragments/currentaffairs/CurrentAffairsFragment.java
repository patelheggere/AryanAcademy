package com.patelheggere.aryanacademy.fragments.currentaffairs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.adapter.CurrentAffairsAdapter;
import com.patelheggere.aryanacademy.base.BaseFragment;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.model.CurrentAffairsModel;
import com.patelheggere.aryanacademy.model.NewsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE_SELECTED;

public class CurrentAffairsFragment extends BaseFragment {

    private static final String TAG = "CurrentAffairsFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference databaseReference, dbKannada, db3;
    private View mView;
    private List<String> keyList = new ArrayList<>();
    private List<String> newsModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CurrentAffairsAdapter adapter;
    private ProgressBar mProgressBar;

    private OnFragmentInteractionListener mListener;

    public CurrentAffairsFragment() {
        // Required empty public constructor
    }

    public static CurrentAffairsFragment newInstance(String param1, String param2) {
        CurrentAffairsFragment fragment = new CurrentAffairsFragment();
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
        mView =  inflater.inflate(R.layout.fragment_current_affairs, container, false);
        initializeViews();
        getNews();
        return mView;
    }

    private void initializeViews() {
        recyclerView = mView.findViewById(R.id.rv_current_affairs);
        mProgressBar = mView.findViewById(R.id.progress_bar);

    }

    private void getNews()
    {
        mProgressBar.setVisibility(View.VISIBLE);
        databaseReference  = FirebaseDatabase.getInstance().getReference();
        String lang = SharedPrefsHelper.getInstance().get(LANGUAGE, "ka");
        // db3 = FirebaseDatabase.getInstance().getReference().child("21-09-2018").setValue(new CurrentAffairsModel("Veerendra"));
        dbKannada = FirebaseDatabase.getInstance().getReference();
        //databaseReference.child(lang).child("currentaffairs").child("20-09-2018").setValue(new CurrentAffairsModel("sdfbgnhm"));
        databaseReference = databaseReference.child(lang).child("currentaffairs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                keyList = new ArrayList<>();
                newsModelList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    keyList.add(snapshot.getKey());
                    newsModelList.add(snapshot.getValue(CurrentAffairsModel.class).getMessage());
                }
                adapter = new CurrentAffairsAdapter(mActivity, keyList, newsModelList);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
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
