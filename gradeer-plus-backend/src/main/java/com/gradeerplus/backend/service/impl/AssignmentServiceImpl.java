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

    public List<Assignment> fetchAllAssignments() {
        return assignmentRepository.fetchAllAssignments();
    }

    public Assignment getAssignmentById(Integer id) {
        return null;
    }

    public String getCheckById(Integer id) {
        return assignmentRepository.getCheckById(id);
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
