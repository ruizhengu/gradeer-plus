package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.service.impl.AssignmentServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Resource
    private AssignmentServiceImpl assignmentServiceImpl;

    @GetMapping
    public List<Assignment> listAll() throws Exception {
        return assignmentServiceImpl.listAll();
    }

    @GetMapping("/check")
    public String getCheckById(@RequestParam int id) throws Exception {
        return assignmentServiceImpl.findCheckById(id);
    }

    @PostMapping("/check")
    public String updateCheckById(@RequestParam int id, @RequestBody String check) {
        assignmentServiceImpl.updateCheckById(id, check);
        return check;
    }

    @PostMapping("/add")
    public void addAssignment(@RequestParam String module, @RequestParam int year, @RequestParam String name, @RequestParam String status, @RequestParam int progress) {
        assignmentServiceImpl.addAssignment(module, year, name, status, progress);
    }

    @PostMapping("/delete")
    public void deleteAssignment(@RequestParam int id) {
        assignmentServiceImpl.deleteAssignment(id);
    }
}
