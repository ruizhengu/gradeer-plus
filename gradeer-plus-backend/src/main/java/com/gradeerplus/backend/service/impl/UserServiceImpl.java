package com.gradeerplus.backend.service.impl;

import com.gradeerplus.backend.entity.User;
import com.gradeerplus.backend.repository.UserRepository;
import com.gradeerplus.backend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.getAllUsers();
    }

    public void addUser(String name, String role) {
        userRepository.addUser(name, role);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }
}
