package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Submission;
import com.gradeerplus.backend.service.impl.SubmissionServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {
    @Resource
    private SubmissionServiceImpl submissionServiceImpl;

    @GetMapping()
    public List<Submission> getAllSubmissionByAssignment(@RequestParam int assignment_id) throws Exception {
        return submissionServiceImpl.getAllSubmissionByAssignment(assignment_id);
    }
}
