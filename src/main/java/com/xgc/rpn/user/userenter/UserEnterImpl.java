package com.xgc.rpn.user.userenter;

import com.xgc.rpn.user.useraction.UserAction;
import com.xgc.rpn.user.operate.OperateFactory;
import com.xgc.rpn.user.useraction.UserActionImpl;

import java.io.InputStream;
import java.util.*;

/**
 * 用户输入实现类
 *
 * @author xgc
 * @Date 2020/8/16
 */
public class UserEnterImpl implements UserEnter{

    private static final String DIGIT = "^-*\\d+$";

    private Scanner scanner;

    /**
     * 获取操作类型
     *
     * @author xgc
     * @Date 2020/8/16
     */
    public UserEnterImpl(InputStream in) {
        this.scanner = new Scanner(in);
    }

    @Override
    public List<UserAction> getUserInput() {
        List<UserAction> userActions = new ArrayList<UserAction>();
        // User interactive mode
        String userEntered = scanner.nextLine();
        if (userEntered != null && userEntered.length() != 0) {
            String[] strings = userEntered.split(UserEnter.SPACE);
            for (String string : strings) {
                Optional<UserAction> userAction = this.constructUserEntry(string);
                if (userAction.isPresent()) {
                    userActions.add(userAction.get());
                }
            }
        }
        return userActions;
    }


    private Optional<UserAction> constructUserEntry(String userEntered) {
        Optional<UserAction> userEntry = Optional.empty();

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

    private Optional<UserAction> getOperatorUserEntry(String userEntered) {
        Optional<UserAction> userEntry = Optional.empty();

        try {
            userEntry = OperateFactory.getOperator(userEntered);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userEntry;
    }

    private Optional<UserAction> getDigitalUserEntry(final String userEntered) {
        Optional<UserAction> userEntry = Optional.empty();

        try {
            userEntry = Optional.of(new UserActionImpl(userEntered));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userEntry;
    }
}
