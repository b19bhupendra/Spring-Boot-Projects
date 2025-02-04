package com.app.chatApp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.chatApp.models.ChatMessage;

/*
 * This Controller will handle all the incoming messages that is being send from the frontend
 * And it is going to add it or broadcast it to all the clients.
 */
@Controller
public class ChatController {
	
	/*
	 * This will be the end-point Handling the messages 
	 * @Annotations : MessageMapping - This annotation maps websocket message to the destination 
	 * and here is my destination is /sendMessage so endPoint will be /app/sendMessage
	 * SendTo : This annotation tells spring to broadcast the return value of sendMessage method to all the client who are subscribed to this particular topic 
	 * app/sendMessage : When any of the message received on this particular end-point it get broadcasted to all the other 
	 * connected clients on to this particular topic -> /topic/messages  
	 * So whoever want to receive the msg that has to subscribed to this particular topic
	 *  
	 *  
	*/
	@MessageMapping("/sendMessage")
	@SendTo("/topic/messages")
	public ChatMessage sendMessage(ChatMessage message) {
		return message;
	}
	
	
	@GetMapping("chat")
	public String chat() {
		return "chat";
		
	}
	
}






