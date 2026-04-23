package com.telusko.EcomProject.controller;

import com.telusko.EcomProject.model.Product;
import com.telusko.EcomProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/home")
    public  String greet(){
        return "Hy, Welcome to Home Page!";
    }

    @GetMapping("/products")
    public List<Product>getAllProducts(){
        return productService.getAllProducts();
    }
}
