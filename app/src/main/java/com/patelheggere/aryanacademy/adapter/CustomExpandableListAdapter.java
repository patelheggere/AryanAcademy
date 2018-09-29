
package com.patelheggere.aryanacademy.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.model.CourseModel;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<CourseModel>> _listDataChild;

    public CustomExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<CourseModel>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final CourseModel childText =  (CourseModel)getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_draws_item2, null);
        }

        TextView eligibility = (TextView) convertView.findViewById(R.id.tv_eligibility_value);
        eligibility.setText(childText.getmEligibilty());

        TextView attempts = (TextView) convertView.findViewById(R.id.tv_attempts_value);
        attempts.setText(childText.getmAgeAttempts());

        TextView syllabus = (TextView) convertView.findViewById(R.id.tv_syllabus_value1);
        if(childText.getPart1()!=null)
        {
            syllabus.setText(childText.getPart1());
        }

        TextView syllabus2 = (TextView) convertView.findViewById(R.id.tv_syllabus_value2);
        if(childText.getPart2()!=null)
        {
            syllabus2.setVisibility(View.VISIBLE);
            syllabus2.setText(childText.getPart2());
        }

        TextView syllabus3 = (TextView) convertView.findViewById(R.id.tv_syllabus_value3);
        if(childText.getPart3()!=null)
        {
            syllabus3.setVisibility(View.VISIBLE);
            syllabus3.setText(childText.getPart3());
        }

        TextView syllabus4 = (TextView) convertView.findViewById(R.id.tv_syllabus_value4);
        if(childText.getPart4()!=null)
        {
            syllabus2.setVisibility(View.VISIBLE);
            syllabus2.setText(childText.getPart4());
        }

        TextView syllabus5 = (TextView) convertView.findViewById(R.id.tv_syllabus_value5);
        if(childText.getPart5()!=null)
        {
            syllabus5.setVisibility(View.VISIBLE);
            syllabus5.setText(childText.getPart5());
        }

        TextView syllabus6 = (TextView) convertView.findViewById(R.id.tv_syllabus_value6);
        if(childText.getPart6()!=null)
        {
            syllabus6.setVisibility(View.VISIBLE);
            syllabus6.setText(childText.getPart6());
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.listTitle);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}