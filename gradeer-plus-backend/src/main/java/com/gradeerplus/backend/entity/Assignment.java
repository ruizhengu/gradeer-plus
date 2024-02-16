package com.gradeerplus.backend.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

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

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private String check;

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
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
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
