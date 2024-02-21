package com.gradeerplus.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SUBMISSIONS")
public class Submission {
    @Id
    @GeneratedValue
    private Long id;

    private String student;
    @OneToOne(fetch = FetchType.LAZY)
    private Assignment assignment;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
