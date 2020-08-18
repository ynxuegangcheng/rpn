package com.xgc.rpn.Calculator;

import com.xgc.rpn.Calculator.container.Container;
import com.xgc.rpn.Calculator.container.ContainerImpl;
import com.xgc.rpn.Calculator.operate.OperateFactory;
import com.xgc.rpn.user.useraction.UserAction;
import com.xgc.rpn.user.userenter.UserEnter;
import com.xgc.rpn.user.userenter.UserEnterImpl;

import java.io.InputStream;
import java.util.EmptyStackException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PRN计算器入口类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class RpnCalculator {

    private static final int ONE = 1;
    private UserEnter userEnter;
    private Container container;
    private OperateFactory operateFactory;

    public RpnCalculator() {
        this(System.in);
    }

    public RpnCalculator(InputStream in) {
        if (null == in) {
            throw new IllegalArgumentException("InputStream cannot be null!");
        }

        this.userEnter = new UserEnterImpl(in);
        this.container = new ContainerImpl();
        this.operateFactory = new OperateFactory();
    }

    public OperateFactory getOperateFactory() {
        return operateFactory;
    }

    /**
     * 执行计算
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public void doCalculate() {
        List<UserAction> userActions = null;
        AtomicInteger counter = new AtomicInteger(ONE);
        while( null != (userActions = this.userEnter.getUserInput())) {
            for(UserAction e : userActions) {
                try {
                    e.execute(this.container);
                    counter.incrementAndGet();
                }
                catch (EmptyStackException ese) {
                    System.out.print(formatErrorMessage(e, counter.get()) + "\n");
                    break;
                }
            }
            container.printStack();
        }
    }

    /**
     * 格式化异常日志
     *
     * @author xgc
     * @Date 2020/8/16
     */
    private String formatErrorMessage(UserAction e, int counter) {
        return e.getEmptyStackErrorMessage(counter);
    }
}
