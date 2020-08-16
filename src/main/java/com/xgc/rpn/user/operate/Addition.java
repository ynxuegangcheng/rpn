package com.xgc.rpn.user.operate;

import com.xgc.rpn.record.OperateRecord;
import com.xgc.rpn.user.enums.OperatorsEnum;
import com.xgc.rpn.container.Container;

/**
 * 加计算实现类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class Addition extends Operate{

    @Override
    public void performDetailOperation(Container container) {
        double first = container.popNum();
        double second = container.popNum();
        double result = second + first;
        container.pushNum(result);
        OperateRecord record = getOperationRecord(first, second);
        container.pushOperateRecord(record);
    }

    @Override
    public String getEmptyStackErrorMessage(int counter) {
        StringBuilder stringBuilder = new StringBuilder("Operator: ");

        stringBuilder.append(OperatorsEnum.ADDITION.getCode());

        stringBuilder.append(" (position: ");
        stringBuilder.append(counter * 2 - 1);
        stringBuilder.append("): insucient parameters");

        return stringBuilder.toString();
    }
}
