package com.example.demo.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Notification implements Serializable {
    private String name;
    private String title;
    private String content;
    private String time;

    @Builder
    public Notification(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public Notification() {
    }
    public static Notification create(String name, String title, String content) {
        return Notification.builder()
                .name(name)
                .title(title)
                .content(content)
                .build();
    }
}