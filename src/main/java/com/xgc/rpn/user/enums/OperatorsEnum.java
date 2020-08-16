package com.xgc.rpn.user.enums;

/**
 * 计算类型枚举类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public enum  OperatorsEnum {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SQUAREROOT("sqrt"),
    UNDO("undo"),
    CLEAR("clear");


    OperatorsEnum(String code) {
        this.code = code;
    }

    private String code;

    /**
     * 获取枚举类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 处理计算操作类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public static OperatorsEnum fromString(String userEntered) {
        for(OperatorsEnum operator : OperatorsEnum.values()) {
            if (operator.getCode().equalsIgnoreCase(userEntered)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("userEntered cannot be " + userEntered);
    }
}
