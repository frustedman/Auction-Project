package com.example.demo.product;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.product.Product.Categories;
import com.example.demo.user.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
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
    private Categories categories;
    private MultipartFile f;
    
    public static ProductDto create(Product p) {
    	return ProductDto.builder()
    			.num(p.getNum())
    			.seller(p.getSeller())
    			.name(p.getName())
    			.img1(p.getImg1())
    			.img2(p.getImg2())
    			.img3(p.getImg3())
    			.img4(p.getImg4())
    			.img5(p.getImg5())
    			.categories(p.getCategories())
    			.build();
    }
    
    @Builder
	public ProductDto(int num, Users seller, String name, String img1, String img2, String img3, String img4, String img5,
			Categories categories,MultipartFile f) {
		this.num = num;
		this.seller = seller;
		this.name = name;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.categories = categories;
		this.f=f;
	}
    private MultipartFile f1;
    private MultipartFile f2;
    private MultipartFile f3;
    private MultipartFile f4;
    private MultipartFile f5;

}
