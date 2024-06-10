package com.example.demo.chat.repository;

import com.example.demo.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RedisMessageRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveMessage(ChatMessage message) {
        redisTemplate.opsForList().rightPush("CHAT_MESSAGES_" + message.getRoomId(), message);

    }

    public List<Object> getMessagesByRoomId(String roomId) {
        return redisTemplate.opsForList().range("CHAT_MESSAGES_" + roomId, 0, -1);
    }
}
