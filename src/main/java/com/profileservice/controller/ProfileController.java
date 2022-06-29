package com.profileservice.controller;

import com.profileservice.model.Customer;
import com.profileservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/v1")
public class ProfileController {

    @Autowired
    CustomerService customerService;

    @PreAuthorize("hasAuthority('create')")
    @PostMapping(value = "/create-user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            customerService.saveCustomers(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/users", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_admin')")
    public CompletableFuture<ResponseEntity> findAllUsers() {
        return  customerService.findAllUsers().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/getUsersByThread", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_admin')")
    public  ResponseEntity getUsers(){
        CompletableFuture<List<Customer>> users1=customerService.findAllUsers();
        CompletableFuture<List<Customer>> users2=customerService.findAllUsers();
        CompletableFuture<List<Customer>> users3=customerService.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    /*@PostMapping(value="create-profile", produces = "application/json")
    @PreAuthorize("hasAuthority('create')")
    public CompletableFuture<Customer> saveProfile(@RequestBody Customer customer) {
        //Customer response= customerService.save(customer);
        return customerService.save(customer);
    }*/



    /*@PostMapping(value="/{type}", produces = "application/json")
    @PreAuthorize("hasAuthority('create')")
    public ResponseEntity<?>  saveProfile(@RequestBody Map<String,Object> customer, @PathVariable String type) {
        System.out.println(customer);
        System.out.println(type);
        //Customer response= customerService.save(customer);
        //return new ResponseEntity<Object>(response, HttpStatus.CREATED);
        return null;
    }*/

    /*@GetMapping(value="/{type}")
    public ResponseEntity<?> fetchProfile(@RequestParam BigDecimal profileId)
    {
        return  new ResponseEntity<Object>(customerService.fetchById(profileId), HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/profiles", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_admin')")  //ROLE_operator
    public List<Customer> fetch() {
        return customerService.fetchAllProfiles();
    }*/
}
