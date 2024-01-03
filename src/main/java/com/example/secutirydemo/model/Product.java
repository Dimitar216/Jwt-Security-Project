package com.example.secutirydemo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String name;
    private String price;
    @OneToOne(mappedBy = "product")
    private UserOrder userOrder;
}