package com.nyl.kafka.model;

public class Message {
    private String sender;
    private String content;

    // Constructors
    public Message() {}
    
    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    // Getters & Setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{sender='" + sender + "', content='" + content + "'}";
    }
}

