package com.example.secutirydemo.controller;

import com.example.secutirydemo.model.Product;
import com.example.secutirydemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Integer productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable("productId") Integer productId,@RequestParam(required = false) String name,@RequestParam(required = false) String price){
        productService.updateProduct(productId,name,price);
    }
}