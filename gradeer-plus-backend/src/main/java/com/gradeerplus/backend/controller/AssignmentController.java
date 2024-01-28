package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.service.AssignmentService;
import jakarta.annotation.Resource;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {
    @Resource
    private AssignmentService assignmentService;

    @RequestMapping("/list")
    public ModelAndView listAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Assignment> assignmentList = assignmentService.listAll();
        mav.addObject("assignmentList", assignmentList);
        mav.addObject("title", "Assignment List");
        mav.addObject("mainPage", "assignments/list");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }
}
