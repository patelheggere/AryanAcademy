package com.patelheggere.aryanacademy.presenter;


public class Presenter {

    public interface View{
        void updateList();
        void showProgressBar();
        void hideProgressBar();
        void noResults(String type);
        void queryLimit(String type);
    }
}
