package com.example.demo.chat;

import com.example.demo.chatroom.Chatroom;
import com.example.demo.user.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chat {
    @Id
    @SequenceGenerator(name = "seq_gen", sequenceName = "seq_chat", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chat")
    private int num;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Chatroom roomnum;

    private String content;

    private Date wdate;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users writer;

    @PrePersist
    public void setDate() {
        wdate = new Date();
    }
}
