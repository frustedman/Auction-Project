package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/auth/prod")
public class ProductController {

    @Autowired
    private ProductService service;

    @Value("${spring.servlet.multipart.location")
    private String path;

    @GetMapping("/add")
    public String addFrom() {
        return "prod/add";
    }

    @PostMapping("/add")
    public String add(ProductDto dto) {
        ProductDto p = service.save(dto);
        String oname = dto.getF().getOriginalFilename();
        String img1 = p.getNum() + oname;
        File f = new File(path + img1);
        try {
            dto.getF().transferTo(f);
            p.setImg1(f.getName());
            service.save(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/auth/auction/list";
    }

    @GetMapping("/read-img")
    public ResponseEntity<byte[]> read_img(String fname) {
        ResponseEntity<byte[]> result = null;
        File f = new File(path + fname);
        HttpHeaders header = new HttpHeaders();
        try {
            header.add("Content-Type", Files.probeContentType(f.toPath()));
            result = new ResponseEntity<byte[]>(
                    FileCopyUtils.copyToByteArray(f), header, HttpStatus.OK
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PostMapping("/edit")
    public String edit(ProductDto dto) {
        ProductDto p = service.getProd(dto.getNum());
        p.setName(dto.getName());
        p.setImg1(dto.getImg1());
        p.setImg2(dto.getImg2());
        p.setImg3(dto.getImg3());
        p.setImg4(dto.getImg4());
        p.setImg5(dto.getImg5());
        p.setCategories(dto.getCategories());
        service.save(p);
        return "redirect:/";
    }

    @RequestMapping("/del")
    public String del(int num) {
        ProductDto p = service.getProd(num);
        service.delProd(num);
        File f = new File(path + p.getImg1());
        f.delete();
        return "redirect:/";
    }

    @GetMapping("/categories")
    public void Categories(String categories, ModelMap map) {
        map.addAttribute("list", service.getByCategories(categories));
    }

    @GetMapping("/name")
    public void name(String name, ModelMap map) {
        map.addAttribute("list", service.getByName(name));
    }

    @GetMapping("/myprod")
    public void myProduct(String seller, ModelMap map) {
        map.addAttribute("list", service.getBySeller(seller));
    }
    
    

}
