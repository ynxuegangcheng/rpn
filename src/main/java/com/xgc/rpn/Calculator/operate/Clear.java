package com.xgc.rpn.Calculator.operate;

import com.xgc.rpn.Calculator.operate.record.OperateRecord;
import com.xgc.rpn.Calculator.operate.enums.OperatorsEnum;
import com.xgc.rpn.user.useraction.UserAction;
import com.xgc.rpn.Calculator.container.Container;

import java.util.*;
import java.util.function.Consumer;

/**
 * 清除操作实现类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class Clear implements UserAction {
    @Override
    public void execute(Container container) {
        List<Double> elements = new ArrayList<Double>();
        Double digit;

        try {
            while (null != (digit = container.popNum())) {
                elements.add(digit);
            }
        }
        catch (EmptyStackException e) {
            Consumer<OperateRecord> consumer = record -> container.pushOperateRecord(record);
            Optional<OperateRecord> record = this.getOperationRecord(elements);
            record.ifPresent(consumer);
        }
    }

    public Optional<OperateRecord> getOperationRecord(List<Double> elements) {
        if (elements != null && elements.size() != 0) {
            Collections.reverse(elements);
            return Optional.of(new OperateRecord(elements, this));
        }
        return Optional.empty();
    }

    @Override
    public String getEmptyStackErrorMessage(int counter) {
        StringBuilder stringBuilder = new StringBuilder("Operator: ");

        stringBuilder.append(OperatorsEnum.CLEAR.getCode());

        stringBuilder.append(" (position: ");
        stringBuilder.append(counter * 2 - 1);
        stringBuilder.append("): insucient parameters");

        return stringBuilder.toString();
    }
}
