package com.example.demo.chat.service;

import com.example.demo.chat.domain.ChatMessage;
import com.example.demo.chat.domain.ChatRoom;
import com.example.demo.chat.repository.RedisChatRoomRepository;
import com.example.demo.chat.repository.RedisMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisChatRoomRepository redisChatRoomRepository;
    private final RedisMessageRepository messageRepository;

    public ChatRoom createChatRoom(String name) {
        return redisChatRoomRepository.save(name);
    }

    public List<Object> findAllChatRooms() {
        return redisChatRoomRepository.findAll();
    }

    public ChatRoom findByName(String name) {
        return redisChatRoomRepository.findRoomById(name);
    }

    public List<ChatMessage> getAllChatMessages(String roomId) {
        List<Object> messages = messageRepository.getMessagesByRoomId(roomId);
        List<ChatMessage> chatMessages = new ArrayList<>();
        log.info("messages: {}", messages);
        if (messages != null) {
            for (Object message : new ArrayList<>(messages)) { // 안전하게 복사본을 반복합니다.
                if (message instanceof ChatMessage) {
                    chatMessages.add((ChatMessage) message);
                }
            }
        }
        return chatMessages;
    }
}
