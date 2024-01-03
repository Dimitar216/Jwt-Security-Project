package com.example.secutirydemo.controller;

import com.example.secutirydemo.model.UserOrder;
import com.example.secutirydemo.services.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class UserOrderController {
    private final UserOrderService userOrderService;

    @Autowired
    public UserOrderController(UserOrderService userOrderService){
        this.userOrderService = userOrderService;
    }

    @GetMapping
    public List<UserOrder> getUserOrders(){
        return userOrderService.getUserOrders();
    }

    @PostMapping
    public void addNewUserOrder(@RequestBody UserOrder userOrder){
        userOrderService.addNewUserOrder(userOrder);
    }

    @DeleteMapping(path = "{userOrderId}")
    public void deleteUserOrder(@PathVariable("userOrderId") Integer userOrderId) {
        userOrderService.deleteUserOrder(userOrderId);
    }
}
