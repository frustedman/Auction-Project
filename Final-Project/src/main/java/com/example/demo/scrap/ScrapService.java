package com.example.demo.scrap;

import com.example.demo.auction.Auction;
import com.example.demo.user.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScrapService {
    @Autowired
    private ScrapDao dao;

    public void save(ScrapDto dto){
        dao.save(Scrap.create(dto));
    }

    public void del(int num){
        log.debug("service; del");
        Scrap scrap = dao.findById(num).orElse(null);
        dao.delete(scrap);
    }

    public ScrapDto getScrapByAuctionAndMember(Auction auction, Member member){
        Scrap scrap = dao.findByMemberAndAuction(member,auction);
        if(scrap == null){
            return null;
        }
        else{
            return ScrapDto.create(scrap);
        }
    }
}
