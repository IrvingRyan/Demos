package com.github.irvingryan.dagger2demo.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by yanwentao on 2017/12/7.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface CD {
}
