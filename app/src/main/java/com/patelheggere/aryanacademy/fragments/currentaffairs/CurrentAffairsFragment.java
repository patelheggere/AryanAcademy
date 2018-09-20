package com.patelheggere.aryanacademy.fragments.currentaffairs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseFragment;
import com.patelheggere.aryanacademy.model.CurrentAffairsModel;
import com.patelheggere.aryanacademy.model.NewsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrentAffairsFragment extends BaseFragment {

    private static final String TAG = "CurrentAffairsFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference databaseReference, db2, db3;
    private HashMap<String, List<CurrentAffairsModel>> listDataChild;
    private View mView;
    private List<String> keyList;
    private List<CurrentAffairsModel> currentAffairsModelList;
    private String key;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private StringBuffer news;
    private String newStr="";
    StringBuilder n = new StringBuilder();

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
        return mView;
    }

    private void initializeViews() {


        listDataChild = new HashMap<String, List<CurrentAffairsModel>>();
        keyList = new ArrayList<>();
        databaseReference  = FirebaseDatabase.getInstance().getReference();

       // db3 = FirebaseDatabase.getInstance().getReference().child("21-09-2018").setValue(new CurrentAffairsModel("Veerendra"));
        db3 = FirebaseDatabase.getInstance().getReference();
        //db3.child("english").child("currentaffairs").child("20-09-2018").setValue(new CurrentAffairsModel("sdfbgnhm"));
        databaseReference = databaseReference.child("english").child("currentaffairs");
        databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot snapshot : dataSnapshot.getChildren())
               {

               }
              // getNews(keyList);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
    private void getNews(final List<String> keyList)
    {
        Log.d(TAG, "calling get");
        for (int i = 0; i<keyList.size(); i++)
        {
            currentAffairsModelList = new ArrayList<>();
            news = new StringBuffer();
            key = keyList.get(i);
            db2 = FirebaseDatabase.getInstance().getReference().child("english").child("currentaffairs").child(keyList.get(i));
            db2.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    long count= dataSnapshot.getChildrenCount();
                    long init = 1;
                    n = new StringBuilder();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        CurrentAffairsModel ob = snapshot.getValue(CurrentAffairsModel.class);
                        n.append(init);
                        n.append(".");
                        n.append(ob.getMessage());
                        n.append("\n");
                        init++;
                    }
                    String st = n.toString();
                    add(st);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            NewsModel newsModel = new NewsModel(key, newStr);
            addNews(newsModel);
        }
        Log.d(TAG, "newslist:"+newsModelList.size());
        for (int j=0; j<newsModelList.size(); j++)
        {
            Log.d(TAG, "getNews: "+newsModelList.get(j).getNews());
        }
    }

    private void add(String str)
    {
        newStr =newStr+str;
    }
    private void addNews(NewsModel ob)
    {
        newsModelList.add(ob);
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
