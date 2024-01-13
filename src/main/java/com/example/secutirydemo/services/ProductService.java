package com.example.secutirydemo.services;

import com.example.secutirydemo.model.Product;
import com.example.secutirydemo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public void deleteProduct(Integer productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists){
            throw new IllegalStateException("student with id "+productId+" does not exist");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Integer productId, String name, String price) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product with id "+productId+"does not exist"));

        if(name != null && !name.isEmpty() &&!Objects.equals(product.getName(),name)){
            product.setName(name);
        }
        if(price != null && !price.isEmpty() &&!Objects.equals(product.getPrice(),price)){
            product.setPrice(price);
        }
    }
}