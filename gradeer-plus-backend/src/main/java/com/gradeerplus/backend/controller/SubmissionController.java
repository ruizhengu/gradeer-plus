package com.gradeerplus.backend.controller;

import com.gradeerplus.backend.entity.Assignment;
import com.gradeerplus.backend.entity.Submission;
import com.gradeerplus.backend.service.impl.SubmissionServiceImpl;
import com.rabbitmq.client.DeliverCallback;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/submissions")
@Component
public class SubmissionController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private SubmissionServiceImpl submissionServiceImpl;

    @GetMapping()
    public List<Submission> getAllSubmissionByAssignment(@RequestParam int assignment_id) throws Exception {
        return submissionServiceImpl.fetchAllSubmissionByAssignment(assignment_id);
    }

    @GetMapping("/code")
    public String getCodeById(@RequestParam int id) throws Exception {
        return submissionServiceImpl.getCodeById(id);
    }

    @GetMapping("/checks")
    public String getAssignmentChecksById(@RequestParam int id) throws Exception {
        return submissionServiceImpl.getAssignmentChecksById(id);
    }

    @PostMapping("/loadPath")
    public ResponseEntity<String> loadSubmissionPath(@RequestBody String path) throws Exception {
        String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);
        MessageProperties props = new MessageProperties();
        props.setReplyTo("load-submission-receive");
        props.setCorrelationId(UUID.randomUUID().toString());
        Message message = new Message(decodedPath.getBytes(StandardCharsets.UTF_8), props);
        Message response = rabbitTemplate.sendAndReceive("load-submission-send", message);
        String responseText = new String(response.getBody(), StandardCharsets.UTF_8);
//        System.out.println("Received response: " + responseText);
        return ResponseEntity.ok(responseText);
    }

    @PostMapping("/mergedSolution")
    public ResponseEntity<String> getMergedSolution(@RequestBody String student) throws Exception {
        MessageProperties props = new MessageProperties();
        props.setReplyTo("merged-solution-receive");
        props.setCorrelationId(UUID.randomUUID().toString());
        Message message = new Message(student.getBytes(StandardCharsets.UTF_8), props);
        Message response = rabbitTemplate.sendAndReceive("merged-solution-send", message);
        String responseText = new String(response.getBody(), StandardCharsets.UTF_8);
        return ResponseEntity.ok(responseText);
    }

    @PostMapping("/save")
    public void saveSubmission(
            @RequestParam String student,
            @RequestParam Integer assignment_id,
            @RequestParam Integer grade,
            @RequestParam String status,
            @RequestParam String marker
    ) throws Exception {
        submissionServiceImpl.storeSubmission(student, assignment_id, grade, status, marker);
    }
}
