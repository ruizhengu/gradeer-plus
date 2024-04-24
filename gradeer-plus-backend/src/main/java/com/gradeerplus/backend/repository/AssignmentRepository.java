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
    @Query(value = "SELECT * FROM assignments", nativeQuery = true)
    List<Assignment> fetchAllAssignments();

    @Query(value = "SELECT check_data FROM assignments WHERE id = :id", nativeQuery = true)
    String getCheckById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE assignments SET check_data = :check WHERE id = :id", nativeQuery = true)
    void updateCheckById(@Param("id") Integer id, @Param("check") String check);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO assignments (module, year, name, progress) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void addAssignment(String module, Integer year, String name, Integer progress);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM assignments WHERE id = ?1", nativeQuery = true)
    void deleteAssignment(Integer id);
}
