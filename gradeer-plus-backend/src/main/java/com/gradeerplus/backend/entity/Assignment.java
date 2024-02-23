package com.gradeerplus.backend.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Set;

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "module")
    private String module;

    @Column(name = "year")
    private Integer year;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "progress")
    private Integer progress;

    @Type(JsonType.class)
    @Column(name = "check_data", columnDefinition = "jsonb")
    private String check_data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getCheck() {
        return check_data;
    }

    public void setCheck(String check) {
        this.check_data = check;
    }
}
