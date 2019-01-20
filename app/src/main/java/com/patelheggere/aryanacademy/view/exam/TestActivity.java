package com.patelheggere.aryanacademy.view.exam;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.patelheggere.aryanacademy.AryanAcademyApplication;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.adapter.AssessmentListAdapter;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.model.AssessmentModel;
import com.patelheggere.aryanacademy.model.MCQQuestionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.COURSE;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.MOBILE;

public class TestActivity extends BaseActivity implements MCQExamFragment.OnFragmentInteractionListener{
    private static final String TAG = "ExamActivity";
    private Button buttonPrev, buttonNext, mButtonAnswerChart;
    private ActionBar mActionBar;
    private CountDownTimer countDownTimer;
    private String titleString;
    private TextView mTextViewTitleBar;
    private TextView mTextViewTimer;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment curFrag;
    private List<MCQQuestionModel> mcqQuestionModelList;
    private int mNumOfQuestions, mQuestionNumber;
    private Fragment fragment;
    private HashMap<String, MCQQuestionModel> mAnsweredList;
    private List<MCQQuestionModel> answerList;
    private AlertDialog mAlertDialogAnswerChart;
    private int i;
    private DatabaseReference databaseReferenceEnglish;
    private List<AssessmentModel> assessmentModelList;
    private AssessmentModel assessmentModel;
    private int NoOfCorrectAns=0;
    private int NoOfWrongAns = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_exam;
    }

    @Override
    protected void initView() {
        buttonNext = findViewById(R.id.next);
        buttonPrev = findViewById(R.id.prev);
    }

    @Override
    protected void initData() {
        mAnsweredList = new HashMap<>();
        answerList = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        curFrag = fragmentManager.getPrimaryNavigationFragment();
        assessmentModel = getIntent().getParcelableExtra("DATA");
        if(assessmentModel!=null)
        getDataFromFireBase();
        //addFragments("MCQ");
        setActionBar();
    }

    @Override
    protected void initListener() {
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoPreviousQuestion();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNextQuestion();
            }
        });
        mButtonAnswerChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnsweredQuest();
            }
        });
    }

    private void getDataFromFireBase() {
        databaseReferenceEnglish = AryanAcademyApplication.getFireBaseRef();
        databaseReferenceEnglish = databaseReferenceEnglish.child("ASSESSMENT").child("assessmentList").child("assessment"+assessmentModel.getId()).child("questions");
        databaseReferenceEnglish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mcqQuestionModelList = new ArrayList<>();
                try {
                    int i = 1;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        MCQQuestionModel mcqQuestionModel = new MCQQuestionModel();
                        mcqQuestionModel = snapshot.getValue(MCQQuestionModel.class);
                        mcqQuestionModel.setmQuestionNo(i);
                        mcqQuestionModelList.add(mcqQuestionModel);
                        i++;
                    }
                    if(mcqQuestionModelList!=null) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("DATA", mcqQuestionModelList.get(0));
                        if (curFrag != null) {
                            fragmentTransaction.detach(curFrag);
                        }
                        fragment = fragmentManager.findFragmentByTag("MCQ");
                        if (fragment == null) {
                            fragment = new MCQExamFragment();
                            fragment.setArguments(bundle);
                            mQuestionNumber = 1;
                            fragmentTransaction.add(R.id.contentFrame, fragment, "MCQ");
                        } else {
                            fragmentTransaction.attach(fragment);
                        }
                        fragmentTransaction.setPrimaryNavigationFragment(fragment);
                        fragmentTransaction.setReorderingAllowed(true);
                        fragmentTransaction.commitNowAllowingStateLoss();
                        startTimer();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millis) {
                titleString = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                mTextViewTimer.setText(titleString);
            }

            @Override
            public void onFinish() {
                titleString = "End";
                sendAnsweredData();
            }
        };
        countDownTimer.start();
    }
    private void sendAnsweredData() {

        List<MCQQuestionModel> list = new ArrayList<MCQQuestionModel>(mAnsweredList.values());
        if(list!=null)
        {
            for(int i =0 ; i<list.size(); i++)
            {
                if(list.get(i).getmUserAnswer()==mcqQuestionModelList.get(i).getCorrectAns())
                {
                    NoOfCorrectAns++;
                }
                else {
                    NoOfWrongAns++;
                }
            }
            assessmentModel.setCompleted(true);
            assessmentModel.setNoOfCorrectAns(NoOfCorrectAns);
            assessmentModel.setNoOfWrongAns(NoOfWrongAns);
            assessmentModel.setNoOfQuestions(mcqQuestionModelList.size());
            databaseReferenceEnglish = AryanAcademyApplication.getFireBaseRef().child("users").child(SharedPrefsHelper.getInstance().get(COURSE,"null")).child(SharedPrefsHelper.getInstance().get(MOBILE, "9611620128")).child("Assessments").child("assessment"+assessmentModel.getId());
            databaseReferenceEnglish.setValue(assessmentModel);
        }
        Log.d(TAG, "sendAnsweredData: "+NoOfWrongAns+" no Correct Ans: "+NoOfCorrectAns);
    }
    private void gotoNextQuestion() {
        if(mQuestionNumber<mcqQuestionModelList.size())
        {
            fragment = getSupportFragmentManager().findFragmentByTag("MCQ");
            ((MCQExamFragment) fragment).setData(mcqQuestionModelList.get(mQuestionNumber));
            mQuestionNumber++;
            //countDownTimer.cancel();
            // startTimer();
        }
    }

    private void gotoPreviousQuestion() {
        if(mQuestionNumber>0)
        {
            mQuestionNumber--;
            if(mQuestionNumber>=1) {
                fragment = getSupportFragmentManager().findFragmentByTag("MCQ");
                ((MCQExamFragment) fragment).setData(mcqQuestionModelList.get(mQuestionNumber - 1));
            }
            //countDownTimer.cancel();
            //startTimer();
        }
    }
    private void setActionBar()
    {
        mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            mActionBar.setDisplayShowCustomEnabled(true);
            mActionBar.setCustomView(R.layout.exam_custom_action_bar_lyt);
            View view =getSupportActionBar().getCustomView();
            mTextViewTitleBar = view.findViewById(R.id.textViewTitle);
            mTextViewTimer = view.findViewById(R.id.textViewTimer);
            mButtonAnswerChart = findViewById(R.id.buttonScore);
        }
    }
    @Override
    public void onFragmentInteraction(MCQQuestionModel uri) {
        if(mcqQuestionModelList.get(uri.getmQuestionNo()-1)!=null) {
            mcqQuestionModelList.get(uri.getmQuestionNo()-1).setAnswered(true);
        }
        mAnsweredList.put(uri.getmQuestionNo()+"", uri);
        Log.d(TAG, "onFragmentInteraction: "+uri.getmUserAnswer());
    }
    private void showAnsweredQuest()
    {
        int answeredCount = 0;
        for(int i = 0; i<mcqQuestionModelList.size(); i++)
        {
            if(mcqQuestionModelList.get(i).isAnswered())
            {
                answeredCount++;
            }
        }
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Answered chart");
        alertDialog.setMessage("Total Questions:"+mcqQuestionModelList.size()+"\n Total answered:"+answeredCount);
        alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAlertDialogAnswerChart.dismiss();
            }
        });
        mAlertDialogAnswerChart = alertDialog.create();
        mAlertDialogAnswerChart.show();
    }
}
