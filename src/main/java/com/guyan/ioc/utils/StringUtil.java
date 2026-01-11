package com.guyan.ioc.utils;

public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 待判断的字符串
     * @return true 如果字符串为 null 或空字符串
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 待判断的字符串
     * @return true 如果字符串不为 null 且不为空字符串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白
     *
     * @param str 待判断的字符串
     * @return true 如果字符串为 null、空字符串，或仅包含空白字符（空格、制表符、换行、回车等）
     */
    public static boolean isBlank(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空白
     *
     * @param str 待判断的字符串
     * @return true 如果字符串不为 null、空字符串，且不仅包含空白字符（空格、制表符、换行、回车等）
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
