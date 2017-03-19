package com.github.ir.annotationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ir.annotationdemo.annotation.AnnotationProcessor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnotationProcessor.getAnnotation(Cat.class);
    }
}
