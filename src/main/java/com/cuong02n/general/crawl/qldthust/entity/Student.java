package com.cuong02n.general.crawl.qldthust.entity;

import com.cuong02n.general.base.BaseEntity;
import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hust_student")
public class Student extends BaseEntity {
    @Id
    @Expose
    @Column(name = "student_id")
    public String studentId;

    @Expose
    @Column(name = "student_name")
    public String studentName;

    @Expose
    @Column(name = "dob")
    public Timestamp dob;

}
