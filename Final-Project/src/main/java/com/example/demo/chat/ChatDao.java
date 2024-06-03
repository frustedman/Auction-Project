package com.example.demo.chat;

import com.example.demo.chatroom.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ChatDao extends JpaRepository<Chat, Integer> {
    ArrayList<Chat> findChatsByRoomnumOrderByWdate(Chatroom chatroom);
}
