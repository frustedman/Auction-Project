package com.example.demo.chat.controller;

import com.example.demo.chat.domain.ChatMessage;
import com.example.demo.chat.domain.ChatMessagePublisher;
import com.example.demo.chat.domain.ChatRoom;
import com.example.demo.chat.repository.RedisMessageRepository;
import com.example.demo.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.*;

@Controller
@RequestMapping("/auth/rooms")
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatMessagePublisher chatMessagePublisher;
    private final ChatRoomService chatRoomService;
    private final RedisMessageRepository redisMessageRepository;

    @GetMapping("/load")
    @ResponseBody
    public Set<Object> getChatRooms(String name) {
//        log.info("rooms={}", chatRoomService.findAllChatRooms());
//        return chatRoomService.findAllChatRooms();
        log.info("name: {}", name);
        log.info("setobject={}", chatRoomService.findByName(name));
        Set<Object> byName = chatRoomService.findByName(name);
        log.info("size={}", byName.size());
        byName.forEach(System.out::println);
        return byName;
    }
    @GetMapping(params = {"seller", "buyer"})
    public String rooms(@RequestParam String seller, @RequestParam String buyer,Model model) {
//    	 Set<Object> byName = chatRoomService.findByName(seller);
    		 chatRoomService.createChatRoom(buyer, seller);
//        if (byName != null) {
//            seller="nope!!";
//        }
        log.info("seller={}", seller);
        model.addAttribute("seller", seller);
        return "chat/rooms";
    }

    @RequestMapping
    public String rooms() {
        return "chat/rooms";
    }

    @PostMapping("/create")
    @ResponseBody
    public ChatRoom createRoom(String buyer, String seller) {
        log.info("buyer={}", buyer);
        log.info("seller={}", seller);
        return chatRoomService.createChatRoom(buyer, seller);
    }

    @GetMapping("/{roomId}/messages")
    @ResponseBody
    public Set<Object> getChatMessages(@PathVariable String roomId) {
        log.info("roomId={}", roomId);
        return chatRoomService.getAllChatMessages(roomId);
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/sub/messages/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, @Payload ChatMessage message) {
        message.setRoomId(roomId);
        message.updateTimestamp();
        chatRoomService.updateChatroom(roomId);
        if(chatRoomService.getChatroom(roomId)) {
        	message.setRead(true);
        }
        chatMessagePublisher.publish(message);
        return message;
    }
    
    
    @MessageMapping("/chatcheck/{roomId}")
    @SendTo("/sub/messages/{roomId}")
    public ChatMessage sendMcheck(@DestinationVariable String roomId, @Payload ChatMessage check) {
        check.setRoomId(roomId);
        if(check.isRead()) {
        	check.updateTimestamp();
            chatRoomService.updateMen(roomId);
            chatMessagePublisher.publish(check);
        }else {
        	chatRoomService.discountMen(roomId);
        }
        return check;
    }
    
    
    //d
    @MessageMapping("/chat/image/{roomId}")
    @SendTo("/sub/messages/{roomId}")
    public ChatMessage handleImageUpload(@DestinationVariable String roomId, @Payload ChatMessage message) {
        message.setRoomId(roomId);
        message.updateTimestamp();
        chatRoomService.updateChatroom(roomId);
        chatMessagePublisher.publish(message);
        return message;
    }

    @MessageMapping("/chat/rooms/{roomId}")
    @SendTo("/sub/rooms")
    public String handleRooms(@DestinationVariable String roomId) {
        chatRoomService.updateChatroom(roomId);
        log.info("message={}", "ok");
        return "OK";
    }

    @GetMapping("/lastMessage")
    @ResponseBody
    public Map<String,Object> lastMessage(String roomId) {
        log.debug("roomId={}", roomId);
        Set<Object> lastMessage = redisMessageRepository.getLastMessage(roomId);
        log.info("lastMessage={}", lastMessage);
        Map<String, Object> content = new HashMap<>();
        content.put("content",lastMessage);
        return content;
    }

}
