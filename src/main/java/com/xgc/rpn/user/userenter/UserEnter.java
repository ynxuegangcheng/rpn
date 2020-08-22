package com.xgc.rpn.user.userenter;

import com.xgc.rpn.Calculator.operate.OperateFactory;
import com.xgc.rpn.Calculator.action.Action;
import com.xgc.rpn.Calculator.action.ActionImpl;

import java.io.InputStream;
import java.util.*;

/**
 * 用户输入实现类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class UserEnter {

    private static final String SPACE = " ";

    private static final String DIGIT = "^-*\\d+$";

    private Scanner scanner;

    /**
     * 获取操作类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public UserEnter(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public List<Action> getUserInput() {
        List<Action> userActions = new ArrayList<Action>();
        String userEntered = scanner.nextLine();
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
