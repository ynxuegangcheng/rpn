package com.xgc.rpn.container;

import com.xgc.rpn.record.OperateRecord;

/**
 * 存储容器接口
 *
 * @author xgc
 * @Date 2020/8/13
 */
public interface Container {

    /**
     * 数字压栈
     *
     * @author xgc
     * @Date 2020/8/16
     */
    void pushNum(Double userEntry);

    /**
     * 数字弹出
     *
     * @author xgc
     * @Date 2020/8/16
     */
    double popNum();

    /**
     * 打印栈内容
     *
     * @author xgc
     * @Date 2020/8/16
     */
    void printStack();

    /**
     * 获取大小
     *
     * @author xgc
     * @Date 2020/8/16
     */
    int getNumStackSize();

    /**
     * 压栈操作记录
     *
     * @author xgc
     * @Date 2020/8/16
     */
    void pushOperateRecord(OperateRecord record);

    /**
     * 弹出操作记录
     *
     * @author xgc
     * @Date 2020/8/16
     */
    OperateRecord popOperationRecord();
}
