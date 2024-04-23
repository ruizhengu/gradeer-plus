package com.gradeerplus.backend.service;

import com.gradeerplus.backend.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    List<Assignment> listAll();

    Assignment findById(Integer id);

    String findCheckById(Integer id);

    void updateCheckById(Integer id, String check);

    void addAssignment(String module, Integer year, String name, Integer progress);

    void deleteAssignment(Integer id);
}