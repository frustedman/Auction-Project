package com.example.demo.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    private int num;

    private String name;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String categories;

}
