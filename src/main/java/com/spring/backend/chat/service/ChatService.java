package com.spring.backend.chat.service;

import com.spring.backend.chat.model.documents.Message;

import java.util.List;

public interface ChatService {
    public List<Message> getLastMessage();
    public Message save(Message message);
}
