package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Assignment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


public class AssignmentController {
    @RequestMapping("/assignments")
    public ModelAndView list() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Assignment> assignmentList;
        return mav;
    }
}
