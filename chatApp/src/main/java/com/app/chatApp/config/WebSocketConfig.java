package com.app.chatApp.config;

import java.net.http.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/*
	 * We have Override the method of WebSocketMessageBrokerConfigurer to use our configuration
	 * And then we will defining our endpoint for websocket connections "/chat" using .addEndpoint to the parameter registry.
	 * We have used setAllowedOrigins as we want to entertain request from the url (frontend url) only and this is security feature to 
	 * prevent any unauthorized broadcast or messages coming into our application
	 * withSockJS :  This is going to add compatibility for clients that do not support websockets by using sockJs as the fallback and this 
	 * makes the app accessible to more users. 
	 * 
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat")
			.setAllowedOrigins("http://localhost:8080")
			.withSockJS();
	}

	/*
	 * This is going to define the message broker 
	 * 
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) { 
		/*Sets up message broker that can handle message for topics
		 * So whoever is subscribe to this "/topic" they will get the brodcast of whatever messages are being sent to here
		 * for example we have a chat-room /topic/chatroom1  whoever is in this chat-room or whoever is subscribed to this chatroom all will get message
		 * So this is a channel that we are creating over here.  
		 */
		registry.enableSimpleBroker("/topic");
		// expect message with /app/sendmessage (adding a line which will tell the server as to when it is suppose to 
		//expect a message for example server will process a message with a perticular prefix so for exmaple
		//if a client sends a message with prefix /app/ then server will know that it is suppose to process.
		/*
		 * And then we are telling the server if you get any message with this prefix process it.
		 */
		registry.setApplicationDestinationPrefixes("/app");
	}

}
