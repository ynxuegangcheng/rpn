package com.xgc.rpn.Calculator;

import com.xgc.rpn.Calculator.container.Container;
import com.xgc.rpn.Calculator.container.ContainerImpl;
import com.xgc.rpn.Calculator.action.Action;
import com.xgc.rpn.user.userenter.UserEnter;

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

    public RpnCalculator() {
        this(System.in);
    }

    private RpnCalculator(InputStream in) {
        if (null == in) {
            throw new IllegalArgumentException("InputStream cannot be null!");
        }

        this.userEnter = new UserEnter(in);
        this.container = new ContainerImpl();
    }

    /**
     * 执行计算
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public void doCalculate() {
        List<Action> userActions = null;
        AtomicInteger counter = new AtomicInteger(ONE);
        while( null != (userActions = this.userEnter.getUserInput())) {
            for(Action e : userActions) {
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
    private String formatErrorMessage(Action e, int counter) {
        return e.getEmptyStackErrorMessage(counter);
    }
}
