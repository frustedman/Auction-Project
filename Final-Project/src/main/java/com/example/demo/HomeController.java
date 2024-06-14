package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.auction.AuctionDto;
import com.example.demo.auction.AuctionService;

import jakarta.servlet.http.HttpSession;

// 로그인 안 해도 접근 가능한 페이지

@Controller
public class HomeController {

	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	
	
	
}
