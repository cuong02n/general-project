package com.cuong02n.general.crawl.qldthust.util;

import com.cuong02n.general.common.util.GsonUtil;
import com.cuong02n.general.crawl.qldthust.entity.StudentEducationData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static com.cuong02n.general.crawl.qldthust.entity.StudentEducationData.Timetable.afternoonTime;
import static com.cuong02n.general.crawl.qldthust.entity.StudentEducationData.Timetable.morningTime;

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

    public static List<StudentEducationData.Timetable> getTimetableFromServer(JsonArray arrayData) {
        List<StudentEducationData.Timetable> list = new ArrayList<>();
        for (JsonElement jsonElement : arrayData) {
            String element = jsonElement.getAsString();
            if (isTimetable(element)) {
                list.add(getTimetable(element));
            }
        }
        return list;
    }

    public static StudentEducationData.Timetable getTimetable(String timetableString) {
        JsonObject jsonObject = GsonUtil.toJsonObject(timetableString);
        String place = jsonObject.get("place").getAsString();
        int fromIndex = jsonObject.get("from").getAsInt();
        int toIndex = jsonObject.get("to").getAsInt();
        int dayTime = jsonObject.get("dayTime").getAsInt();
        int dayOfWeek = jsonObject.get("day").getAsInt();
        String week = jsonObject.get("week").getAsString();
        int from = fromIndex;
        int to=toIndex;
        if (fromIndex < 20 && toIndex < 20) {
            if (dayTime == 1) {
                from = morningTime.get("start" + fromIndex);
                to = morningTime.get("end" + toIndex);
            } else {
                from = afternoonTime.get("start" + fromIndex);
                to = afternoonTime.get("end" + toIndex);
            }
        }
        return new StudentEducationData.Timetable(place, from, to, dayOfWeek, week); // TODO
    }

    public static boolean isTimetable(String data) {
        if (!data.startsWith("{")) return false;
        JsonObject jsonData = GsonUtil.toJsonObject(data);
        return jsonData.has("day")
                && jsonData.has("from")
                && jsonData.has("to")
                && jsonData.has("week")
                && jsonData.has("place");
    }
}
