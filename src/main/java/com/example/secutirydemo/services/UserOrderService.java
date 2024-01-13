package com.example.secutirydemo.services;

import com.example.secutirydemo.model.Product;
import com.example.secutirydemo.model.User;
import com.example.secutirydemo.model.UserOrder;
import com.example.secutirydemo.repository.ProductRepository;
import com.example.secutirydemo.repository.UserOrderRepository;
import com.example.secutirydemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {


    private final UserOrderRepository userOrderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Autowired
    public UserOrderService(UserOrderRepository userOrderRepository,UserRepository userRepository,ProductRepository productRepository){
        this.userOrderRepository = userOrderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<UserOrder> getUserOrders() {
        return userOrderRepository.findAll();
    }

    public void addNewUserOrder(UserOrder userOrder) {
        userOrderRepository.save(userOrder);
    }

    public void deleteUserOrder(Integer userOrderId) {
        boolean exists = userOrderRepository.existsById(userOrderId);
        if (!exists){
            throw new IllegalStateException("User order with id "+userOrderId+" does not exist");
        }
        userOrderRepository.deleteById(userOrderId);
    }
    @Transactional
    public void updateUserOrder(Integer userOrderId,String quantity,Integer productId,Integer userId) {
        UserOrder userOrder = userOrderRepository.findById(userOrderId).orElseThrow(() -> new IllegalStateException("User order with id "+userOrderId+"does not exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id "+userId+"does not exist"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("User with id "+productId+"does not exist"));
        userOrder.setQuantity(quantity);
        userOrder.setProduct(product.getId());
        userOrder.setUser(user.getId());
    }
}