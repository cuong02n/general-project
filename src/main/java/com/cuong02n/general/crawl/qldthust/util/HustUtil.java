package com.cuong02n.general.crawl.qldthust.util;

public class HustUtil {
    public static int getYearAdmission(String studentId) {
        return Integer.parseInt(studentId.substring(0, 4));
    }

    /**
     * @param index > 1
     * @return year + (0000+index)
     */
    public static String getStudentId(int year, int index) {
        if (year == 2024) return String.valueOf(year * 100000 + index);
        return String.valueOf(year * 10000 + index);
    }
}
