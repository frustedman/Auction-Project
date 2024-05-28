package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
   @Autowired
   private UserDao dao;
   
   @Autowired
   private PasswordEncoder passwordEncoder;

   // 추가, 수정
   public UserDto save(UserDto dto) {
      // dao.save() 반환값: 방금 추가/수정한 그 행을 검색해서 entity에 담아서 반환
      Users u = dao.save(new Users(dto.getId(),passwordEncoder.encode(dto.getPwd()),dto.getName(),dto.getEmail(),dto.getCardnum(),
            dto.getPoint(),dto.getRank(),dto.getExp(),dto.getType()));
      return new UserDto(u.getId(),u.getPwd(),u.getName(),u.getEmail(),u.getCardnum(),u.getPoint(),u.getRank(),u.getExp(),u.getType());
   }

   // id로 검색
   public UserDto getUser(String id) {
      // dao.findById(pk): pk기준으로 검색
      Users u = dao.findById(id).orElse(null);// orElse(null): 검색결과 없으면 널 반환
      if (u == null) {
         return null;
      }
      return new UserDto(u.getId(),u.getPwd(),u.getName(),u.getEmail(),u.getCardnum(),u.getPoint(),u.getRank(),u.getExp(),u.getType());
   }

   // 전체검색: findAll()
   public ArrayList<UserDto> getAll() {
      List<Users> l = dao.findAll();
      ArrayList<UserDto> list = new ArrayList<>();
      for (Users u : l) {
         list.add(new UserDto(u.getId(),u.getPwd(),u.getName(),u.getEmail(),u.getCardnum(),u.getPoint(),u.getRank(),u.getExp(),u.getType()));
      }
      return list;
   }
   
   //id기준 삭제
   public void delMember(String id) {
      dao.deleteById(id);
   }
   
   //type을 검색
   public ArrayList<UserDto> getByType(String type) {
      List<Users> l = dao.findByType(type);
      ArrayList<UserDto> list = new ArrayList<>();
      for (Users u : l) {
         list.add(new UserDto(u.getId(),u.getPwd(),u.getName(),u.getEmail(),u.getCardnum(),u.getPoint(),u.getRank(),u.getExp(),u.getType()));
      }
      return list;
   }
}