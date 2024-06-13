package com.example.demo.chat.repository;

import com.example.demo.chat.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RedisChatRoomRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SetOperations<String, Object> setOperations;

    public ChatRoom save(String buyer, String seller){
        ChatRoom chatRoom = ChatRoom.createChatRoom(buyer,seller);
        setOperations.add("ROOM_"+buyer, chatRoom );
        setOperations.add("ROOM_"+seller, chatRoom);
//        redisTemplate.opsForHash().put("CHAT_ROOMS", chatRoom.getId(), chatRoom);
//        redisTemplate.opsForHash().put("CHAT_ROOMS_BY_NAME", name, chatRoom.getId());
        return chatRoom;
    }

    public ChatRoom findRoomByName(String name){
        String roomId = (String) redisTemplate.opsForHash().get("CHAT_ROOMS_BY_NAME", name);
        List<Object> chatRooms = redisTemplate.opsForList().range("CHAT_ROOMS", 0, -1);
        for (Object chatRoom : chatRooms) {
        }
        if (roomId != null) {
            return findRoomById(roomId);
        }
        return null;
    }

    public ChatRoom findRoomById(String id){
        return (ChatRoom) redisTemplate.opsForHash().get("CHAT_ROOMS", id);
    }

    public Set<Object> findByName(String name){
//        log.info("CHAT_ROOMS={}", redisTemplate.opsForHash().values("CHAT_ROOMS"));
//        redisTemplate.opsForHash().values("CHAT_ROOMS");
        return setOperations.members("ROOM_"+name);
//        return redisTemplate.opsForHash().values("CHAT_ROOMS");
    }
}
