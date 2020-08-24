package com.xgc.rpn.Calculator;

import com.xgc.rpn.Calculator.action.ActionImpl;
import com.xgc.rpn.Calculator.container.Container;
import com.xgc.rpn.Calculator.container.ContainerImpl;
import com.xgc.rpn.Calculator.action.Action;
import com.xgc.rpn.Calculator.operate.OperateFactory;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PRN计算器入口类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class RpnCalculator {

    private static final int ONE = 1;
    private static final String SPACE = " ";
    private static final String DIGIT = "^-*\\d+$";
    private Container container;
    private Scanner scanner;

    public RpnCalculator() {
        this(System.in);
    }

    private RpnCalculator(InputStream in) {
        if (null == in) {
            throw new IllegalArgumentException("InputStream cannot be null!");
        }

        this.scanner = new Scanner(in);
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
        while(true) {
            userActions = getUserInput();
            if (userActions.size() != 0) {
                for (Action e : userActions) {
                    try {
                        e.execute(this.container);
                        counter.incrementAndGet();
                    } catch (EmptyStackException ese) {
                        System.out.print(formatErrorMessage(e, counter.get()) + "\n");
                        break;
                    }
                }

                container.printStack();
            }
            else {
                return;
            }
        }
    }

    private String formatErrorMessage(Action e, int counter) {
        return e.getEmptyStackErrorMessage(counter);
    }

    private List<Action> getUserInput() {
        List<Action> userActions = new ArrayList<Action>();
            String userEntered = null;
            if (scanner.hasNext()) {
                userEntered = scanner.nextLine();
            }
            if (userEntered != null && userEntered.length() != 0) {
                String[] strings = userEntered.split(SPACE);
                for (String string : strings) {
                    Optional<Action> userAction = this.constructUserEntry(string);
                    if (userAction.isPresent()) {
                        userActions.add(userAction.get());
                    }
                }
            }
        return userActions;
    }


    private Optional<Action> constructUserEntry(String userEntered) {
        Optional<Action> userEntry = Optional.empty();

        if (userEntered != null && userEntered.length() != 0) {
            if (userEntered.matches(DIGIT)) {
                userEntry = getDigitalUserEntry(userEntered);
            }
            else {
                userEntry = getOperatorUserEntry(userEntered);
            }
        }
        return userEntry;
    }

    private Optional<Action> getOperatorUserEntry(String userEntered) {
        Optional<Action> userEntry = Optional.empty();

        try {
            userEntry = OperateFactory.getOperateFactory().getOperator(userEntered);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userEntry;
    }

    private Optional<Action> getDigitalUserEntry(final String userEntered) {
        Optional<Action> UserAction = Optional.empty();

        try {
            UserAction = Optional.of(new ActionImpl(userEntered));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return UserAction;
    }
}
