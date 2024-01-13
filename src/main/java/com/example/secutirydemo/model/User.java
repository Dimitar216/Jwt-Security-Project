package com.example.secutirydemo.model;

import com.example.secutirydemo.enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Model class that generates a _users table in postgresql
 */
@Data // provides us with getter,setter,toString (reduces boilerplate code)
@Builder // helps to build the object using the design pattern builder
@NoArgsConstructor // generates a constructor that requires no values to be input
@AllArgsConstructor // generates a constructor that requires a value for every field in the class
@Entity // marks the class as an entity to be managed by JPA and every field is used to create a column in a database
@Table(name = "_user") //name of table
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user")
    private UserOrder userOrder;

    /**
     * class that returns a list of the roles of all users
     * @return List of roles of users
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * class that returns email as username
     * @return email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * class that returns password of user
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}