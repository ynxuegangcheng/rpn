package com.xgc.rpn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 计算工具类
 *
 * @author xgc
 * @Date 2020/8/13
 *
 */
public class CalculateUtils {

    /**
     * 判断是否是数字
     *
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        try {
            Double.valueOf(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 进行加减乘除计算
     *
     * @param numbers
     * @param operator
     */
    public static void calculate(Stack<Double> numbers, String operator) throws Exception {
        // 按照从左向右计算，则先弹出栈顶的数据是：被除数。
        double num2 = numbers.pop();
        double num1 = numbers.pop();

        switch (operator) {
            case "+":
                numbers.push(num1 + num2);
                break;
            case "-":
                numbers.push(num1 - num2);
                break;
            case "*":
                numbers.push(num1 * num2);
                break;
            case "/":
                if (num2 == 0) {
                    throw new Exception("被除数不能为0！");
                }
                numbers.push(num1 / num2);
                break;
            default:
                throw new Exception("RPN表达式错误！");
        }
    }

    /**
     * 进行开平方计算
     *
     * @param numbers
     * @param operator
     */
    public static void sqrt(Stack<Double> numbers, String operator) throws Exception {
        double num = numbers.pop();
        if (num < 0) {
            throw new Exception("负数不能开平方!");
        }
        double sqrtNum = (double) Math.sqrt(num);
        numbers.push(sqrtNum);
    }

    /**
     * 进行回退操作
     *
     * @param numbers
     * @param logList
     * @param operator
     * @throws Exception
     */
    public static void undo(Stack<Double> numbers, Stack<List<Double>> logList, String operator) throws Exception {

        // 将栈内数据清空
        while (!numbers.isEmpty()) {
            numbers.pop();
        }

        // 将上一步的操作数据存入操作数栈中
        if (!logList.isEmpty()) {
            // 弹出计算结果的日志
            logList.pop();
            // 获取计算前的栈数据
            List<Double> numbersLog = logList.peek();
            for (Double d : numbersLog) {
                if (d != null) {
                    numbers.push(d);
                }
            }
        }
    }

    /**
     * 进行清理操作
     *
     * @param numbers
     * @param logStack
     * @param operator
     */
    public static void clear(Stack<Double> numbers, Stack<List<Double>> logStack, String operator) throws Exception {
        // 清理栈里的数据
        while (!numbers.isEmpty()) {
            numbers.pop();
        }
        // 清理动作在日志栈里存入null，用于回退时区分
        List<Double> list = new ArrayList<>();
        list.add(null);
        logStack.push(list);
    }
}
