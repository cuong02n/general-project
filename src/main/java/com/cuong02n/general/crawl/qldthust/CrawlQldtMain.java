package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.common.util.JsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.cuong02n.general.common.util.JavascriptUtil.runConvertGwtScript;

public class CrawlQldtMain {
    /**
     * if student number is 10000, then number of thread created is 10000/STUDENT_PER_THREAD (+1).
     */
    static final int STUDENT_PER_THREAD = 5000;
    static String baseDirectorySavedData = "output/";
    static String startStopFilePath = "src/main/resources/start_stop_index_crawl_student.json";

    /**
     * @param studentIdEnd: Exclude
     */
    public static void crawl(int semester, int studentIdStart, int studentIdEnd) throws IOException {
        int continuous = 0;
        for (int studentId = studentIdStart; studentId < studentIdEnd; studentId++) {
            String data = CrawlQldtService.getStudentEducationData(String.valueOf(studentId), semester);
            if (data.length() > 100) {
                File f = new File(baseDirectorySavedData + semester + "/" + studentId + ".json");
                Files.write(f.toPath(), data.getBytes());
                runConvertGwtScript(f.getAbsolutePath());
                continuous = 0;
            } else {
                continuous++;
                if (continuous >= 50) {
                    System.out.println("The thread stopped at student Id: " + studentId + " and semester " + semester + " due to 50 fails ");
                    break;
                }
            }
        }
    }


    public static void crawl(int semester) throws IOException{
        JsonObject startStop = JsonUtil.loadJsonObject(new File(startStopFilePath));
        JsonArray startStopSemester = startStop.getAsJsonArray(String.valueOf(semester));

        for (int i = 0; i < startStopSemester.size(); i += 2) {
            int startId = startStopSemester.get(i).getAsInt();
            int stopId = startStopSemester.get(i + 1).getAsInt();
            crawl(semester,startId,stopId);
        }
    }
}
