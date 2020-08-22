package com.xgc.rpn.Calculator.operate;

import com.xgc.rpn.Calculator.operate.record.OperateRecord;
import com.xgc.rpn.Calculator.operate.enums.OperatorsEnum;
import com.xgc.rpn.Calculator.action.Action;
import com.xgc.rpn.Calculator.container.Container;

import java.util.Arrays;
import java.util.List;

/**
 * 开放计算实现类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class SquareRoot implements Action {

    @Override
    public void execute(Container container) {
        double first = container.popNum();
        if (first >= 0) {
            double result = Math.sqrt(first);
            container.pushNum(result);
            OperateRecord record = getOperationRecord(first);
            container.pushOperateRecord(record);
        }
        else {
            container.pushNum(first);
            System.err.println("Square root cannot be applied to " );
        }
    }

    /**
     * 获取操作类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    private OperateRecord getOperationRecord(double digit) {
        List<Double> params = Arrays.asList(digit);
        return new OperateRecord(params, this);
    }

    @Override
    public String getEmptyStackErrorMessage(int counter) {
        StringBuilder stringBuilder = new StringBuilder("Operator: ");
        stringBuilder.append(OperatorsEnum.SQUAREROOT.getCode());
        stringBuilder.append(" (position: ");
        stringBuilder.append(counter * 2 - 1);
        stringBuilder.append("): insucient parameters");
        return stringBuilder.toString();
    }
}
