package com.cuong02n.general.crawl.qldthust.entity;

import com.cuong02n.general.base.BaseEntity;
import com.cuong02n.general.common.util.GsonUtil;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Description;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "student_education_data_v1")
public class StudentEducationData extends BaseEntity {
    @EmbeddedId
    @Expose
    Key key;

    String studentName;

    /**
     * Present as JsonArray, each Element is JsonObject
     */
    @Column(length = 65535)
    String timetable;

    public void setTimetable(List<Timetable> timetable) {
        this.timetable = GsonUtil.toString(timetable);
    }

    @Embeddable
    @Data
    @AllArgsConstructor
    public static class Key implements Serializable {
        @Column(name = "student_id")
        @Expose
        String studentId;
        @Column(name = "semester")
        @Expose
        String semester;

        protected Key(){}
    }

    @AllArgsConstructor
    public static class Timetable {
        final String place;
        final int from;
        final int to;

        final int dayOfWeek;
        final String week;


        public static Map<String, Integer> morningTime = new HashMap<>() {{
            put("start1", 645);
            put("end1", 730);
            put("start2", 730);
            put("end2", 815);
            put("start3", 825);
            put("end3", 910);
            put("start4", 920);
            put("end4", 1005);
            put("start5", 1015);
            put("end5", 1100);
            put("start6", 1100);
            put("end6", 1145);
        }};

        public static Map<String, Integer> afternoonTime = new HashMap<>() {{
            put("start1", 1230);
            put("end1", 1315);
            put("start2", 1315);
            put("end2", 1400);
            put("start3", 1410);
            put("end3", 1455);
            put("start4", 1505);
            put("end4", 1550);
            put("start5", 1600);
            put("end5", 1645);
            put("start6", 1645);
            put("end6", 1730);
        }};
    }


}
