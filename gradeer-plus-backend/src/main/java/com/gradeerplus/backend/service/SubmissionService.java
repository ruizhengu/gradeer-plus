package com.gradeerplus.backend.service;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.entity.Submission;

import java.util.List;

public interface SubmissionService {

    List<Submission> fetchAllSubmissionByAssignment(Integer assignment_id);

    List<Submission> fetchSubmissionWithAssignmentAndMarker(Integer assignment_id, String marker);

    String getCodeById(Integer id);

    String getAssignmentChecksById(Integer id);

    void storeSubmission(
            String student,
            Integer assignment_id,
            Integer grade,
            String status,
            String marker
    );
}
