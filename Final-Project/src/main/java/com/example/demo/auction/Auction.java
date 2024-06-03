package com.example.demo.auction;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.bid.Bid;
import com.example.demo.product.Product;
import com.example.demo.user.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Auction {

    @Id
    @SequenceGenerator(name = "seq_gen", sequenceName = "seq_auction", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auction")
    private int num;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users seller;

    private int min;
    private int max;

    @OneToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private int status;

    private Date start_time;
    private Date end_time;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy="parent", cascade= CascadeType.ALL)
    private List<Bid> BidList =new ArrayList<>();
    
    public enum Type {
        NORMAL, BLIND, EVENT
    }
    
    public static Auction create(AuctionDto dto) {
    	return Auction.builder()
    			.num(dto.getNum())
    			.seller(dto.getSeller())
    			.min(dto.getMin())
    			.max(dto.getMax())
    			.product(dto.getProduct())
    			.status(dto.getStatus())
    			.start_time(dto.getStart_time())
    			.end_time(dto.getEnd_time())
    			.type(dto.getType())
    			.build();
    }

    @Builder
	public Auction(int num, Users seller, int min, int max, Product product, int status, Date start_time, Date end_time,
			Type type) {
		this.num = num;
		this.seller = seller;
		this.min = min;
		this.max = max;
		this.product = product;
		this.status = status;
		this.start_time = start_time;
		this.end_time = end_time;
		this.type = type;
	}
    
	
}
