package com.telusko.EcomProject.controller;

import com.telusko.EcomProject.model.Product;
import com.telusko.EcomProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
    public ResponseEntity<List<Product>>getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product prod=productService.getProductById(id);
        if(prod==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(prod,HttpStatus.OK);
    }
}
