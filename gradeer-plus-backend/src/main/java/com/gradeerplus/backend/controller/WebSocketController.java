package com.gradeerplus.backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/sendPath")
    @SendTo("/topic/paths")
    public String sendPath(String path) {
        System.out.println("socket " + path);
        return path;
    }
}
