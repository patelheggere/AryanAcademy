package com.patelheggere.aryanacademy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.helper.converters;
import com.patelheggere.aryanacademy.model.AssessmentModel;
import com.patelheggere.aryanacademy.model.JobUpdatesModel;
import com.patelheggere.aryanacademy.view.exam.TestActivity;

import java.util.List;

public class AssessmentListAdapter extends RecyclerView.Adapter<AssessmentListAdapter.AssessmentViewHolder>{

    private static final String TAG = "AssessmentListAdapter";
    List<AssessmentModel> jobList;
    private Context mContext;

    public AssessmentListAdapter(Context mContext, List<AssessmentModel> jobList) {
        this.jobList = jobList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.assessment_list_item, viewGroup, false);
        return new AssessmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder currentaffairsViewHolder, int i) {
       final AssessmentModel assessmentModel = jobList.get(i);
       if(assessmentModel!=null) {
           if (assessmentModel.getmTitle()!= null)
               currentaffairsViewHolder.title.setText(assessmentModel.getmTitle());
           currentaffairsViewHolder.date.setText(converters.TimeStampToDate(assessmentModel.getmTime()));
           currentaffairsViewHolder.totalQuestions.setText("No of Questions:"+assessmentModel.getNoOfQuestions());
           currentaffairsViewHolder.answeredWrong.setText("Total Answered Wrong:" + assessmentModel.getNoOfWrongAns());
           currentaffairsViewHolder.answeredCorrect.setText("Total Answered Correct:" + assessmentModel.getNoOfCorrectAns());
           if (assessmentModel.getEndTime() < (System.currentTimeMillis()) / 1000) {
               currentaffairsViewHolder.buttonTest.setText("OVER");
           }
           else {
               currentaffairsViewHolder.buttonTest.setText("START");
           }
           currentaffairsViewHolder.buttonTest.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (assessmentModel.getEndTime() > (System.currentTimeMillis()) / 1000) {
                       Intent intent = new Intent(mContext, TestActivity.class);
                       intent.putExtra("DATA", assessmentModel);
                       mContext.startActivity(intent);
                   } else {
                       Toast.makeText(mContext, "Already Over this ", Toast.LENGTH_LONG).show();
                   }
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class AssessmentViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, totalQuestions, answeredCorrect, answeredWrong, totalAnswered;
        Button buttonTest;
        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.assessmentTitle);
            date = itemView.findViewById(R.id.textViewTime);
            totalQuestions = itemView.findViewById(R.id.textViewTotalQuestions);
            answeredCorrect = itemView.findViewById(R.id.textViewAnsweredCorrect);
            answeredWrong = itemView.findViewById(R.id.textViewAnsweredWrong);
            totalAnswered = itemView.findViewById(R.id.textViewTotalAnswered);
            buttonTest = itemView.findViewById(R.id.buttonTakeTest);
        }
    }
}
