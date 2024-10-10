package com.cuong02n.general.crawl.qldthust.entity;

import com.cuong02n.general.base.BaseEntity;
import com.cuong02n.general.common.util.GsonUtil;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "student_education_data")
public class StudentEducationData extends BaseEntity {
    @EmbeddedId
    @Expose
    Key key;

    String studentName;

    /**
     * Present as jsonObject
     */

    String timetable;

    public Timetable getTimetable() {
        return Timetable.fromString(timetable);
    }

    @Embeddable
    @Data
    static class Key implements Serializable {
        @Column(name = "student_id")
        @Expose
        String studentId;
        @Column(name = "semester")
        @Expose
        String semester;
    }

    @AllArgsConstructor
    public static class Timetable {
        String place;
        String from;
        String to;

        String dayOfWeek;
        String week;

        public static Timetable fromString(String timetableString) {
            JsonObject jsonObject = GsonUtil.toJsonObject(timetableString);
            int from = jsonObject.get("from").getAsInt();

            return null; // TODO
        }
    }

    enum StartTime {
        String
    }


}
