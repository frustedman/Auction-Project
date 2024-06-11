package com.example.demo.chat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import com.example.demo.chat.domain.ChatMessage;
import com.example.demo.chat.domain.ChatMessagePublisher;
import com.example.demo.chat.domain.ChatRoom;
import com.example.demo.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public List<Object> getChatMessages() {
        log.info("messages={}",chatRoomService.findAllChatRooms());
        return chatRoomService.findAllChatRooms();
    }

    @GetMapping("/{roomId}")
    public String enterRoom(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "chat/chatRoom";
    }

    @GetMapping
    public String rooms(Model model) {
        return "chat/rooms";
    }

    @PostMapping
    @ResponseBody
    public ChatRoom creatRoom(@RequestParam String name) {
        return chatRoomService.createChatRoom(name);
    }

    @GetMapping("/{roomId}/messages")
    @ResponseBody
    public List<ChatMessage> getChatMessages(@PathVariable String roomId) {
        log.info("roomId={}",roomId);
        return chatRoomService.getAllChatMessages(roomId);
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/sub/messages/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, @Payload ChatMessage message) {
    	message.setTimestamp(new Date());
        message.setRoomId(roomId);
        chatMessagePublisher.publish(message);
        return message;
    }

}
