package com.example.demo.chat;

import com.example.demo.chatroom.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatService {
    @Autowired
    private ChatDao dao;

    public ChatDto save(ChatDto dto){
        Chat c = dao.save(new Chat(dto.getNum(),dto.getRoomnum(),dto.getContent(),dto.getDate(),dto.getWriter()));
        return new ChatDto(c.getNum(),c.getRoomnum(),c.getContent(),c.getWdate(),c.getWriter());
    }

    public ArrayList<ChatDto> findAllByRoomnum(int roomnum){
        ArrayList<Chat> l = dao.findChatsByRoomnumOrderByWdate(new Chatroom(roomnum,null,null));
        ArrayList<ChatDto> list = new ArrayList<>();
        for(Chat c : l){
            list.add(new ChatDto(c.getNum(),c.getRoomnum(),c.getContent(),c.getWdate(),c.getWriter()));
        }
        return list;
    }

    public void delChat(int num){
        dao.delete(new Chat(num,null,null,null,null));
    }
}
