package com.example.demo.chat.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private String id;
    private String buyer;
    private String seller;
    private Long men;

    @Builder
    public ChatRoom(String id, String buyer, String seller,Long men) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.men=men;
    }

    public static ChatRoom createChatRoom(String buyer, String seller,Long men) {
        return ChatRoom.builder()
                .id(UUID.randomUUID().toString())
                .buyer(buyer)
                .seller(seller)
                .men(men)
                .build();
    }
    public static ChatRoom updateChatRoom(String roomId, String buyer, String seller) {
        return ChatRoom.builder()
                .id(roomId)
                .buyer(buyer)
                .seller(seller)
                .build();
    }
}