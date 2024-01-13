package com.example.secutirydemo.controller;

import com.example.secutirydemo.model.UserOrder;
import com.example.secutirydemo.services.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addNewUserOrder(@RequestBody UserOrder userOrder){
        userOrderService.addNewUserOrder(userOrder);
        return ResponseEntity.ok("data saved");
    }

    @DeleteMapping(path = "{userOrderId}")
    public void deleteUserOrder(@PathVariable("userOrderId") Integer userOrderId) {
        userOrderService.deleteUserOrder(userOrderId);
    }
    @PutMapping(path = "{userOrderId}")
    public void updateProduct(@PathVariable("userOrderId") Integer userOrderId,@RequestParam(required = false) String quantity,@RequestParam Integer productId,@RequestParam Integer userId){
        userOrderService.updateUserOrder(userOrderId,quantity,productId,userId);
    }
}