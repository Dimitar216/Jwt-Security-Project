package com.example.secutirydemo.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import jakarta.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserOrder {

    @Getter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Integer id;
    private String quantity;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Integer user;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Product.class)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Integer product;
}