package com.xgc.rpn.Calculator.operate;

import com.xgc.rpn.Calculator.operate.record.OperateRecord;
import com.xgc.rpn.Calculator.operate.enums.OperatorsEnum;
import com.xgc.rpn.Calculator.container.Container;

/**
 * 减计算类型
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class Subtraction extends Operate {
    @Override
    public void performDetailOperation(Container container) {
        double first = container.popNum();
        double second = container.popNum();
        double result = second - first;
        container.pushNum(result);
        OperateRecord record = this.getOperationRecord(first, second);
        container.pushOperateRecord(record);
    }

    @Override
    public String getEmptyStackErrorMessage(int counter) {
        StringBuilder stringBuilder = new StringBuilder("Operator: ");
        stringBuilder.append(OperatorsEnum.SUBTRACTION.getCode());
        stringBuilder.append(" (position: ");
        stringBuilder.append(counter * 2 - 1);
        stringBuilder.append("): insucient parameters");
        return stringBuilder.toString();
    }
}
