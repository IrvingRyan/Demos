package com.github.irvingryan.dagger2demo.di.module;

import com.github.irvingryan.dagger2demo.mvp.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanwentao on 2017/12/7.
 */

@Module
public class MainModule {
    private MainView mView;

    public MainModule(MainView view) {
        mView = view;
    }

    @Provides
    MainView provideMainView() {
        return mView;
    }
}