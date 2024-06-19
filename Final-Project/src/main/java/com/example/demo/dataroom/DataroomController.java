package com.example.demo.dataroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/auth/dataroom")
public class DataroomController {
    @Autowired
    private DataroomService service;
    @Autowired
    private ReplyService rservice;

    @RequestMapping("/list")
    public String list(ModelMap map) {
        ArrayList<DataroomDto> list=service.findAll();
        for(DataroomDto dto:list){
            dto.setReplies(rservice.findAll(dto));
        }
        map.addAttribute("list", list);
        return "dataroom/list";
    }

    @PostMapping("/add")
    public String add(DataroomDto dto){
        service.save(dto);
        return "redirect:/auth/dataroom/list";
    }
    @PostMapping("/reply")
    public String reply(ReplyDto dto){
        log.debug("dto="+dto);
        rservice.save(dto);
        log.debug("after dto="+dto);
        return "redirect:/auth/dataroom/list";
    }
}
