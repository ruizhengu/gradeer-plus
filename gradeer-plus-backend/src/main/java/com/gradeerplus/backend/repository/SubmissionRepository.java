package com.gradeerplus.backend.repository;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer>, JpaSpecificationExecutor<Submission> {
    @Query(value = "SELECT * FROM submissions WHERE assignment_id = :assignment_id", nativeQuery = true)
    List<Submission> getAllSubmissionByAssignment(@Param("assignment_id") Integer assignment_id);

}
