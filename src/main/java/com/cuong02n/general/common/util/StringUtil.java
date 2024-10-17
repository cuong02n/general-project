package com.cuong02n.general.common.util;

public class StringUtil {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String randomHexString(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = HEX_CHAR[(int) (Math.random() * 16)];
        }
        return new String(chars);
    }
}
