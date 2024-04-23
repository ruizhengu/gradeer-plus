package com.gradeerplus.backend.service.impl;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.repository.AssignmentRepository;
import com.gradeerplus.backend.service.AssignmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService {
    @Resource
    private AssignmentRepository assignmentRepository;

    public List<Assignment> listAll() {
        return assignmentRepository.getAllAssignments();
    }

    public Assignment findById(Integer id) {
        return null;
    }

    public String findCheckById(Integer id) {
        return assignmentRepository.findCheckById(id);
    }

    public void updateCheckById(Integer id, String check) {
        assignmentRepository.updateCheckById(id, check);
    }

    public void addAssignment(String module, Integer year, String name, Integer progress) {
        assignmentRepository.addAssignment(module, year, name, progress);
    }

    public void deleteAssignment(Integer id) {
        assignmentRepository.deleteAssignment(id);
    }
}
