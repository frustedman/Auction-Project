package com.example.demo.chatroom;

import com.example.demo.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatroomService {
    @Autowired
    private ChatroomDao dao;

    public ChatroomDto save(ChatroomDto dto) {
        Chatroom chatroom = dao.save(new Chatroom(dto.getNum(),dto.getSeller(),dto.getBuyer()));
        return new ChatroomDto(chatroom.getNum(),chatroom.getSeller(),chatroom.getBuyer());
    }

    public ChatroomDto getChatroom(int num){
        Chatroom cr  = dao.findById(num).orElse(null);
        if(cr==null){
            return null;
        }
        return new ChatroomDto(cr.getNum(),cr.getSeller(),cr.getBuyer());
    }

    public ArrayList<ChatroomDto> getAll(String id){
        Member u = new Member(id,"","","",null,0,"",0,"");
        List<Chatroom> l = dao.findByBuyerOrSeller(u,u);
        ArrayList<ChatroomDto> list = new ArrayList<>();
        for(Chatroom cr : l){
            list.add(new ChatroomDto(cr.getNum(),cr.getSeller(),cr.getBuyer()));
        }
        return list;
    }

    public void delchatroom(int num){
        dao.delete(new Chatroom(num,null,null));
    }
}
