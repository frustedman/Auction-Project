package com.example.demo.delivery;

import com.example.demo.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Integer> {
    DeliveryDto findByAuction(Auction auction);
}
