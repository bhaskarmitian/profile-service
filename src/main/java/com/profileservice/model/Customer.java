package com.profileservice.model;

import javax.persistence.*;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cust_id")
    private BigDecimal id;

    private String firstName;
    private String lastName;
    //private String password;
    private String email;
    //private String mobileNo;
    private String gender;
    //private boolean enabled;
    //@Column(name = "accountNonExpired")

    //private boolean accountNonExpired;
    //@Column(name = "credentialsNonExpired")
    //private boolean credentialsNonExpired;
    //@Column(name = "accountNonLocked")
    //private boolean accountNonLocked;


}