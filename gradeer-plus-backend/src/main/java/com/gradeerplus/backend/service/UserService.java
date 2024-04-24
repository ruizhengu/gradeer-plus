package com.gradeerplus.backend.service;

import com.gradeerplus.backend.entity.User;

import java.util.List;

public interface UserService {
    List<User> fetchAllUsers();

    void addUser(String name, String role);

    void deleteUser(Integer id);
}
