package com.xgc.rpn.container;

import com.xgc.rpn.record.OperateRecord;
import com.xgc.rpn.user.userenter.UserEnter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 存储容器实现类
 *
 * @author xgc
 * @Date 2020/8/13
 */
public class ContainerImpl implements Container{
    private Stack<Double> numStack = new Stack<Double>();
    private Stack<OperateRecord> history = new Stack<OperateRecord>();

    @Override
    public void pushNum(Double num) {
        this.numStack.push(num);
    }

    @Override
    public double popNum() {
        return this.numStack.pop();
    }

    @Override
    public void printStack() {
        System.out.print("Stack: ");
        List<Double> nums = new ArrayList<Double>(this.numStack);
        for (Double num : nums) {
            System.out.print(format(num));
            System.out.print(UserEnter.SPACE);
        }
        System.out.print("\n");
    }

    /**
     * 格式化输出
     *
     * @author xgc
     * @Date 2020/8/13
     */
    public String format(Double num) {
        DecimalFormat numFormat = new DecimalFormat("##########.##########");
        numFormat.setRoundingMode(RoundingMode.DOWN);
        String output = numFormat.format(num);
        return output;
    }

    @Override
    public int getNumStackSize() {
        return this.numStack.size();
    }

    @Override
    public void pushOperateRecord(OperateRecord record) {
        this.history.push(record);
    }

    @Override
    public OperateRecord popOperationRecord() {
        return this.history.pop();
    }
}
