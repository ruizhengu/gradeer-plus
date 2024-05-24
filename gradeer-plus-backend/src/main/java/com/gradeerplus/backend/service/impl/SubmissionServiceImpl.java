package com.gradeerplus.backend.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gradeerplus.backend.entity.Submission;
import com.gradeerplus.backend.repository.SubmissionRepository;
import com.gradeerplus.backend.service.SubmissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("submissionService")
public class SubmissionServiceImpl implements SubmissionService {
    @Resource
    private SubmissionRepository submissionRepository;

    public List<Submission> fetchAllSubmissionByAssignment(Integer assignment_id) {
        return submissionRepository.fetchAllSubmissionByAssignment(assignment_id);
    }

    @Override
    public List<Submission> fetchSubmissionWithAssignmentAndMarker(Integer assignment_id, String marker) {
        return submissionRepository.fetchSubmissionWithAssignmentAndMarker(assignment_id, marker);
    }

//    @Override
//    public String getCodeById(Integer id) {
//        return submissionRepository.getCodeById(id);
//    }

    @Override
    public String getAssignmentChecksById(Integer id) {
        return submissionRepository.getAssignmentChecksById(id);
    }

    @Override
    public void storeSubmission(String student, Integer assignment_id, Integer grade, String status, String marker) {
        submissionRepository.storeSubmission(student, assignment_id, grade, status, marker);
    }

    @Override
    public void storeGrade(int submission_id, String checkResults) {
        double grade = generateGrade(checkResults);
        submissionRepository.storeGrade(submission_id, grade);
    }

    @Override
    public void storeFeedback(int submission_id, String checkResults) {
        String feedback = generateFeedback(checkResults);
        submissionRepository.storeFeedback(submission_id, feedback);
    }

    public double generateGrade(String checkResults) {
        double totalWeight = 0;
        double weightedScoreSum = 0;
        JsonArray checkResultsJson = JsonParser.parseString(checkResults).getAsJsonArray();
        for (JsonElement element : checkResultsJson) {
            JsonObject checkResult = element.getAsJsonObject();
            double weight = checkResult.get("weight").getAsDouble();
            double weightedScore = calculateWeightedScore(checkResult);
            totalWeight += weight;
            weightedScoreSum += weightedScore;
        }
        return (100 * weightedScoreSum) / totalWeight;
    }

    public String generateFeedback(String checkResults) {
        StringBuilder sb = new StringBuilder();
        JsonArray checkResultsJson = JsonParser.parseString(checkResults).getAsJsonArray();
        for (JsonElement element : checkResultsJson) {
            JsonObject checkResult = element.getAsJsonObject();
            String feedback = "";
            double bestScore = -1;
            double unweightedScore = getUnweightedScore(checkResult);
            JsonArray feedbackValues = checkResult.get("feedbackValues").getAsJsonArray();
            for (JsonElement feedbackValue : feedbackValues) {
                JsonObject feedbackObject = feedbackValue.getAsJsonObject();
                double score = feedbackObject.get("score").getAsDouble();
                if (score <= unweightedScore && score > bestScore) {
                    bestScore = score;
                    feedback = feedbackObject.get("feedback").getAsString();
                }
            }
            sb.append(feedback).append("\n");
        }
        return sb.toString();
    }

    public double calculateWeightedScore(JsonObject checkResult) {
        double unweightedScore = getUnweightedScore(checkResult);
        double weight = checkResult.get("weight").getAsDouble();
        return unweightedScore * weight;
    }

    public double getUnweightedScore(JsonObject checkResult) {
        // TODO does not cover binary options (could modify the option in the frontend e.g., slide to ratio button)
        double unweightedScore = 1.0;
        int result = checkResult.get("result").getAsInt();
        double weight = checkResult.get("weight").getAsDouble();
        int maxRange = checkResult.get("maxRange").getAsInt();

        if (weight > 0) {
            unweightedScore = (double) result / maxRange;
            if (maxRange != 10) {
                unweightedScore = ((double) result * maxRange / 10) / maxRange;
            }
        }
        return unweightedScore;
    }
}
