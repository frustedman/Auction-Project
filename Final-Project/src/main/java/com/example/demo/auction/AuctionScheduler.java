package com.example.demo.auction;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AuctionScheduler {
	
	@Autowired
	private AuctionService service;
	
	@Scheduled(cron = "0 0/5 * * * *") // 매 5분에 실행 
	public void setStatus() {
		Date date =new Date();
		ArrayList<AuctionDto> list=service.getByStatus("경매중");
		for(AuctionDto auction:list) {
			if(auction.getEnd_time().before(date)) {
				auction.setStatus("경매 마감");
				service.save(auction);
			}
		}
	}
}
