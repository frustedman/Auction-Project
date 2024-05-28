package com.example.demo.user;

import com.example.demo.card.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

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
