package com.github.irvingryan.dagger2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.irvingryan.dagger2demo.di.component.DaggerMainComponent;
import com.github.irvingryan.dagger2demo.di.module.MainModule;
import com.github.irvingryan.dagger2demo.di.qualifier.CD;
import com.github.irvingryan.dagger2demo.model.City;
import com.github.irvingryan.dagger2demo.mvp.MainPresenter;
import com.github.irvingryan.dagger2demo.mvp.MainView;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements MainView {
    @Inject
    public MainPresenter mainPresenter;

//    @Named("CD")
    @CD
    @Inject
    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    public void click01(View view) {
        mainPresenter.doSomething();
    }


    public void click02(View view) {
        Toast.makeText(this, city.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void somethingDone(String s) {
        Toast.makeText(this, "something done !! ", Toast.LENGTH_SHORT).show();
    }
}
