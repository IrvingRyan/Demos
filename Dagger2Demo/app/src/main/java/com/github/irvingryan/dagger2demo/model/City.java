package com.github.irvingryan.dagger2demo.model;

import javax.inject.Inject;

/**
 * Created by yanwentao on 2017/12/7.
 */

public class City {
    String name;

    @Inject
    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
