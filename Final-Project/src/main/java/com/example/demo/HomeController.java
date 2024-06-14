package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.auction.Auction;
import com.example.demo.auction.AuctionDto;
import com.example.demo.auction.AuctionService;
import com.example.demo.bid.BidService;

// 로그인 안 해도 접근 가능한 페이지

@Controller
public class HomeController {

	@Autowired
	private AuctionService aservice;
	
	@Autowired
	private BidService bservice;
	
	@GetMapping("/")
	public String home(ModelMap map) {
		ArrayList<AuctionDto> l=aservice.getAllByBids("경매중");
		ArrayList<String> list= new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getType().equals(Auction.Type.BLIND)) {
				l.get(i).setMax(l.get(i).getMin());
			}
			list.add(null);
			map.addAttribute("HBA"+(list.size()),l.get(i));
			if(list.size()>5) {
				break;
			}
		}
		return "index";
	}
	
	
	
	
}
