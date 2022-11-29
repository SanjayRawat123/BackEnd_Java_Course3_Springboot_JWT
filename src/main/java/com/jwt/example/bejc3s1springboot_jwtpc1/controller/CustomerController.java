/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.jwt.example.bejc3s1springboot_jwtpc1.controller;



import com.jwt.example.bejc3s1springboot_jwtpc1.domain.Customer;
import com.jwt.example.bejc3s1springboot_jwtpc1.exception.CustomerNotFoundException;
import com.jwt.example.bejc3s1springboot_jwtpc1.service.CustomerService;
import com.jwt.example.bejc3s1springboot_jwtpc1.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private ResponseEntity responseEntity;

    private CustomerService customerService;

    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator){
        this.customerService = customerService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUer(@RequestBody Customer customer){

        Customer customer1 = customerService.addUser(customer);
        responseEntity=new ResponseEntity<>(customer1, HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/customerdata/v1/customer")
    public ResponseEntity<?> getAllUser(){
        List<Customer> customerList = customerService.getAllUser();
        responseEntity=new ResponseEntity<>(customerList,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Customer customer) throws CustomerNotFoundException {

        Map<String, String> map = null;
        try {
            Customer customerObj = customerService.findByCustomerNameAndPassword(customer.getCustomerName(), customer.getPassword());
            if (customerObj.getCustomerName().equals(customer.getCustomerName())) {
                map = securityTokenGenerator.generateToken(customer);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        catch(CustomerNotFoundException e){
            throw new CustomerNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/customerdata/v1/{customerId}")
    public ResponseEntity<?> deleteSingleCustomer(@PathVariable("customerId") int customerId) throws CustomerNotFoundException {

        ResponseEntity responseEntity=null;
        try{
            customerService.deleteCustomer(customerId);
            responseEntity=new ResponseEntity<>("Successfully Deleted 1 record",HttpStatus.OK);

        }catch (CustomerNotFoundException e){

            throw new CustomerNotFoundException();

        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}