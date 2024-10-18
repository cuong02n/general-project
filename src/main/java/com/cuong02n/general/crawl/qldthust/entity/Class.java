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

    @Column(name = "class_type")
    @Expose
    String classType;

    @Column(name = "courseId")
    @Expose
    String courseId;

    @Column(name = "course_name")
    @Expose
    String courseName;

    @Column(name = "class_note")
    @Expose
    String classNote;

    @Column(name = "list_teacher_name")
    @Expose
    String listTeacherName;

    @Column(name = "timetable")
    @Expose
    String timetable;

    @Column(name = "file_progress_grade")
    @Expose
    String fileProgressGrade;

    @Column(name = "file_final_grade")
    @Expose
    String fileFinalGrade;



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
