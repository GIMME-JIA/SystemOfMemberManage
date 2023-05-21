package com.util;

public class StingUtil {
    public static boolean isNotBlank(String str) {
        return str != null && !"".equals(str.trim());
    }
}
