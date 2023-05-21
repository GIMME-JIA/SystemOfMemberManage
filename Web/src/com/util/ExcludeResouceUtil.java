package com.util;

public class ExcludeResouceUtil {
    static String[] urls = {".js", ".css", ".ico", ".jpg", ".png"};

    public static boolean shouldExclude(String uri) {
        boolean flag = false;
        for (String str : urls) {
            if (uri.contains(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
