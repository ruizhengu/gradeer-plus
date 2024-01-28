package com.gradeerplus.backend.repository;

import com.gradeerplus.backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer>, JpaSpecificationExecutor<Assignment> {
    @Query(value = "SELECT * FROM ASSIGNMENTS", nativeQuery = true)
    List<Assignment> getAllAssignments();
}
