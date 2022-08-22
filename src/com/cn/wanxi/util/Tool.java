package com.cn.wanxi.util;

import com.mysql.cj.util.StringUtils;

import java.math.BigDecimal;

public class Tool {
    /**
     * 将null转换为“”
     *
     * @param value
     * @return
     */
    public static String nullToString(String value) {
        return value == null ? "" : value;
//        return String.(value);
    }

    /**
     * 将null转换为默认日期
     *
     * @param value
     * @return
     */
    public static String nullToDate(String value) {
        return value == null ? "1970-01-01" : value;
    }

    public static Integer strToInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return -1;
        }
    }
    public static Integer strToPage(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return 1;
        }
    }
    public static Integer strToLimit(String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return 10;
        }
    }

    public static BigDecimal nullToBigDecimal(String value) {
        try {
            return BigDecimal.valueOf(Long.parseLong(value));
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }
}
