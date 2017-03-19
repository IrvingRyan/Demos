package com.github.ir.annotationdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yanwentao on 2017/3/19.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {

    public enum GenderType{

        Male("男"),
        Female("女"),
        Other("中性");

        private String gender;

        private GenderType(String gender){
            this.gender=gender;
        }

        @Override
        public String toString() {
            return gender;
        }
    }

    GenderType value() default GenderType.Other;
}
