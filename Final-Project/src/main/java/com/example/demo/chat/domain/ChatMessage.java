package com.example.demo.chat.domain;

import jakarta.annotation.PostConstruct;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage implements Serializable {

    private String sender;
    private String content;
    private LocalDateTime timestamp;
    @Setter
    private String roomId;

    @PostConstruct
    public void init() {
        this.timestamp = LocalDateTime.now();
    }

}
