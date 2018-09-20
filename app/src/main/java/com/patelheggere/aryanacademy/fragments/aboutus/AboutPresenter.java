package com.patelheggere.aryanacademy.fragments.aboutus;

import com.patelheggere.aryanacademy.presenter.Presenter;

public class AboutPresenter implements AboutContract.Presenter {
    private static final String TAG = "AboutPresenter";

    private AboutContract.View view;

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate) {

    }

    @Override
    public void addNewTask() {

    }

    public AboutPresenter()
    {
        view.setPresenter(this);
    }
}
