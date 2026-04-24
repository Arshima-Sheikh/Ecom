package com.telusko.EcomProject.service;

import com.telusko.EcomProject.model.Product;
import com.telusko.EcomProject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepo productRepo;

    public List<Product> getAllProducts(){
       return productRepo.findAll();
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct (Product p, MultipartFile img)throws IOException {
        p.setImageName(img.getOriginalFilename());
        p.setImageType(img.getContentType());
        p.setImageData(img.getBytes());
        return productRepo.save(p);
    }
    public Product updateProduct(int id,Product p,MultipartFile f) throws IOException{
        p.setImageData(f.getBytes());
        p.setImageName(f.getOriginalFilename());
        p.setImageType(f.getContentType());
        return  productRepo.save(p);
    }
    public void deleteById(int id){
        productRepo.deleteById(id);
    }
}
