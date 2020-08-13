package com.xgc.rpn;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PRN计算器类
 *
 * @author xgc
 * @Date 2020/8/13
 */
public class RpnCalculator {

    // 记录当前可以操作的数的栈
    private final Stack<Double> numbers = new Stack<Double>();
    // 记录栈数据的操作日志
    private final Stack<List<Double>> logStack = new Stack<List<Double>>();

    /**
     * 计算入口
     * @param expression
     */
    public void doCalculate(String expression) throws Exception {

        // 通过空格分隔切分输入的表达式
        String[] paramArr = expression.split(" ");

        int paramArrLenth = paramArr.length;

        for (int i = 0; i < paramArrLenth; i++) {
            String operator = paramArr[i];

            // 判断是数字则入栈，记录栈日志
            if (CalculateUtils.isNumber(operator)) {
                numbers.push(Double.valueOf(operator));
                putLogList(numbers, logStack);
                continue;
            }

            // 判断是加减乘除操作，则进行相应的计算，计算完成记录日志
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
                if (numbers.size() > 1) {
                    CalculateUtils.calculate(numbers, operator);
                    putLogList(numbers, logStack);
                } else {
                    System.out.print("operator " + operator + " (position: " + (i * 2 + 1) + "): insufficient parameters ");// 判断如果操作数不足则退出循环，提示位置； （i*2+1）位置需要加上空格
                    break;
                }
            } else if ("sqrt".equals(operator)) {// 判断如果是开平方，则进入
                if (numbers.size() > 0) {
                    CalculateUtils.sqrt(numbers, operator);
                    putLogList(numbers, logStack);
                } else {
                    System.out.print("operator " + operator + " (position: " + (i * 2 + 1) + "): insufficient parameters ");
                    break;
                }
            } else if ("undo".equals(operator)) {// 判断如果是回退，则进入
                CalculateUtils.undo(numbers, logStack, operator);
            } else if ("clear".equals(operator)) {// 判断是清除，则进入
                CalculateUtils.clear(numbers, logStack, operator);
            } else {
                throw new Exception("输入的RPN表达式错误！");
            }
        }
        printStack(numbers);
    }

    /**
     * 将操作数栈的里数据记录到日志栈中
     * @param numbers
     * @param logList
     */
    private void putLogList(Stack<Double> numbers, Stack<List<Double>> logList) {
        List<Double> numbersList = new ArrayList<>(numbers);
        logList.push(numbersList);
    }

    /**
     * 打印栈数据
     * @param numbers
     */
    private void printStack(Stack<Double> numbers) {
        System.out.print("stack： ");
        if(!numbers.isEmpty()) {
            for(double d : numbers) {
                System.out.print(numberFormat(d) + " ");
            }
        }
        System.out.println();
    }

    /**
     * 定义显示精度
     * @param number
     * @return
     */
    private String numberFormat(double number) {
        DecimalFormat numFormat = new DecimalFormat("##########.##########");
        numFormat.setRoundingMode(RoundingMode.DOWN);
        String output = numFormat.format(number);
        return output;
    }
}
