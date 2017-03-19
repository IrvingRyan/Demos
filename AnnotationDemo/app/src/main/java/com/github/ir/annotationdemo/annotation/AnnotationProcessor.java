package com.github.ir.annotationdemo.annotation;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * 注解处理器
 * Created by yanwentao on 2017/3/19.
 */

public class AnnotationProcessor {
    private static String TAG = "AnnotationProcessor";

    static String animalType = "动物种类：";
    static String name = "名字：";
    static String sex = " 性别：";
    static String character = "特征：";

    public static void getAnnotation(Class<?> clz) {
        if (clz.isAnnotationPresent(Animal.class)) {
            Animal animal = clz.getAnnotation(Animal.class);
            animalType = animalType + animal.value();
            Log.i(TAG, animalType);
        }

        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Name.class)) {
                Name fruitName = field.getAnnotation(Name.class);
                name = name + fruitName.value();
                Log.i(TAG, name);
            }
            if (field.isAnnotationPresent(Gender.class)) {
                Gender gender = field.getAnnotation(Gender.class);
                sex = sex + gender.value();
                Log.i(TAG, sex);
            }
            if (field.isAnnotationPresent(Characters.class)) {
                Characters characters = field.getAnnotation(Characters.class);
                character = "行为："+characters.action()+" 爱好："+characters.love()+"有无毛发："+characters.pilifera();
                Log.i(TAG, character);
            }
        }
    }
}