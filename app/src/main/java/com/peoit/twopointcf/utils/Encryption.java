package com.peoit.twopointcf.utils;

import java.security.MessageDigest;

/**
 * Created by ling on 2015/10/30.
 * description:加密
 */
public class Encryption {
    //private static final String attach = "dqm";
    // 十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    /**
     * 加密
     *
     * @param inputString
     * @return
     */
    public static String generatePassword(String inputString) {
        //inputString += attach;
        return encodeByMD5(inputString);
    }

//    /**
//     * 密码校验
//     *
//     * @param password
//     * @param inputString
//     * @return
//     */
//    public static boolean validatePassword(String password, String inputString) {
//        inputString += attach;
//        if (password.equals(encodeByMD5(inputString))) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                // 创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                // 将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                //return resultString.toUpperCase();
                return resultString;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
