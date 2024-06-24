package com.example.demo.auction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.bid.BidDto;
import com.example.demo.bid.BidService;
import com.example.demo.chat.domain.ChatRoom;
import com.example.demo.chat.service.ChatRoomService;
import com.example.demo.user.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuctionScheduler {

	@Autowired
	private AuctionService service;
	@Autowired
	private BidService bidService;
	@Autowired
	private ChatRoomService chatRoomService;

	@Scheduled(cron = "0 0/5 * * * *") // 매 5분에 실행
	public void setStatus() {
		Date date = new Date();
		ArrayList<AuctionDto> list = service.getByStatus("경매중");
		for (AuctionDto auction : list) {
			if (auction.getEnd_time().before(date)) {
				auction.setStatus("경매 마감");
				String seller = auction.getSeller().getId();
				BidDto byBuyer = bidService.getByBuyer(auction.getNum());
				if (auction.getType().equals(Auction.Type.EVENT)) {
					ArrayList<BidDto> buylist = bidService.getByParent(auction.getNum());
					Map<Member, Object> map = new HashMap();
					int total = 0;
					for (BidDto bdto : buylist) {
						map.put(bdto.getBuyer(), 0);
						total = total + bdto.getPrice();
					}
					for (BidDto bdto : buylist) {
						int price = (int) map.get(bdto.getBuyer());
						map.put(bdto.getBuyer(), price + bdto.getPrice());
					}
					int total2 = total;
					Map<Member, Double> map2 = new HashMap<>();
					map.forEach((key, value) -> {
						int price = (int) value;
						double chance = price / total2;
						map2.put(key, chance);
					});
					Random random = new Random();
					double randomNumber = random.nextDouble(); // 0부터 1 사이의 랜덤 숫자 생성

					// Stream API를 활용한 랜덤 선택
					Member winner = map2.entrySet().stream().filter(entry -> randomNumber < entry.getValue())
							.map(Map.Entry::getKey) // 당첨자 이름으로 매핑
							.findFirst() // 첫 번째 당첨자 찾기
							.orElse(null); // 찾지 못할 경우 null 반환
					byBuyer.setBuyer(winner);
				}
				auction.setMino(byBuyer.getBuyer());
				log.debug("byBuyer:{}", byBuyer);
				service.save(auction);
				Set<Object> byName = chatRoomService.findByName(auction.getMino().getId());
				if (byName.isEmpty()) {
					log.debug("byName:{}", byName);
					log.debug("id={}", byBuyer.getBuyer().getId());
					chatRoomService.createChatRoom(byBuyer.getBuyer().getId(), seller);
					return;
				}
				for (Object obj : byName) {
					if (obj instanceof ChatRoom) {
						ChatRoom chatRoom = (ChatRoom) obj;
						String chatRoomSeller = chatRoom.getSeller();
						if (!chatRoomSeller.equals(seller)) {
							chatRoomService.createChatRoom(byBuyer.getBuyer().getId(), seller);// Get seller from
																								// ChatRoom
						}
						System.out.println("ChatRoom Seller: " + chatRoomSeller);
						// Additional processing if needed
					}
				}
			}
		}
	}
}
