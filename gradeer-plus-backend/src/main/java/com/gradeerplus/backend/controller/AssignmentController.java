package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.service.AssignmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Resource
    private AssignmentService assignmentService;

    @GetMapping
    public List<Assignment> listAll() throws Exception {
        return assignmentService.listAll();
    }
}
