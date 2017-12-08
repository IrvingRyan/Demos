package com.github.irvingryan.dagger2demo.di.component;

import com.github.irvingryan.dagger2demo.MainActivity;
import com.github.irvingryan.dagger2demo.di.module.BeanModule;
import com.github.irvingryan.dagger2demo.di.module.MainModule;

import dagger.Component;

/**
 * Created by yanwentao on 2017/12/7.
 */
@Component(modules = {MainModule.class, BeanModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
