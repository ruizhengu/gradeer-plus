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

    public List<Submission> fetchAllSubmissionByAssignment(Integer assignment_id) {
        return submissionRepository.fetchAllSubmissionByAssignment(assignment_id);
    }

    @Override
    public List<Submission> fetchSubmissionWithAssignmentAndMarker(Integer assignment_id, String marker) {
        return submissionRepository.fetchSubmissionWithAssignmentAndMarker(assignment_id, marker);
    }

    @Override
    public String getCodeById(Integer id) {
        return submissionRepository.getCodeById(id);
    }

    @Override
    public String getAssignmentChecksById(Integer id) {
        return submissionRepository.getAssignmentChecksById(id);
    }

    @Override
    public void storeSubmission(String student, Integer assignment_id, Integer grade, String status, String marker) {
        submissionRepository.storeSubmission(student, assignment_id, grade, status, marker);
    }
}
