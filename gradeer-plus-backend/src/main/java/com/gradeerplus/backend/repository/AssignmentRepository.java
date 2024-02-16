package com.gradeerplus.backend.repository;

import com.gradeerplus.backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer>, JpaSpecificationExecutor<Assignment> {
    @Query(value = "SELECT * FROM ASSIGNMENTS", nativeQuery = true)
    List<Assignment> getAllAssignments();

    @Query(value = "SELECT \"check\" FROM ASSIGNMENTS WHERE id = :id", nativeQuery = true)
    String findCheckById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ASSIGNMENTS SET \"check\" = :check WHERE id = :id", nativeQuery = true)
    void updateCheckById(@Param("id") Integer id, @Param("check") String check);
}
