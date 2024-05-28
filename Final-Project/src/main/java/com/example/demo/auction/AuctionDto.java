package com.example.demo.auction;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.auction.Auction.Type;
import com.example.demo.product.Product;
import com.example.demo.user.Users;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class AuctionDto {
	    private int num;

	 
	    private Users seller;

	    private int min;
	    private int max;

	    private Product product;

	    private int status;

	    private Date start_time;
	    private Date end_time;
	    private Type type;
	   
}
