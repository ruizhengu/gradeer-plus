package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Submission;
import com.gradeerplus.backend.service.impl.SubmissionServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SimpMessagingTemplate template;
    @Resource
    private SubmissionServiceImpl submissionServiceImpl;

    @GetMapping()
    public List<Submission> getAllSubmissionByAssignment(@RequestParam int assignment_id) throws Exception {
        return submissionServiceImpl.fetchAllSubmissionByAssignment(assignment_id);
    }

    @GetMapping("/code")
    public String getCodeById(@RequestParam int id) throws Exception {
        return submissionServiceImpl.getCodeById(id);
    }

    @GetMapping("/checks")
    public String getAssignmentChecksById(@RequestParam int id) throws Exception {
        return submissionServiceImpl.getAssignmentChecksById(id);
    }

    @PostMapping("/loadPath")
//    @MessageMapping("/sendPath")
//    @SendTo("/topic/paths")
    public ResponseEntity<String> loadSubmissionPath(@RequestBody String path) throws Exception {
        System.out.println("submission controller " +  path);
        template.convertAndSend("/topic/paths", path);
        return ResponseEntity.ok("Path: " + path);
    }
}
