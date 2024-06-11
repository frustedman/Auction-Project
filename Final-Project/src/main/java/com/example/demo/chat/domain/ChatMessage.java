package com.example.demo.chat.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Temporal;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class ChatMessage implements Serializable {

    private String sender;
    private String content;
    private LocalDateTime timestamp;
    @Setter
    private String roomId;

    public ChatMessage(String sender, String content, LocalDateTime timestamp, String roomId) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.roomId = roomId;
    }

}
