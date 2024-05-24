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
    public String processCheckResults(String checkResults) {
        JsonArray checkResultsJson = JsonParser.parseString(checkResults).getAsJsonArray();
        JsonArray resultsArray = new JsonArray();
        for (JsonElement element : checkResultsJson) {
            JsonObject checkResult = element.getAsJsonObject();
            String checkIdentifier = checkResult.get("type").getAsString() + "_" + checkResult.get("name").getAsString();
            double unweightedScore = getUnweightedScore(checkResult);
            String feedback = getFeedback(unweightedScore, checkResult);

            JsonObject resultObject = new JsonObject();
            resultObject.addProperty("checkIdentifier", checkIdentifier);
            resultObject.addProperty("unweightedScore", "unweightedScore");
            resultObject.addProperty("feedback", feedback);
            resultsArray.add(resultObject);
        }
        return resultsArray.toString();
    }

    @Override
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

    public double calculateWeightedScore(JsonObject checkResult) {
        double unweightedScore = getUnweightedScore(checkResult);
        double weight = checkResult.get("weight").getAsDouble();
        return unweightedScore * weight;
    }

//    public double getTotalWeight(JsonArray checkResults) {
//        double totalWeight = 0;
//        for (JsonElement element : checkResults) {
//            JsonObject checkResult = element.getAsJsonObject();
//            double weight = checkResult.get("weight").getAsDouble();
//            totalWeight += weight;
//        }
//        return totalWeight;
//    }

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

    public String getFeedback(double unweightedScore, JsonObject checkResult) {
        JsonArray feedbackValues = checkResult.get("feedbackValues").getAsJsonArray();
        String feedback = "";
        double bestScore = -1;
        for (JsonElement element : feedbackValues) {
            JsonObject feedbackObject = element.getAsJsonObject();
            double score = feedbackObject.get("score").getAsDouble();
            if (score <= unweightedScore && score > bestScore) {
                bestScore = score;
                feedback = feedbackObject.get("feedback").getAsString();
            }
        }
        return feedback;
    }
}
