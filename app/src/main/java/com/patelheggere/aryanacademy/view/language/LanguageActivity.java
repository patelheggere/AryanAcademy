package com.patelheggere.aryanacademy.view.language;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.view.main.MainActivity;
import com.patelheggere.aryanacademy.view.splash.Splash2Activity;
import com.patelheggere.aryanacademy.view.welcome.WelcomeActivity;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;

public class LanguageActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonKannada, radioButtonEnglish;
    private Button btnNext;

    @Override
    protected int getContentView() {
        return R.layout.activity_language;
    }

    @Override
    protected void initView() {
        radioGroup = findViewById(R.id.rg_language);
        radioButtonEnglish = findViewById(R.id.rb_english);
        radioButtonKannada = findViewById(R.id.rb_kannada);
        btnNext = findViewById(R.id.btn_next);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
                startActivity(new Intent(LanguageActivity.this, WelcomeActivity.class));
                finish();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb_kannada) {
                    Log.d(TAG, "onCheckedChanged: kannada");
                    SharedPrefsHelper.getInstance().save(LANGUAGE, "ka");
                }
                else if(i==R.id.rb_english)
                {
                    Log.d(TAG, "onCheckedChanged: english");
                    SharedPrefsHelper.getInstance().save(LANGUAGE, "en");
                }

            }

        });

        

        
    }
}
