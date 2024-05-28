package com.example.demo.user;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.card.Card;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
	// 시발

    @Id
    private String id;

    private String pwd;
    private String name;
    private String email;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Card cardnum;

    private int point;
    private String rank;
    private int exp;
    private String type;

//    @OneToMany
//    private Installments installment;
}