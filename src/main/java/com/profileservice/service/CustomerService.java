package com.profileservice.service;


import com.profileservice.model.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface CustomerService {

    public CompletableFuture<List<Customer>> findAllUsers();

    public CompletableFuture<List<Customer>> saveCustomers(MultipartFile file) throws Exception;

    public CompletableFuture<List<Customer>> findAllCustomers();

    //CompletableFuture<Customer> save(Customer customer);

    //Customer fetchById(BigDecimal profileId);

    //List<Customer> fetchAllProfiles();
}
