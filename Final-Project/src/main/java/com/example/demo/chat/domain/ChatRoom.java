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

    @Builder
    public ChatRoom(String id, String buyer, String seller) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
    }

    public static ChatRoom createChatRoom(String buyer, String seller) {
        return ChatRoom.builder()
                .id(UUID.randomUUID().toString())
                .buyer(buyer)
                .seller(seller)
                .build();
    }
}
