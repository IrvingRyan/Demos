package com.irvingryan.unittestdemo;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by wentao on 2016/9/2.
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        assertEquals(6d, calculator.sum(1d, 5d), 0);
    }

    @Test
    public void testSubstract() throws Exception {
        assertEquals(-4d, calculator.substract(1d, 5d), 0);

    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(1/5d, calculator.divide(1d, 5d), 0);

    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(5d, calculator.multiply(1d, 5d), 0);

    }
}