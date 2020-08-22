package com.xgc.rpn.Calculator.action;

import com.xgc.rpn.Calculator.container.Container;

/**
 * 用户操作接口
 *
 * @author xgc
 * @Date 2020/8/16
 */
public interface Action {
    /**
     * 执行操作
     *
     * @author xgc
     * @Date 2020/8/16
     */
    void execute(Container container);

    /**
     * 获取错误类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    String getEmptyStackErrorMessage(int counter);
}
