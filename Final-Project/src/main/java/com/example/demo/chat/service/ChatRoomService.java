package com.example.demo.chat.service;

import com.example.demo.chat.domain.ChatMessage;
import com.example.demo.chat.domain.ChatRoom;
import com.example.demo.chat.repository.RedisChatRoomRepository;
import com.example.demo.chat.repository.RedisMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisChatRoomRepository redisChatRoomRepository;
    private final RedisMessageRepository messageRepository;
    private final Map<String, List<String>> chatRoomMap = new HashMap<>();
    private final List<String> members = new ArrayList<>();
    public ChatRoom createChatRoom(String buyer, String seller) {
        return redisChatRoomRepository.save(buyer,seller);
    }

    //    public List<Object> findAllChatRooms() {
//        return redisChatRoomRepository.findAll();
//    }
    public void updateChatroom(String roomId) {
        log.debug("updateChatroom roomId: {}", roomId);
        redisChatRoomRepository.updateChatRoom(roomId);
    }
    public Set<Object> findByName(String name) {
        return redisChatRoomRepository.findByName(name);
    }

    public List<Object> getAllChatMessages(String roomId) {
        //        Set<ChatMessage> chatMessages =new HashSet<>();
//        log.info("messages: {}", messages);
//        if (messages != null) {
//            for (Object message : new ArrayList<>(messages)) { // 안전하게 복사본을 반복합니다
//                if (message instanceof ChatMessage) {
//                    chatMessages.add((ChatMessage) message);
//                }
//            }
//        }
        List<Object> messagesByRoomId = messageRepository.getMessagesByRoomId(roomId);
        log.debug("list all messages by roomId: {}", messagesByRoomId);
        return messageRepository.getMessagesByRoomId(roomId);
    }

    public void addChatRoom(String roomId, String member){
        if (chatRoomMap.get(roomId)==null){
            List<String> memberList = new ArrayList<>();
            memberList.add(member);
            chatRoomMap.put(roomId, memberList);
            return;
        }

        if (chatRoomMap.get(roomId).size()==1 && !chatRoomMap.get(roomId).contains(member)) {
            chatRoomMap.get(roomId).add(member);
            log.debug("size={}", chatRoomMap.get(roomId).size());
        }
    }

    public boolean check(String roomId) {
        List<String> strings = chatRoomMap.get(roomId);
        log.debug("strings size={}", strings.size());
        log.debug("members={}", chatRoomMap.get(roomId).get(0));
        if (strings.size()==2) {
            messageRepository.updateRead(roomId);
            return true;
        }
        return false;
    }
}
