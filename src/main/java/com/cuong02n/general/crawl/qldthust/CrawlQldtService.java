package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.common.util.HttpUtil;
import com.cuong02n.general.common.util.JsonUtil;
import com.cuong02n.general.common.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class CrawlQldtService {
    static final String baseUrl = "https://qldt.hust.edu.vn";
    static final String urlPath = "/soicteducationstudent/data";
    static final HashMap<String, String> headers = new HashMap<>();

    static final String payloadPattern = "7|0|10|https://qldt.hust.edu.vn/soicteducationstudent/|5C6CDB13D0FD25B266F3C36FA7FF6ED9|com.soict.edu.core.client.DataService|getCourseMembers|java.lang.Long/4227064769|java.lang.String/2004016611|java.util.List|%s|java.util.Arrays$ArrayList/2507071751|%d|1|2|3|4|3|5|6|7|5|TXbrzIAAA|8|9|1|6|10|";

    static {
        headers.put("content-type", "text/x-gwt-rpc; charset=UTF-8");
    }

    public static String getStudentEducationData(String studentId, int semester) {
        String payload = String.format(payloadPattern, studentId, semester);
        headers.put("x-gwt-permutation", StringUtil.randomHexString(32));
        return HttpUtil.post(baseUrl + urlPath, headers, payload);
    }


    public static final Map<String, String> propertiesPathMap = new HashMap<>() {
        {
            put("absent", "a-a");
            put("classId", "i");
            put("classNote", "S-Z");
            put("classGrade", "O-a");
            put("fileProgressGrade", "S-ob-a");
            put("fileFinalGrade", "S-Q-a");
            put("progressGrade", "jb");
            put("finalGrade", "v");
            put("classType", "k");
            put("courseId", "o");
            put("courseName", "j");
            put("credit", "S-u");
            put("timetable", "S-j-a");

            put("studentName", "tb");
            put("studentId", "sb");
            put("semester", "nb");
            put("listTeacher", "S-Ob-a");
            put("classExam", "S-D-a");
            put("numberOfStudent", "S-Mb");
        }
    };

    /**
     *
     * @param dataJson: each dataJson is an object
     */
    public static void getAndSaveData(JsonObject dataJson) {

    }
    public static void readFileAndSaveData(String filePath){
        JsonObject fileObject = JsonUtil.loadJsonObject(new File(filePath));
        JsonArray dataArray = fileObject.getAsJsonArray("a");

    }

    public static JsonElement getElement(JsonObject object, String jsonPath) {
        String[] paths = jsonPath.split("-");
        for (int i = 0; i < paths.length - 1; i++) {
            object = object.getAsJsonObject(paths[i]);
        }
        return object.get(paths[paths.length - 1]);
    }


    public static void main(String[] args) {

    }

}
