package com.patelheggere.aryanacademy.view.exam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseFragment;
import com.patelheggere.aryanacademy.model.MCQQuestionModel;


public class MCQExamFragment extends BaseFragment {
    private static final String TAG = "MCQExamFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;
    private RadioGroup mRadioGroupQuestions;
    private RadioButton mRadioButtonOp1, mRadioButtonOp2, mRadioButtonOp3, mRadioButtonOp4, mRadioButtonOp5, mRadioButtonOp6;
    private TextView mQuestionNumber, mQuestionDescription;
    private OnFragmentInteractionListener mListener;
    private MCQQuestionModel mcqQuestionModel;

    public MCQExamFragment() {
        // Required empty public constructor
    }

    public static MCQExamFragment newInstance(String param1, String param2) {
        MCQExamFragment fragment = new MCQExamFragment();
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
        mView = inflater.inflate(R.layout.fragment_mcq_exam, container, false);
        initViews();
        initData();
        initListener();
        return mView;
    }

    private void initListener() {
        mRadioGroupQuestions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonOp1) {
                    Log.d(TAG, "onCheckedChanged: op1");
                    mcqQuestionModel.setmUserAnswer(1);
                    mcqQuestionModel.setAnswered(true);
                }
                else if (i == R.id.radioButtonOp2) {
                    Log.d(TAG, "onCheckedChanged: op2");
                    mcqQuestionModel.setmUserAnswer(2);
                    mcqQuestionModel.setAnswered(true);
                }
                else if (i == R.id.radioButtonOp3) {
                    Log.d(TAG, "onCheckedChanged: op3");
                    mcqQuestionModel.setmUserAnswer(3);
                    mcqQuestionModel.setAnswered(true);
                }
                else if (i == R.id.radioButtonOp4) {
                    Log.d(TAG, "onCheckedChanged: op4");
                    mcqQuestionModel.setmUserAnswer(4);
                    mcqQuestionModel.setAnswered(true);
                }
                else if (i == R.id.radioButtonOp5) {
                    Log.d(TAG, "onCheckedChanged: op5");
                    mcqQuestionModel.setmUserAnswer(5);
                    mcqQuestionModel.setAnswered(true);
                }
                onButtonPressed(mcqQuestionModel);
            }
        });
    }

    public void setData(MCQQuestionModel mcqQuestionMode) {
        initViews();
        mcqQuestionModel = mcqQuestionMode;
        if (mcqQuestionModel != null) {
            if (mcqQuestionModel.getmNoOfOptions() == 4) {
                mRadioButtonOp5.setVisibility(View.GONE);
            }
            mQuestionNumber.setText(mcqQuestionModel.getmQuestionNo() + ".");
            mQuestionDescription.setText(mcqQuestionModel.getQuestion());
            mRadioButtonOp1.setText(mcqQuestionModel.getOptions().getOption1());
            mRadioButtonOp2.setText(mcqQuestionModel.getOptions().getOption2());
            mRadioButtonOp3.setText(mcqQuestionModel.getOptions().getOption3());
            mRadioButtonOp4.setText(mcqQuestionModel.getOptions().getOption4());
            if(mcqQuestionModel.isAnswered())
            {
                if(mcqQuestionModel.getmUserAnswer()==1)
                {
                    mRadioButtonOp1.setChecked(true);
                }
                else if(mcqQuestionModel.getmUserAnswer()==2){
                    mRadioButtonOp2.setChecked(true);
                }

                else if(mcqQuestionModel.getmUserAnswer()==3){
                    mRadioButtonOp3.setChecked(true);
                }

                else if(mcqQuestionModel.getmUserAnswer()==4){
                    mRadioButtonOp4.setChecked(true);
                }
                else if(mcqQuestionModel.getmUserAnswer()==5){
                    mRadioButtonOp5.setChecked(true);
                }
            }
            else {
                mRadioButtonOp1.setChecked(false);
                mRadioButtonOp2.setChecked(false);
                mRadioButtonOp3.setChecked(false);
                mRadioButtonOp4.setChecked(false);
            }
        }
    }

    private void initData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mcqQuestionModel = bundle.getParcelable("DATA");
        }
        if (mcqQuestionModel != null) {
            if (mcqQuestionModel.getmNoOfOptions() == 4) {
                mRadioButtonOp5.setVisibility(View.GONE);
            }
            mQuestionNumber.setText(mcqQuestionModel.getmQuestionNo() + ".");
            mQuestionDescription.setText(mcqQuestionModel.getQuestion());
            mRadioButtonOp1.setText(mcqQuestionModel.getOptions().getOption1());
            mRadioButtonOp2.setText(mcqQuestionModel.getOptions().getOption2());
            mRadioButtonOp3.setText(mcqQuestionModel.getOptions().getOption3());
            mRadioButtonOp4.setText(mcqQuestionModel.getOptions().getOption4());
        }
    }

    private void initViews() {
        mQuestionDescription = mView.findViewById(R.id.textViewQuesion);
        mQuestionNumber = mView.findViewById(R.id.textViewQuesionNumber);
        mRadioGroupQuestions = mView.findViewById(R.id.radioGroupQuestions);
        mRadioButtonOp1 = mView.findViewById(R.id.radioButtonOp1);
        mRadioButtonOp2 = mView.findViewById(R.id.radioButtonOp2);
        mRadioButtonOp3 = mView.findViewById(R.id.radioButtonOp3);
        mRadioButtonOp4 = mView.findViewById(R.id.radioButtonOp4);
        mRadioButtonOp5 = mView.findViewById(R.id.radioButtonOp5);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(MCQQuestionModel uri) {
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
        void onFragmentInteraction(MCQQuestionModel uri);
    }
}
