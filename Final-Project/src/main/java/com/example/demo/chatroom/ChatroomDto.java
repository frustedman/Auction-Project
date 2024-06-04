package com.example.demo.chatroom;

import com.example.demo.user.Users;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatroomDto {
    private int num;

    private Users seller;

    private Users buyer;
}
