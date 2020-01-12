package com.example.android.udacity_baking_app.base;


public interface MvpView {

    void showLoading();

    void hideLoading();

    void showErrorMessage();

    void showNetworkConnectionError();

    void showServerError();
}
