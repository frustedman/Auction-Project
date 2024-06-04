package com.example.demo.chatroom;

import com.example.demo.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ChatroomDao extends JpaRepository<Chatroom, Integer> {
    ArrayList<Chatroom> findByBuyerOrSeller(Member buyer, Member seller);
}
