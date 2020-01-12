package com.example.android.udacity_baking_app.base;


public interface BasePresenter<V> {

    void setView(V view);

    void detachView();
}
