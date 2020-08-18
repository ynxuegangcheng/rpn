package com.xgc.rpn.Calculator.operate;

import com.xgc.rpn.Calculator.operate.record.OperateRecord;
import com.xgc.rpn.Calculator.operate.enums.OperatorsEnum;
import com.xgc.rpn.user.useraction.UserAction;
import com.xgc.rpn.Calculator.container.Container;
import java.util.function.Predicate;

/**
 * 回退操作计算类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class Undo implements UserAction {
    @Override
    public void execute(Container container) {
        OperateRecord record = container.popOperationRecord();

        UserAction operator = record.getUserAction();
        if (isNeedClearUpResult().test(operator)) {
            container.popNum();
        }
        if (null != record.getUserAction()) {
            for(double digit : record.getParameters()) {
                container.pushNum(digit);
            }
        }
    }

    public static Predicate<UserAction> isNeedClearUpResult() {
        return e -> ((null == e) || (!(e instanceof Clear)));
    }

    @Override
    public String getEmptyStackErrorMessage(int counter) {
        StringBuilder stringBuilder = new StringBuilder("Operator: ");

        stringBuilder.append(OperatorsEnum.UNDO.getCode());

        stringBuilder.append(" (position: ");
        stringBuilder.append(counter * 2 - 1);
        stringBuilder.append("): insucient parameters");

        return stringBuilder.toString();
    }
}
