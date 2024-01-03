package com.example.secutirydemo.services;

import com.example.secutirydemo.model.Product;
import com.example.secutirydemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        Optional<Product> productPriceOptional = productRepository.findByPrice(product.getPrice());
        Optional<Product> productNameOptional = productRepository.findByName(product.getName());
        if(productNameOptional.isPresent()&&productPriceOptional.isPresent()){
            throw new IllegalStateException("Product already exists");
        }
        productRepository.save(product);
    }
    public void deleteStudent(Integer productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists){
            throw new IllegalStateException("student with id "+productId+" does not exist");
        }
        productRepository.deleteById(productId);
    }

}
