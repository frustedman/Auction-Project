package com.example.demo.auction;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.product.Product;
import com.example.demo.user.Member;

@Repository
public interface AuctionDao extends JpaRepository<Auction, Integer> {
	
	
	// 판매자로 찾기
	ArrayList<Auction> findBySeller(Member seller);
	// 상품번호로 찾기
	ArrayList<Auction> findByProduct(Product product);
	// 경매 타입별 목록
	ArrayList<Auction> findByType(String type);
	// 경매 상태별 목록
	ArrayList<Auction> findByStatus(String status);
	
	@Query("SELECT a FROM Auction a JOIN a.product p WHERE p.name LIKE %:productName%")
    ArrayList<Auction> findByProductNameLike(@Param("productName") String productName);
	
	
}
