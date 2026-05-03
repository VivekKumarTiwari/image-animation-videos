/*
package com.generate.animation.videos.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AICaptionService {


    @Autowired
    private  ChatClient chatClient;

    public String generateCaption(String fileName) {

        String prompt = "Generate a short caption for image: " + fileName;

        return chatClient.prompt(prompt).call().content();
    }
}*/
