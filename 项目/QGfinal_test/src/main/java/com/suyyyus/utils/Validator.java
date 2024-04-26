package com.suyyyus.utils;

/**
 * 正则表达式判断学号与密码是否符合
 */
public class Validator {

    public static boolean isValidStudentid(String studentid){
        //判断学生
        return studentid != null && studentid.matches("\\d{10}");
    }

    public static boolean isValidStuPassword(String password){
        //判断密码
        return password != null && password.matches(".{6,}");
    }


}
