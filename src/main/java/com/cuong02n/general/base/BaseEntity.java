package com.cuong02n.general.base;

import com.google.gson.annotations.Expose;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public class BaseEntity {
    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "createdTime", updatable = false)
    @Expose
    @Nullable
    Timestamp createdTime;

    @UpdateTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updatedTime")
    @Expose
    @Nullable
    Timestamp updatedTime;

}
