package com.patelheggere.aryanacademy.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;

import java.util.Locale;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;

public abstract class BaseActivity extends AppCompatActivity {

    protected static final String TAG = BaseActivity.class.getName();

    public Context context;
    public Activity activity;
    private String currentLanguage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setLocale();
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        activity = this;
        setContentView(getContentView());
        initView();
        initData();
        initListener();
    }

    public void setLocale() {
        Locale myLocale = new Locale(SharedPrefsHelper.getInstance().get(LANGUAGE, "en"));
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        Resources res = getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void restartActivity() {
        currentLanguage = SharedPrefsHelper.getInstance().get(LANGUAGE, "ka");
        if(!currentLanguage.equalsIgnoreCase(SharedPrefsHelper.getInstance().get(LANGUAGE, "ka"))) {
            Intent currentActivity = getIntent();
            startActivity(currentActivity);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onPause() {
        super.onPause();
    }
}
