package com.github.ir.annotationdemo;

import com.github.ir.annotationdemo.annotation.Animal;
import com.github.ir.annotationdemo.annotation.Characters;
import com.github.ir.annotationdemo.annotation.Gender;
import com.github.ir.annotationdemo.annotation.Name;

/**
 * Created by yanwentao on 2017/3/19.
 */
@Animal("Cat")
public class Cat {
    @Name("Tom")
    String name;
    @Gender(Gender.GenderType.Male)
    String gender;
    @Characters(action = "run",love = "mouse",pilifera = true)
    String character;
}
