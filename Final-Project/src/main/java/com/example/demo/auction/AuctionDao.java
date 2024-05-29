package com.example.demo.auction;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.product.Product;
import com.example.demo.user.Users;

@Repository
public interface AuctionDao extends JpaRepository<Auction, Integer> {
	
	
	// 판매자로 찾기
	ArrayList<Auction> findBySeller(Users seller);
	// 상품명으로 찾기
	ArrayList<Auction> findByProduct(Product product);
	// 경매 타입별 목록
	ArrayList<Auction> findByType(String type);
	// 경매 상태별 목록
	ArrayList<Auction> findByStatus(int status);
}
