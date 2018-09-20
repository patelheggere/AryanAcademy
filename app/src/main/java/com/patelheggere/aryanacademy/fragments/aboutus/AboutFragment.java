package com.patelheggere.aryanacademy.fragments.aboutus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.adapter.CustomExpandableListAdapter;
import com.patelheggere.aryanacademy.base.BaseFragment;
import com.patelheggere.aryanacademy.model.Data;
import com.patelheggere.aryanacademy.view.main.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class AboutFragment extends BaseFragment implements AboutContract.View{


    private OnFragmentInteractionListener mListener;
    private AboutContract.Presenter mPresenter;
    private FirebaseFirestore mFirestore;
    private List<String> expandableListTitle;
    private HashMap<String, String> expandableListDetail2;
    private ExpandableListView expandableListView;
    private int lastExpandedGroupPosition;
    private View mView;
    private CustomExpandableListAdapter expandableListAdapter;
    CustomExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
       /* args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          /*  mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
        //initFirestore();
    }

    private void initFirestore() {
        mFirestore = FirebaseFirestore.getInstance();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("NAME", "Chanakya");
        hashMap.put("EMAIL", "chanakya@gmail.com");
        String phone = "9611620128";
        mFirestore.collection("CurrentAffairs").document("State").collection(phone).document().set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(mActivity, "User Registered",
                        Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mActivity, "Fail Registered",
                        Toast.LENGTH_SHORT).show();
            }
        });

        DocumentReference user = mFirestore.collection("CurrentAffairs").document("State").collection(phone).document("09Qev2PRb22Qo2l6R11d");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Log.d("", "onComplete: "+documentSnapshot.get("NAME"));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("", "onFailure: ");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_about, container, false);
        expandableListTitle = new ArrayList<String>();
        expandableListTitle.add("PDO");
        expandableListTitle.add("KAS");
        expandableListTitle.add("PDO");
        expandableListTitle.add("KAS");
        expandableListDetail2 = new HashMap<String, String>();
        expandableListDetail2.put("PDO","PDO");
        expandableListDetail2.put("KAS","PDO");
        expandableListDetail2.put("FDA","PDO");
        expandableListDetail2.put("SDA","PDO");
        expListView = mView.findViewById(R.id.expandableListView);

        //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        prepareListData();

        listAdapter = new CustomExpandableListAdapter(mActivity, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != lastExpandedGroupPosition){
                    expListView.collapseGroup(lastExpandedGroupPosition);
                }
                lastExpandedGroupPosition = groupPosition;
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("KAS");
        listDataHeader.add("PDO");
        listDataHeader.add("BANKING");
        listDataHeader.add("FDA");
        listDataHeader.add("SDA");
        listDataHeader.add("IAS");
        listDataHeader.add("KAS");
        listDataHeader.add("PDO");
        listDataHeader.add("BANKING");
        listDataHeader.add("FDA");
        listDataHeader.add("SDA");
        listDataHeader.add("IAS");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(4), nowShowing);
        listDataChild.put(listDataHeader.get(5), comingSoon);
        listDataChild.put(listDataHeader.get(6), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(7), nowShowing);
        listDataChild.put(listDataHeader.get(8), comingSoon);
        listDataChild.put(listDataHeader.get(9), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(10), nowShowing);
        listDataChild.put(listDataHeader.get(11), comingSoon);
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

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTasks(List<Task> tasks) {

    }

    @Override
    public void showAddTask() {

    }

    @Override
    public void showTaskDetailsUi(String taskId) {

    }

    @Override
    public void showTaskMarkedComplete() {

    }

    @Override
    public void showTaskMarkedActive() {

    }

    @Override
    public void showCompletedTasksCleared() {

    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void showActiveFilterLabel() {

    }

    @Override
    public void showCompletedFilterLabel() {

    }

    @Override
    public void showAllFilterLabel() {

    }

    @Override
    public void showNoActiveTasks() {

    }

    @Override
    public void showNoCompletedTasks() {

    }

    @Override
    public void showSuccessfullySavedMessage() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showFilteringPopUpMenu() {

    }

    @Override
    public void setPresenter(AboutContract.Presenter presenter) {
        this.mPresenter = new AboutPresenter();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
