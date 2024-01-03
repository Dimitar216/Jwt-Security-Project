package com.example.secutirydemo.services;

import com.example.secutirydemo.model.UserOrder;
import com.example.secutirydemo.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {


    private final UserOrderRepository userOrderRepository;

    @Autowired
    public UserOrderService(UserOrderRepository userOrderRepository){
        this.userOrderRepository = userOrderRepository;
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
            throw new IllegalStateException("student with id "+userOrderId+" does not exist");
        }
        userOrderRepository.deleteById(userOrderId);
    }
}
