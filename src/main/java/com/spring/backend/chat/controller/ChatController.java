package com.spring.backend.chat.controller;


import com.spring.backend.chat.model.documents.Message;
import com.spring.backend.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private String[] colors = {"red","green","yellow","black"};

    // Para publicar se debe apuntar a app/message....
    // Para recibir Anotacion SendTo
    // Indicaremos la respuesta a todos
    // los clientes subscriptos a este metodo.
    // El primer nombre Chat se definio en Broker del config
    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message recivedMessage(Message message){
        message.setDate(new Date().getTime());

        if(message.getType().equals("NEW_USER")){
            message.setColor(colors[new Random().nextInt(colors.length)]);
            message.setText("Started Session");
        } else {
            chatService.save(message);
        }

        return message;
    }
    @MessageMapping("/writing")
    @SendTo("/chat/writing")
    public String isWriting(String username){
        return username.concat(" is writing...");
    }


    //Necesitamos un template para poder enviar un identificador, y que la
    //subscripcion de los clientes sea unica, para hacer esto sacamos el send to
    @MessageMapping("/history")
    public void getHistory(String userId){
        simpMessagingTemplate.convertAndSend("/chat/history/" + userId, chatService.getLastMessage());
    }



}
