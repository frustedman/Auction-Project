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
        String fname = p.getNum() + oname;
        File f = new File(path + fname);
        try {
            dto.getF().transferTo(f);
            p.setFname(f.getName());
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
        p.setFname(dto.getFname());
        p.setName(dto.getName());
        p.setCategories(dto.getCategories());
        service.save(p);
        return "redirect:/";
    }

    @RequestMapping("/del")
    public String del(int num) {
        ProductDto p = service.getProd(num);
        service.delProd(num);
        File f = new File(path + p.getFname());
        f.delete();
        return "redirect:/";
    }

    @GetMapping("/categories")
    public void Categories(String categories, ModelMap map) {
        map.addAttribute("list", service.getByCategories(categories));
    }

    @GetMapping("/name")
    public void name(String name, ModelMap map) {
        map.addAttribute("name", service.getByName(name));
    }
    
    

}
