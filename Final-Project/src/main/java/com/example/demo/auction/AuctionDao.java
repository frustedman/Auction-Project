package com.example.demo.auction;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.product.Product;
import com.example.demo.user.Users;

@Repository
public interface AuctionDao extends JpaRepository<Auction, Integer> {

	
	ArrayList<Auction> findBySeller(Users seller);
	ArrayList<Auction> findByProduct(Product product);
}
