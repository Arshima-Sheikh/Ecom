package com.telusko.EcomProject.service;

import com.telusko.EcomProject.model.Product;
import com.telusko.EcomProject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepo productRepo;

    public List<Product> getAllProducts(){
       return productRepo.findAll();
    }
}
