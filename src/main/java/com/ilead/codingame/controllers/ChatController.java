package com.ilead.codingame.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ilead.codingame.DTO.ChatRequest;
import com.ilead.codingame.DTO.ChatResponse;

@RestController
public class ChatController {
    
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @GetMapping("/chat")
    public String chat(@RequestParam String context, @RequestParam String question, @RequestParam String reponse, @RequestParam String criteres) {
    	String prompt = "Dans le context "+ context + " ,noter sur 10 la reponse :" + reponse +" , pour la question : " + question + " ,avec les critere d'evaluation : " + criteres;
    	
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);
        
        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        
        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}
