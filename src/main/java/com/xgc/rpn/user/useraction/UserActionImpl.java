package com.xgc.rpn.user.useraction;

import com.xgc.rpn.record.OperateRecord;
import com.xgc.rpn.container.Container;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 用户操作实现类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class UserActionImpl implements UserAction{

    private final double num;

    public UserActionImpl(String userEntered) {
        this.num = new Double(userEntered);
    }

    @Override
    public void execute(Container container) {
        container.pushNum(num);
        OperateRecord record = toOperateRecord().apply(num);
        container.pushOperateRecord(record);
    }

    /**
     * 获取记录操作类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public static Function<Double, OperateRecord> toOperateRecord() {
        return d -> {
            List<Double> params = Arrays.asList(d);
            return new OperateRecord(params, null);
        };
    }

    @Override
    public String getEmptyStackErrorMessage(int counter) {
        return "";
    }
}
