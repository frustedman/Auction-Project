package com.example.demo.chat.repository;

import com.example.demo.chat.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RedisChatRoomRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public ChatRoom save(String name){
        ChatRoom chatRoom = ChatRoom.createChatRoom(name);
        redisTemplate.opsForHash().put("CHAT_ROOMS", chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    public ChatRoom findRoomById(String id){
        return (ChatRoom) redisTemplate.opsForHash().get("CHAT_ROOMS", id);
    }

    public List<Object> findAll(){
        return redisTemplate.opsForHash().values("CHAT_ROOMS");
    }
}
