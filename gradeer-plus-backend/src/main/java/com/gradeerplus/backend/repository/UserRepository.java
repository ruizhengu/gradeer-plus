package com.gradeerplus.backend.repository;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.entity.Submission;
import com.gradeerplus.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUsers();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users (name, role) VALUES (?1, ?2)", nativeQuery = true)
    void addUser(String name, String role);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = ?1", nativeQuery = true)
    void deleteUser(Integer id);
}
