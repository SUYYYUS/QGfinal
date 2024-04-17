package com.suyyyus.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 用于对密码进行加密
 * 防治被破解
 */
public class MD5Util {
    /**
     * 对密码进行加盐加密
     * @param password
     * @return
     */
    public static String generateSaltPassword(String password){
        //用来生成随机数
        Random random = new Random();

        //生成16位数的盐
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = stringBuilder.length();
        if(len < 16){
            for(int i = 0; i < 16 ; i++){
                stringBuilder.append("0");
            }
        }
        //生成盐
        String salt = stringBuilder.toString();
        //组合起来
        password = md5Hex(password+salt);
        char[] cs = new char[48];
        for(int i = 0; i < 48; i+=3){
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i+1] = c;
            cs[i+2] = password.charAt(i / 3 * 2 +1);
        }
        return new String(cs);
    }


    /**
     * 验证明文和加盐后的MD5码是否匹配
     * @param password
     * @param md5
     * @return
     */
    public static boolean verifySaltPassword(String password, String md5){
        //先从MD5码中取出之前加的盐和加盐后的MD5
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i+=3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i /3 ] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        //比较两者是否相同
        return md5Hex(password+salt).equals(new String(cs1));
    }


    /**
     * 生成MD5密码
     * @param src
     * @return
     */
    public static String md5Hex(String src){

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String (new Hex().encode(bs));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
