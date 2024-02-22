package com.gradeerplus.backend.service.impl;

import com.gradeerplus.backend.entity.Submission;
import com.gradeerplus.backend.repository.SubmissionRepository;
import com.gradeerplus.backend.service.SubmissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("submissionService")
public class SubmissionServiceImpl implements SubmissionService {
    @Resource
    private SubmissionRepository submissionRepository;

    public List<Submission> getAllSubmissionByAssignment(Integer assignment_id) {
        return submissionRepository.getAllSubmissionByAssignment(assignment_id);
    }
}
