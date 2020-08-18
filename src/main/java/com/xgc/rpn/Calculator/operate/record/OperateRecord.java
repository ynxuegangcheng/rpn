package com.xgc.rpn.Calculator.operate.record;

import com.xgc.rpn.user.useraction.UserAction;
import java.util.List;


public class OperateRecord {
    private List<Double> parameters;
    private UserAction userAction;

    /**
     * 获取输入参数
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public List<Double> getParameters() {
        return parameters;
    }

    /**
     * 获取用户操作
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public UserAction getUserAction() {
        return userAction;
    }

    /**
     * 操作记录处理封装
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public OperateRecord(List<Double> parameters, UserAction userAction) {
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("parameters cannot be null！");
        }
        this.parameters = parameters;
        this.userAction = userAction;
    }
}
