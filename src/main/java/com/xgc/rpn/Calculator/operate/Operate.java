package com.xgc.rpn.Calculator.operate;

import com.xgc.rpn.Calculator.operate.record.OperateRecord;
import com.xgc.rpn.Calculator.action.Action;
import com.xgc.rpn.Calculator.container.Container;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

/**
 * 执行操作抽象类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public abstract class Operate implements Action {
    private static final int TWO = 2;

    /**
     * 获取操作记录
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public OperateRecord getOperationRecord(Double first, Double second) {
        List<Double> params = Arrays.asList(second, first);
        return new OperateRecord(params, this);
    }

    /**
     * 判断是否为有效输入
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public boolean isValidOperation(Container container) {
        if (container.getNumStackSize() < TWO) {
            throw new EmptyStackException();
        }
        return true;
    }


    @Override
    public void execute(Container container) {
        if (isValidOperation(container)) {
            performDetailOperation(container);
        }
    }

    /**
     * 执行数字操作抽象方法
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public abstract void performDetailOperation(Container container);
}
