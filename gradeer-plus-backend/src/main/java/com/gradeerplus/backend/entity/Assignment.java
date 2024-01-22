package com.gradeerplus.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ASSIGNMENTS")
public class Assignment {
    @Id
    @GeneratedValue
    private Integer id;

    private String module;

    private Integer year;

    private String name;

    private String status;

    private Integer progress;

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

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", module='" + module + '\'' +
                ", year='" + year + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", progress=" + progress +
                '}';
    }
}
