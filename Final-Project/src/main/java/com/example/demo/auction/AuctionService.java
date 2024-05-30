package com.example.demo.auction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.product.Product;
import com.example.demo.user.Users;

@Service
public class AuctionService {

	@Autowired
	private AuctionDao dao;
	
	public void save(AuctionDto dto) {	
		dao.save(new Auction(dto.getNum(),dto.getSeller(),dto.getMin(),dto.getMax(),dto.getProduct(),dto.getStatus(),new Date(),dto.getEnd_time(),dto.getType()));
		
	}
	// end 타임 설정
	public void setTime(AuctionDto dto,int t) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dto.getStart_time());
		int time = cal.get(Calendar.HOUR);
		cal.set(Calendar.HOUR, t+time);
		dto.setEnd_time(cal.getTime());
		dao.save(new Auction(dto.getNum(),dto.getSeller(),dto.getMin(),dto.getMax(),dto.getProduct(),dto.getStatus(),dto.getStart_time(),dto.getEnd_time(),dto.getType()));
	}
	// 번호로 삭제
	public void delete(int num) {
		dao.deleteById(num);
	}
	// 번호로 찾기
	public AuctionDto get(int num) {
		Auction a=dao.findById(num).orElse(null);
		if(a==null) {
			return null;
		}
		return new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType());
	}
	// 전체목록
	public ArrayList<AuctionDto> getAll(){
		List<Auction>l= dao.findAll();
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	// 판매자로 찾기
	public ArrayList<AuctionDto> getBySeller(Users seller){
		List<Auction>l= dao.findBySeller(seller);
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	// 상품으로 찾기
	public ArrayList<AuctionDto> getByProduct(Product product){
		List<Auction>l= dao.findByProduct(product);
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	// 타입으로 찾기
	public ArrayList<AuctionDto> getByType(String type){
		List<Auction>l= dao.findByType(type);
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	
	// 상태로 찾기
	public ArrayList<AuctionDto> getByStatus(int status){
		List<Auction>l= dao.findByStatus(status);
		ArrayList<AuctionDto> list=new ArrayList<>();
		for(Auction a:l) {
			list.add(new AuctionDto(a.getNum(),a.getSeller(),a.getMin(),a.getMax(),a.getProduct(),a.getStatus(),a.getStart_time(),a.getEnd_time(),a.getType()));
		}
		return list;
				
	}
	
}
