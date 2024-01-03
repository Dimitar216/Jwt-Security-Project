package com.example.secutirydemo.repository;

import com.example.secutirydemo.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder,Integer> {
    Optional<UserOrder> findUserOrderById(Integer id);
}
