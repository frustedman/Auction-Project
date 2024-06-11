package com.example.demo.chat.domain;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Temporal;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@NoArgsConstructor
@ToString
public class ChatMessage implements Serializable {

    private String sender;
    private String contentType;
    private String content;
    private String timestamp;
    @Setter
    private String roomId;



    public void updateTimestamp() {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
