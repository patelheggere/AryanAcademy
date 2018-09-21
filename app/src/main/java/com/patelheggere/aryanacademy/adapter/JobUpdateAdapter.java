package com.patelheggere.aryanacademy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.model.JobUpdatesModel;

import java.util.List;

public class JobUpdateAdapter extends RecyclerView.Adapter<JobUpdateAdapter.CurrentaffairsViewHolder>{

    List<JobUpdatesModel> jobList;
    private Context mContext;

    public JobUpdateAdapter(Context mContext, List<JobUpdatesModel> jobList) {
        this.jobList = jobList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CurrentaffairsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.job_item, viewGroup, false);
        return new CurrentaffairsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentaffairsViewHolder currentaffairsViewHolder, int i) {
       JobUpdatesModel jobUpdatesModel = jobList.get(i);
       if(jobUpdatesModel.getDept()!=null)
           currentaffairsViewHolder.dept.setText(jobUpdatesModel.getDept());

        if(jobUpdatesModel.getDocuments()!=null)
            currentaffairsViewHolder.documents.setText(jobUpdatesModel.getDocuments());

        if(jobUpdatesModel.getWebsite()!=null)
            currentaffairsViewHolder.website.setText(jobUpdatesModel.getWebsite());

        if(jobUpdatesModel.getExamDate()!=null)
            currentaffairsViewHolder.examDate.setText(jobUpdatesModel.getExamDate());

        if(jobUpdatesModel.getExamMode()!=null)
            currentaffairsViewHolder.examMode.setText(jobUpdatesModel.getExamMode());


        if(jobUpdatesModel.getLastDate()!=null)
            currentaffairsViewHolder.lastDate.setText(jobUpdatesModel.getLastDate());

        if(jobUpdatesModel.getNoPosts()!=null)
            currentaffairsViewHolder.noPost.setText(jobUpdatesModel.getNoPosts());

        if(jobUpdatesModel.getNotiDate()!=null)
            currentaffairsViewHolder.noti_date.setText(jobUpdatesModel.getNotiDate());

        if(jobUpdatesModel.getPaymentMode()!=null)
            currentaffairsViewHolder.payment.setText(jobUpdatesModel.getPaymentMode());

        if(jobUpdatesModel.getQualification()!=null)
            currentaffairsViewHolder.qualification.setText(jobUpdatesModel.getQualification());

        if(jobUpdatesModel.getTitle()!=null)
            currentaffairsViewHolder.title.setText(jobUpdatesModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class CurrentaffairsViewHolder extends RecyclerView.ViewHolder {

        TextView title, dept, qualification, noti_date, lastDate, examMode, payment, documents, examDate, noPost, website;
        public CurrentaffairsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_job_title);
            dept = itemView.findViewById(R.id.tv_dept);

            qualification = itemView.findViewById(R.id.tv_qualifiacation_value);
            noti_date = itemView.findViewById(R.id.tv_noti_date_value);

            lastDate = itemView.findViewById(R.id.tv_last_date_value);
            examMode  = itemView.findViewById(R.id.tv_mode_of_exam_value);

            payment = itemView.findViewById(R.id.tv_payment_mode_value);
            documents = itemView.findViewById(R.id.tv_documents_value);

            examDate = itemView.findViewById(R.id.tv_exam_date_value);
            noPost = itemView.findViewById(R.id.tv_no_of_post_value);

            website = itemView.findViewById(R.id.tv_web_value);
        }
    }
}
