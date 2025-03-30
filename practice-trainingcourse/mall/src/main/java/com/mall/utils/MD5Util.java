package com.mall.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 李茂鑫
 * @Date
 * @Descripition MD5工具类,用于密码加密
 */
public class MD5Util {
    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;// 用于计算md5

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");// MessageDigest专门做md5加密
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            System.err.println("初始化MD5失败，MessageDigest不支持MD5Util");
            noSuchAlgorithmException.printStackTrace();
        }
    }

    /**
     * 生成MD5字符串校验码
     *
     * @param s 要计算的字符串
     * @return 计算后的md5字符串
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     *
     * @param password  要校验的字符串
     * @param md5PwdStr 已知的md5校验码
     * @return true表示校验成功，false表示校验失败
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * 计算并返回md5字符串
     */
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    /**
     * 作用是将计算出的字节数组转换为16进制表示的字符串
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, bytes.length);
    }

    /**
     * 作用是将计算出的字节数组中指定区间的元素转换为16进制表示的字符串
     *
     * @param bytes 字节数组
     * @param n     结束位置
     * @return 16进制字符串
     */
    private static String bufferToHex(byte[] bytes, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        for (int l = 0; l < n; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    /**
     * 作用是将一个字节转化成2个HEX字符
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>>
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.getMD5String("123456"));
    }
}
