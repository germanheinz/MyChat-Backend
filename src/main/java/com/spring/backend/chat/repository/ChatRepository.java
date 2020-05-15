package com.spring.backend.chat.repository;

import com.spring.backend.chat.model.documents.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Message, String> {

    public List<Message> findFirst10ByOrderByDateDesc();

}
