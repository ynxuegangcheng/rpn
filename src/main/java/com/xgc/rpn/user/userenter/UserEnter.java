package com.xgc.rpn.user.userenter;

import com.xgc.rpn.user.useraction.UserAction;

import java.util.List;

/**
 * 用户输入接口
 *
 * @author xgc
 * @Date 2020/8/16
 */
public interface UserEnter {

    String SPACE = " ";

    /**
     * 获取用户输入
     *
     * @author xgc
     * @Date 2020/8/16
     */
    List<UserAction> getUserInput();
}
