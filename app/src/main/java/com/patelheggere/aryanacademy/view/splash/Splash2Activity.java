package com.patelheggere.aryanacademy.view.splash;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.view.language.LanguageActivity;
import com.patelheggere.aryanacademy.view.main.MainActivity;
import com.patelheggere.aryanacademy.view.welcome.WelcomeActivity;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.FIRST_TIME;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.THREE_SECOND;

public class Splash2Activity extends BaseActivity {

    @Override
    protected int getContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash2;
    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(SharedPrefsHelper.getInstance().get(FIRST_TIME, true)) {
                    Intent i = new Intent(Splash2Activity.this, LanguageActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(Splash2Activity.this, MainActivity.class);
                    startActivity(i);
                }

                // close this activity
                finish();

            }



        }, THREE_SECOND);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
