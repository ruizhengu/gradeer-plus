package com.gradeerplus.backend.repository;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer>, JpaSpecificationExecutor<Submission> {

    @Query(value = "SELECT * FROM submissions WHERE assignment_id = :assignment_id", nativeQuery = true)
    List<Submission> fetchAllSubmissionByAssignment(@Param("assignment_id") Integer assignment_id);

    @Query(value = "SELECT * FROM submissions WHERE assignment_id = :assignment_id AND marker = :marker", nativeQuery = true)
    List<Submission> fetchSubmissionWithAssignmentAndMarker(@Param("assignment_id") Integer assignment_id, @Param("marker") String marker);

//    @Query(value = "SELECT code from submissions WHERE id = :id", nativeQuery = true)
//    String getCodeById(@Param("id") Integer id);

    @Query(value = "SELECT a.check_data FROM assignments a JOIN submissions s ON a.id = s.assignment_id WHERE s.id = :id", nativeQuery = true)
    String getAssignmentChecksById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO submissions (student, assignment_id, grade, status, marker) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void storeSubmission(
            @Param("student") String student,
            @Param("assignment_id") Integer assignment_id,
            @Param("grade") Integer grade,
            @Param("status") String status,
            @Param("marker") String marker
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE submissions SET grade = :grade WHERE id = :submission_id", nativeQuery = true)
    void storeGrade(
            @Param("submission_id") int submission_id,
            @Param("grade") double grade
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE submissions SET feedback = :feedback WHERE id = :submission_id", nativeQuery = true)
    void storeFeedback(
            @Param("submission_id") int submission_id,
            @Param("feedback") String feedback
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE submissions SET status = :status WHERE id = :submission_id", nativeQuery = true)
    void updateStatus(
            @Param("submission_id") int submission_id,
            @Param("status") String status
    );
}
