package com.example.demo.auction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.Users;

@Service
public class AuctionService {

	@Autowired
	private AuctionDao dao;
	
	public void save(AuctionDto dto) {
		dao.save(new Auction(dto.getNum(),dto.getSeller(),dto.getMin(),dto.getMax(),dto.getProduct(),dto.getStatus(),dto.getStart_time(),dto.getEnd_time(),dto.getType()));
	}

	public void delete(int num) {
		dao.deleteById(num);
	}
	
	public AuctionDto get(int num) {
		Auction a=dao.findById(num).orElse(null);
		if(a==null) {
			return null;
		}
		return new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType());
	}
	
	public ArrayList<AuctionDto> getAll(){
		List<Auction>l= dao.findAll();
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	public ArrayList<AuctionDto> getBySeller(Users seller){
		List<Auction>l= dao.findBySeller(seller);
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	
}
