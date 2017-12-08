package com.github.irvingryan.dagger2demo.di.module;

import com.github.irvingryan.dagger2demo.di.qualifier.CD;
import com.github.irvingryan.dagger2demo.model.City;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yanwentao on 2017/12/7.
 */
@Module
public class BeanModule {
//    @Named("CD")
    @CD
    @Provides
    public City provideCityCD() {
        return new City("成都");
    }

    @Named("SZ")
    @Provides
    public City provideCitySZ() {
        return new City("深圳");
    }

}
