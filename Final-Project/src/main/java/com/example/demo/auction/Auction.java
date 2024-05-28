package com.example.demo.auction;

import com.example.demo.product.Product;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Auction {

    @Id
    private int num;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User seller;

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


    public enum Type {
        NORMAL, BLIND, EVENT
    }

    @PrePersist
    public void prePersist() {
        start_time = new Date();
        end_time = new Date();
    }
}
