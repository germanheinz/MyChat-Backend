package com.spring.backend.chat.service;

import com.spring.backend.chat.model.documents.Message;
import com.spring.backend.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Message> getLastMessage() {
        return chatRepository.findFirst10ByOrderByDateDesc();
    }

    @Override
    public Message save(Message message) {
        return chatRepository.save(message);
    }
}
