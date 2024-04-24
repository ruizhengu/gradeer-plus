package com.gradeerplus.backend.service;

import com.gradeerplus.backend.entity.Submission;

import java.util.List;

public interface SubmissionService {
    List<Submission> fetchAllSubmissionByAssignment(Integer assignment_id);

    String getCodeById(Integer id);

    String getAssignmentChecksById(Integer id);
}
