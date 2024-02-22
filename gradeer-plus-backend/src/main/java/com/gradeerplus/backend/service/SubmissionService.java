package com.gradeerplus.backend.service;

import com.gradeerplus.backend.entity.Submission;

import java.util.List;

public interface SubmissionService {
    List<Submission> getAllSubmissionByAssignment(Integer assignment_id);
}
