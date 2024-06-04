package com.example.demo.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bid.BidAddDto;
import com.example.demo.bid.BidDto;
import com.example.demo.bid.BidService;
import com.example.demo.user.Users;


@Controller
@RequestMapping("/auth/auction")
public class AuctionController {

	@Autowired
	private AuctionService aservice;
	@Autowired
	private BidService bservice;
	
	
	@PostMapping("/add")
	public String add(AuctionDto a) {
		aservice.save(a);
		return "redirect:/";
	}
	@GetMapping("/detail")
	public String detail(int num,ModelMap map) {
		map.addAttribute("auciton", aservice.get(num));
		return "/auth/auction/detail";
	}
	
	@GetMapping("/list")
	public String list(ModelMap map) {
		map.addAttribute("list", aservice.getAll());
		return "/auth/auction";
	}
	
	@GetMapping("/add")
	public String addform() {
		return "/auth/auction/add";
	}
	@MessageMapping("/price")
	@SendTo("/auth/topic/auction")
	public String send(BidAddDto b) throws InterruptedException {
		
		try{
			bservice.save(new BidDto(b.getNum(),new Auction(b.getParent()),new Users(b.getBuyer()),b.getPrice()));
			
		}catch(Exception e) {
			return null;
		}
		String price=""+b.getPrice();
		return price;
	}
	
	
}
