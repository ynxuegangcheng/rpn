package com.xgc.rpn.Calculator.operate;

import com.xgc.rpn.Calculator.action.Action;
import com.xgc.rpn.Calculator.operate.enums.OperatorsEnum;

import java.util.Optional;

/**
 * 操作类型工厂类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class OperateFactory {

    private static final OperateFactory operateFactory = new OperateFactory();

    private OperateFactory(){}

    /**
     * OperateFactory外部访问接口
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public static OperateFactory getOperateFactory() {
        return operateFactory;
    }

    /**
     * 获取操作类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public Optional<Action> getOperator(final String userEntered) {
        Optional<Action> userEntry = Optional.empty();
        try {
            OperatorsEnum operator = OperatorsEnum.fromString(userEntered);
            switch (operator) {
                case ADDITION: //+
                    userEntry = Optional.of(new Addition());
                    break;
                case SUBTRACTION: //-
                    userEntry = Optional.of(new Subtraction());
                    break;
                case MULTIPLICATION: //*
                    userEntry = Optional.of(new Multiplication());
                    break;
                case DIVISION: //÷
                    userEntry = Optional.of(new Division());
                    break;
                case SQUAREROOT: //sqrt
                    userEntry = Optional.of(new SquareRoot());
                    break;
                case CLEAR: //清除
                    userEntry = Optional.of(new Clear());
                    break;
                case UNDO: //回退
                    userEntry = Optional.of(new Undo());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return userEntry;
    }
}
