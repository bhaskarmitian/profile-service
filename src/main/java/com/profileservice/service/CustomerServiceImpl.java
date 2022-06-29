package com.profileservice.service;

import com.profileservice.model.Customer;
import com.profileservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Async
    public CompletableFuture<List<Customer>> findAllUsers(){
        log.info("get list of user by "+Thread.currentThread().getName());
        List<Customer> users=customerRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    @Async
    @Override
    public CompletableFuture<List<Customer>> saveCustomers(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Customer> customers = parseCSVFile(file);
        log.info("saving list of Customers of size {}", customers.size(), "" + Thread.currentThread().getName());
        customers = customerRepository.saveAll(customers);
        long end = System.currentTimeMillis();
        log.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(customers);
    }
    @Async
    @Override
    public CompletableFuture<List<Customer>> findAllCustomers(){
        log.info("get list of Customer by "+Thread.currentThread().getName());
        List<Customer> Customers=customerRepository.findAll();
        return CompletableFuture.completedFuture(Customers);
    }

    private List<Customer> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Customer> customers = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Customer customer = new Customer();
                    customer.setFirstName(data[0]);
                    customer.setLastName(data[1]);
                    customer.setEmail(data[2]);
                    customer.setGender(data[3]);
                    customers.add(customer);
                }
                return customers;
            }
        } catch (final IOException e) {
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    /*@Override
    public Customer fetchById(BigDecimal profileId) {
        Optional<Customer> customer = customerRepository.findById(profileId);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> fetchAllProfiles() {
        return customerRepository.findAll();
    }*/
}
