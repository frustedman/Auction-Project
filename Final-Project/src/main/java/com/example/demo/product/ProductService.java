package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    // 추가, 수정
    public ProductDto save(ProductDto dto) {
        Product p = dao.save(new Product(dto.getNum(), dto.getName(), dto.getImg1(), dto.getImg2(), dto.getImg3(), dto.getImg4(), dto.getImg5(), dto.getCategories()));
        return new ProductDto(p.getNum(), p.getName(), p.getImg1(), p.getImg2(), p.getImg3(), p.getImg4(), p.getImg5(), p.getCategories(), null);
    }

    // 번호로 삭제
    public void delProd(int num) {
        dao.deleteById(num);
    }

    // 번호로 검색
    public ProductDto getProd(int num) {
        Product p = dao.findById(num).orElse(null);
        if (p == null) {
            return null;
        }
        return new ProductDto(p.getNum(), p.getName(), p.getImg1(), p.getImg2(), p.getImg3(), p.getImg4(), p.getImg5(), p.getCategories(), null);
    }


    // 카테고리로 검색
    public ArrayList<ProductDto> getByCategories(String categories) {
        List<Product> l = dao.findByCategories(categories);
        ArrayList<ProductDto> list = new ArrayList<>();
        for (Product p : l) {
            list.add(new ProductDto(p.getNum(), p.getName(), p.getImg1(), p.getImg2(), p.getImg3(), p.getImg4(), p.getImg5(), p.getCategories(), null));
        }
        return list;
    }

    // 이름으로 검색
    public ArrayList<ProductDto> getByName(String name) {
        List<Product> l = dao.findByCategories(name);
        ArrayList<ProductDto> list = new ArrayList<>();
        for (Product p : l) {
            list.add(new ProductDto(p.getNum(), p.getName(), p.getImg1(), p.getImg2(), p.getImg3(), p.getImg4(), p.getImg5(), p.getCategories(), null));
        }
        return list;
    }

}
