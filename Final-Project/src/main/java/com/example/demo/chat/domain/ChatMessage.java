package com.example.demo.chat.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ChatMessage implements Serializable {

    private String sender;
    private String content;
    @Setter
    private Date timestamp;
    @Setter
    private String roomId;

}
