package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.common.util.GsonUtil;
import com.cuong02n.general.crawl.qldthust.entity.StudentEducationData;
import com.cuong02n.general.crawl.qldthust.repository.StudentEducationRepository;
import com.cuong02n.general.crawl.qldthust.util.HustUtil;
import com.google.gson.JsonArray;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CrawlQldtMain {
    public static void crawl(ConfigurableApplicationContext ctx) throws Exception {

        Map<Integer, Integer> start = new HashMap<>() {{
            put(20181, 20183702);
            put(20182,1);
            put(20183,1);
            put(20191,1);
            put(20192,1);
            put(20193,1);
            put(20201,1);
            put(20202,1);
            put(20203,1);
            put(20211,1);
            put(20212,1);
            put(20213,1);
            put(20221,1);
            put(20222,1);
            put(20223,1);
            put(20231,1);
            put(20232,1);
            put(20233,1);
            put(20241,1);
        }};
        StudentEducationRepository repository = ctx.getBean(StudentEducationRepository.class);
        for (int year = 2018; year <= 2024; year++) {
            for (int semesterYear = year; semesterYear <= 2024; semesterYear++) {
                for (int semesterIndex = 1; semesterIndex <= 3; semesterIndex++) {
                    int semester = semesterYear * 10 + semesterIndex;
                    int continuous = 0;
                    for (int i = start.get(semester); i < 20_000; i++) {
                        StudentEducationData educationData = new StudentEducationData();
                        String studentId = HustUtil.getStudentId(year, i);

                        educationData.setKey(new StudentEducationData.Key(studentId, String.valueOf(semester)));
                        System.out.println("Crawling student: " + studentId + ": " + semester);
                        String data = CrawlQldtService.getStudentEducationData(studentId, String.valueOf( semester));

                        JsonArray jsonData = GsonUtil.toJsonArray(data);
                        int dataSize = jsonData.get(jsonData.size() - 5).getAsInt();
                        if (dataSize > 0) {
                            File f = new File("output/" + semester + "/" + studentId + ".json");

                            Files.write(f.toPath(), data.getBytes());

                        }
                        if (dataSize == 0) {
                            continuous++;
                            if (continuous == 50) {
                                System.out.println("Crawl semester " + semester + " finished at " + studentId + " after 100 fails");
                                break;
                            }
                        } else {
                            continuous = 0;
                        }

                        educationData.setTimetable(HustUtil.getTimetableFromServer(jsonData.get(jsonData.size() - 3).getAsJsonArray()));
                        repository.save(educationData);
                    }
                }
            }
        }
    }

    public static void getListTimetable(JsonArray data) {

    }

    /**
     * {"classIdInfo":"","placeId":-1,"place":"san2","week":"25-32,34-42","date":-1,"dayTime":2,"day":5,"from":1400,"to":1500,"teacherId":-1,"teacherIds":[],"teacherNames":[],"lesson":"","weeks":[],"lessonType":"","status":1,"createdBy":-1,"classId":-1}
     */

}
