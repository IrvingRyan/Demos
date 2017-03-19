package com.github.ir.annotationdemo.annotation;

/**
 * Created by yanwentao on 2017/3/19.
 */

public @interface Name {
    String value() default "";
}
