package com.example.demo.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @SequenceGenerator(name = "seq_gen", sequenceName = "seq_product", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
    private int num;

    private String name;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String categories; // 의류, 디지털 기기, 골동품, 뷰티/잡화, 스포츠용품, 애완용품, 인력, 기타
    private String fname;

}
