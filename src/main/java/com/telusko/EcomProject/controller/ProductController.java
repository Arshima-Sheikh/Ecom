package com.telusko.EcomProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.EcomProject.model.Product;
import com.telusko.EcomProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestPart("product") String productJson,
            @RequestPart("image") MultipartFile imgFile
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product p = mapper.readValue(productJson, Product.class);

            Product prod = productService.addProduct(p, imgFile);
            return new ResponseEntity<>(prod, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/image/{id}")
    public  ResponseEntity<byte []>getImageByProductId(@PathVariable Integer id){
        Product prod=productService.getProductById(id);
        byte[] imageFile=prod.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(prod.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Integer id,
            @RequestPart("prod") String productJson,
            @RequestPart("imgFile") MultipartFile imgFile
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product prod = mapper.readValue(productJson, Product.class);

            productService.updateProduct(id, prod, imgFile);

            return new ResponseEntity<>("updated", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("failed to update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        Product product=productService.getProductById(id);
        if(product!=null){
            productService.deleteById(id);
            return  new ResponseEntity<>("deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("product does not exists",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/product/search")
    public  ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product>prod=productService.searchProducts(keyword);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }
}
