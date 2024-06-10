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
    private String name;

    @Builder
    public ChatRoom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ChatRoom createChatRoom(String name) {
        return ChatRoom.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build();
    }
}
