package com.codeclub.subject.common.util;


import com.codeclub.subject.common.context.LoginContextHolder;

/**
 * 用户登录util
 */
public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
