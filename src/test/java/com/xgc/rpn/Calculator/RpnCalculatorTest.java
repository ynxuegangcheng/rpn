package com.xgc.rpn.Calculator;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class RpnCalculatorTest {

    /**
     * Example 1
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest1() {
        String input = "5 2";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 5 2 \n", bos.toString());
    }

    /**
     * Example 2
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest2() {
        String input = "2 sqrt\r" + "clear 9 sqrt";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 1.4142135623 \n" + "Stack: 3 \n", bos.toString());
    }

    /**
     * Example 3
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest3() {
        String input = "5 2 -\r" + "3 -\r" + "clear";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 3 \n" + "Stack: 0 \n" + "Stack: \n", bos.toString());
    }

    /**
     * Example 4
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest4() {
        String input = "5 4 3 2\r" + "undo undo *\r" + "5 *\r" + "undo";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 5 4 3 2 \n" + "Stack: 20 \n" + "Stack: 100 \n" + "Stack: 20 5 \n", bos.toString());
    }

    /**
     * Example 5
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest5() {
        String input = "7 12 2 /\r" + "*\r" + "4 /";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 7 6 \n" + "Stack: 42 \n" + "Stack: 10.5 \n", bos.toString());
    }

    /**
     * Example 6
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest6() {
        String input = "1 2 3 4 5\r" + "*\r" + "clear 3 4 -";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 1 2 3 4 5 \n" + "Stack: 1 2 3 20 \n" + "Stack: -1 \n", bos.toString());
    }

    /**
     * Example 7
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest7() {
        String input = "1 2 3 4 5\r" + "* * * *";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Stack: 1 2 3 4 5 \n" + "Stack: 120 \n", bos.toString());
    }

    /**
     * Example 8
     *
     * @author xgc
     * @Date 2020/8/16
     */
    @Test
    public void doCalculateTest8() {
        String input = "1 2 3 * 5 + * * 6 5";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        RpnCalculator rpnCalculator = new RpnCalculator();
        rpnCalculator.doCalculate();
        assertEquals("Operator: * (position: 15): insucient parameters\n" + "Stack: 11 \n", bos.toString());
    }
}