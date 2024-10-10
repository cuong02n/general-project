package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.crawl.qldthust.util.HustUtil;

public class CrawlQldtMain {
    public static void main(String[] args) throws Exception{
        for (int year = 2018; year <= 2024; year++) {
            for (int semester = 1; semester <= 3; semester++) {
                for (int i = 1; i < 20_000; i++) {
                    String studentId = HustUtil.getStudentId(year, i);
                    CrawlQldtService.getStudentEducationData(studentId, String.valueOf(year * 10 + semester));


                }
            }
        }
    }
}
