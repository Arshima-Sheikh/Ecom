package com.telusko.EcomProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    @RequestMapping("/home")
    public  String greet(){
        return "Hy, Welcome to Home Page!";
    }
}
