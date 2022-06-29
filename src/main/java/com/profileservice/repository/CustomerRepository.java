package com.profileservice.repository;


import com.profileservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface CustomerRepository  extends JpaRepository<Customer, BigDecimal> {
}
