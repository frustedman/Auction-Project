package com.example.demo.chat;

import com.example.demo.chatroom.Chatroom;
import com.example.demo.user.Member;
import lombok.*;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatDto {
    private int num;

    private Chatroom roomnum;

    private String content;

    private Date date;

    private Member writer;
}
