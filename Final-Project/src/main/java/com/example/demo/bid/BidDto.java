package com.example.demo.bid;

import com.example.demo.auction.Auction;
import com.example.demo.user.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BidDto {

	    private int num;
	    private Auction parent;
	    private Users buyer;
	    private int price;
	    
	   
	    
	    public static BidDto create(Bid b) {
	    	return BidDto.builder()
	    			.num(b.getNum())
	    			.parent(b.getParent())
	    			.buyer(b.getBuyer())
	    			.price(b.getPrice())
	    			.build();
	    }

	    @Builder
		public BidDto(int num, Auction parent, Users buyer, int price) {
			this.num = num;
			this.parent = parent;
			this.buyer = buyer;
			this.price = price;
		}
}
