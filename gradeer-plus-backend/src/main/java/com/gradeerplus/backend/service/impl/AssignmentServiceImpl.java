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

    @Override
    public List<Assignment> listAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment findById(Integer id) {
        return null;
    }
}
