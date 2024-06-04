package com.example.demo.chatroom;

import com.example.demo.user.Member;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatroomDto {
    private int num;

    private Member seller;

    private Member buyer;
}
