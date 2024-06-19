package com.example.demo.chat.repository;

import com.example.demo.chat.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class RedisMessageRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ZSetOperations<String, Object> zSetOperations;

    public void saveMessage(ChatMessage message) {
        long timestamp = Instant.now().toEpochMilli();
//        redisTemplate.opsForList().rightPush("CHAT_MESSAGES_" + message.getRoomId(), message);
        zSetOperations.add("CHAT_MESSAGE_" + message.getRoomId(), message, timestamp);

    }

    public Set<Object> getMessagesByRoomId(String roomId) {
//        return redisTemplate.opsForList().range("CHAT_MESSAGES_" + roomId, 0, -1);
        return zSetOperations.range("CHAT_MESSAGE_" + roomId, 0, -1);
    }
    public Set<Object> getLastMessage(String roomId) {
        return zSetOperations.range("CHAT_MESSAGE_" + roomId, -1, -1);
    }
}
