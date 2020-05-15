package com.spring.backend.chat.controller;


import com.spring.backend.chat.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatController {

    // Para publicar se debe apuntar a app/message....
    // Para recibir Anotacion SendTo
    // Indicaremos la respuesta a todos
    // los clientes subscriptos a este metodo.
    // El primer nombre Chat se definio en Broker del config
    @MessageMapping("/message")
    @SendTo("/chat/message")
    public Message recivedMessage(Message message){
        message.setDate(new Date().getTime());
        message.setText("Recived by Broker: " + message.getText());

        return message;
    }

}
