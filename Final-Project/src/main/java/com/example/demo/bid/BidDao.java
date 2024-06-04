package com.example.demo.bid;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.auction.Auction;

@Repository
public interface BidDao extends JpaRepository<Bid, Integer> {

	ArrayList<Bid>findByParentOrderByPrice(Auction parent);
}
