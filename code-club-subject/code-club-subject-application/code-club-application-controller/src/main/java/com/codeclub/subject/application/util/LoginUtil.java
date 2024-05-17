package com.codeclub.subject.application.util;

import com.codeclub.subject.application.context.LoginContextHolder;

/**
 * 用户登录util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
