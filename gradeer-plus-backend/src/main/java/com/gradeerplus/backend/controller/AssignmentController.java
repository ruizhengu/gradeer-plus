package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.repository.AssignmentRepository;
import com.gradeerplus.backend.service.AssignmentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    @Resource
    private AssignmentService assignmentService;

    @GetMapping
    public List<Assignment> listAll() throws Exception {
        return assignmentService.listAll();
    }

    @GetMapping("/check")
    public String getCheckById(@RequestParam int id) throws Exception {
        return assignmentService.findCheckById(id);
    }

    @PostMapping("/check")
//    public String updateCheckById(@RequestParam int id, @RequestHeader("Check") String check) {
    public String updateCheckById(@RequestParam int id, @RequestBody String check) {
        assignmentService.updateCheckById(id, check);
        return check;
    }
}
