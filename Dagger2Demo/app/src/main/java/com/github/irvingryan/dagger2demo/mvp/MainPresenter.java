package com.github.irvingryan.dagger2demo.mvp;

import javax.inject.Inject;

/**
 * Created by yanwentao on 2017/12/7.
 */

public class MainPresenter {
    String TAG = "MainPresenter";
    MainView mainView;

    @Inject
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }


    public void doSomething() {
        mainView.somethingDone("");
    }

}
