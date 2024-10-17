package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.crawl.qldthust.util.HustUtil;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CrawlQldtMain {

    public static void crawl(int semester,int yearStudent,int indexStartStudent){

    }
    public static void crawl(int semester) throws Exception {
        for (int year = 2024; year >= 2020; year--) {
            int continuous = 0;
            for (int i = 1; i < 22_000; i++) {
                String studentId = HustUtil.getStudentId(year, i);
                String data = CrawlQldtService.getStudentEducationData(studentId, String.valueOf(semester));

                if (data.length() > 100) {
                    File f = new File("output/" + semester + "/" + studentId + ".json");
                    Files.write(f.toPath(), data.getBytes());
                }
                if (data.length() < 100) {
                    System.out.println("Data length of: "+data.length()+" is less than 100");
                    continuous++;
                    if (continuous == 100) {
                        System.out.println("Crawl semester " + semester + " finished at " + studentId + " after 100 fails");
                        break;
                    }
                } else {
                    continuous = 0;
                }
            }
        }
    }
}
