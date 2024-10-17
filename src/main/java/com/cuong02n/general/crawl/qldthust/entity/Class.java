package com.cuong02n.general.crawl.qldthust.entity;

import com.cuong02n.general.base.BaseEntity;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "hust_class")
public class Class extends BaseEntity {
    @EmbeddedId
    @Expose
    ClassPK classPK;

    @Column(name = "courseId")
    @Expose
    String courseId;

    @Column(name = "course_name")
    @Expose
    String courseName;

    @Column(name = "teacher_name")
    @Expose
    String teacherName;

    @Column(name = "timetable")
    @Expose
    String timetable;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ClassPK {
        @Column(name = "class_id")
        @Expose
        private String classId;
        @Column(name = "semester")
        @Expose
        private int semester;
    }
}
