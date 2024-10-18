package com.cuong02n.general.crawl.qldthust.entity;

import com.cuong02n.general.base.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hust_student_register_class")
public class HustStudentClass extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "class_id", referencedColumnName = "class_id"),
            @JoinColumn(name = "semester", referencedColumnName = "semester")
    })
    Class cls;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "progress_grade", precision = 4, scale = 2)
    double progressGrade;

    @Column(name = "final_grade", precision = 4, scale = 2)
    double finalGrade;

    @Column(name = "absents")
    String absents;

    public int getAbsent() {
        return absents.split(",").length + 1;
    }



}
