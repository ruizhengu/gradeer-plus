package com.gradeerplus.backend.controller;

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
//        System.out.println("submission controller " + decodedPath);
        MessageProperties props = new MessageProperties();
        props.setReplyTo("return-submission");
        props.setCorrelationId(UUID.randomUUID().toString());
        Message message = new Message(decodedPath.getBytes(StandardCharsets.UTF_8), props);
        Message response = rabbitTemplate.sendAndReceive("load_submission", message);
        String responseText = new String(response.getBody(), StandardCharsets.UTF_8);
//        System.out.println("Received response: " + responseText);
        return ResponseEntity.ok(responseText);
    }
}
