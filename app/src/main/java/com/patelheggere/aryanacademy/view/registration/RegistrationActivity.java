package com.patelheggere.aryanacademy.view.registration;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patelheggere.aryanacademy.AryanAcademyApplication;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.helper.AppUtils;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.model.UserDetails;
import com.patelheggere.aryanacademy.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.COURSE;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.EMAIL;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.FIRST_TIME;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.MOBILE;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.NAME;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.TEN_SECOND;

public class RegistrationActivity extends BaseActivity {

    private static final String TAG = "RegistrationActivity";
    private ActionBar mActionBar;
    private Spinner spinner;
    private TextInputEditText textInputEditTextName, textInputEditTextPhone, textInputEditTextEmail;
    private String course;
    private List<String> listInterest;
    private ArrayAdapter<String> adapter;
    private Button mButtonSubmit;
    private DatabaseReference databaseReference;

    @Override
    protected int getContentView() {
        return R.layout.activity_registration;
    }

    @Override
    protected void initView() {
       addListofInterest();
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getString(R.string.registration));
        spinner = findViewById(R.id.sp_interest);
        textInputEditTextEmail = findViewById(R.id.et_email);
        textInputEditTextName = findViewById(R.id.et_name);
        textInputEditTextPhone = findViewById(R.id.et_phone);
        mButtonSubmit = findViewById(R.id.btn_register);
    }

    private void addListofInterest() {
        listInterest = new ArrayList<>();
        listInterest.add("Select one");
        listInterest.add("KAS");
        listInterest.add("PDO");
        listInterest.add("FDA");
        listInterest.add("SDA");
        listInterest.add("BANKING");
        listInterest.add("TET");
        listInterest.add("CET");
        listInterest.add("NEET");
        listInterest.add("POLICE");
        listInterest.add("PSI");
        listInterest.add("KES");
        listInterest.add("OTHERS");
    }

    @Override
    protected void initData() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listInterest);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course = listInterest.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitDetails();
            }
        });
    }

    private void submitDetails() {
        try {
            if(textInputEditTextName.getText()==null || textInputEditTextName.getText().length()<3)
            {
                textInputEditTextName.setError(getString(R.string.name_required));
                return;
            }
            if(textInputEditTextPhone.getText()==null || textInputEditTextPhone.getText().length()!=10)
            {
                textInputEditTextPhone.setError(getString(R.string.phone_correct));
                return;
            }
            if(course.contains("Select one"))
            {
                AppUtils.showSnackBar(activity, getString(R.string.please_select_area));
                return;
            }
           /* if(textInputEditTextEmail.getText()!=null) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(textInputEditTextEmail.getText()).matches())) {
                    return;
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        databaseReference = AryanAcademyApplication.getFireBaseRef();
        databaseReference = databaseReference.child("users").child(course);
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(textInputEditTextEmail.getText().toString());
        userDetails.setInterest(course);
        userDetails.setMobile(textInputEditTextPhone.getText().toString());
        userDetails.setName(textInputEditTextName.getText().toString());
        databaseReference.child(textInputEditTextPhone.getText().toString()).child("profile").setValue(userDetails);
        SharedPrefsHelper.getInstance().save(FIRST_TIME, false);
        SharedPrefsHelper.getInstance().save(COURSE, course);
        SharedPrefsHelper.getInstance().save(NAME,textInputEditTextName.getText().toString());
        SharedPrefsHelper.getInstance().save(EMAIL, textInputEditTextEmail.getText().toString());
        SharedPrefsHelper.getInstance().save(MOBILE, textInputEditTextPhone.getText().toString());
        startActivity(new Intent(activity, MainActivity.class));
        finish();
    }
}
