package com.suyyyus.utils;

/**
 * 正则表达式判断学号与密码是否符合
 */
public class Validator {

    public static boolean isValidStudentid(String studentid){

        return studentid != null && studentid.matches("\\d{10}");
    }

    public static boolean isValidStuPassword(String password){

        return password != null && password.matches(".{6,}");
    }


}
