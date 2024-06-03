package com.example.demo.product;

import com.example.demo.user.Users;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private int num;
    private Users seller;
    private String name;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String categories;
    private MultipartFile f1;
    private MultipartFile f2;
    private MultipartFile f3;
    private MultipartFile f4;
    private MultipartFile f5;
}
