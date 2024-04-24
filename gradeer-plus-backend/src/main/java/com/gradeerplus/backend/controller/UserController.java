package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.User;
import com.gradeerplus.backend.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public List<User> listAll() throws Exception {
        return userServiceImpl.fetchAllUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestParam String name, @RequestParam String role) {
        userServiceImpl.addUser(name, role);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestParam Integer id) {
        userServiceImpl.deleteUser(id);
    }
}
