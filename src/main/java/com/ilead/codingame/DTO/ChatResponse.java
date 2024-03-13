package com.ilead.codingame.DTO;

import java.util.List;

import lombok.Data;
import lombok.Getter;

public class ChatResponse {

    public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	private List<Choice> choices;
    
    public static class Choice {

        private int index;
        private Message message;
        
        public int getIndex() {
    		return index;
    	}
        
        public Message getMessage() {
    		return message;
    	}
        
        
    }
}