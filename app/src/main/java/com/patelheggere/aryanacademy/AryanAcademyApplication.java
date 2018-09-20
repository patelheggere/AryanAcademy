package com.patelheggere.aryanacademy;

import android.app.Application;

public class AryanAcademyApplication extends Application {
    private static AryanAcademyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // ApiClient.intialise();
       /* if(isDeve()) {
            ApiClient.setBaseUrl(AppConstants.appBaseUrlDev);
        }
        else
        {
            ApiClient.setBaseUrl(AppConstants.appBaseUrl);

        }*/

    }

    public static synchronized AryanAcademyApplication getInstance() {
        return mInstance;
    }

}