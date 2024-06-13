package com.example.demo.chat.controller;

import com.example.demo.chat.domain.ChatMessage;
import com.example.demo.chat.domain.ChatMessagePublisher;
import com.example.demo.chat.domain.ChatRoom;
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

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/auth/rooms")
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatMessagePublisher chatMessagePublisher;
    private final ChatRoomService chatRoomService;

    @GetMapping("/load")
    @ResponseBody
    public Set<Object> getChatRooms(String name) {
//        log.info("rooms={}", chatRoomService.findAllChatRooms());
//        return chatRoomService.findAllChatRooms();
        log.info("setobject={}", chatRoomService.findByName(name).toString());
        Set<Object> byName = chatRoomService.findByName(name);
        log.info("size={}", byName.size());
        byName.forEach(System.out::println);
        return byName;
    }
    @GetMapping(params = {"seller"})
    public String rooms(@RequestParam String seller,Model model) {
        Set<Object> byName = chatRoomService.findByName(seller);
//        if (byName != null) {
//            seller="nope!!";
//        }
        log.info("seller={}", seller);
        model.addAttribute("seller", seller);
        return "chat/rooms";
    }

    @GetMapping
    public String rooms() {
        return "chat/rooms";
    }

    @PostMapping
    @ResponseBody
    public ChatRoom createRoom(String buyer, String seller) {
        return chatRoomService.createChatRoom(buyer, seller);
    }

    @GetMapping("/{roomId}/messages")
    @ResponseBody
    public List<ChatMessage> getChatMessages(@PathVariable String roomId) {
        log.info("roomId={}", roomId);
        return chatRoomService.getAllChatMessages(roomId);
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/sub/messages/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, @Payload ChatMessage message) {
        message.setRoomId(roomId);
        message.updateTimestamp();
        chatMessagePublisher.publish(message);
        return message;
    }
//d
    @MessageMapping("/chat/image/{roomId}")
    @SendTo("/sub/messages/{roomId}")
    public ChatMessage handleImageUpload(@DestinationVariable String roomId, @Payload ChatMessage message) {
        message.setRoomId(roomId);
        message.updateTimestamp();
        chatMessagePublisher.publish(message);
        return message;
    }
}
