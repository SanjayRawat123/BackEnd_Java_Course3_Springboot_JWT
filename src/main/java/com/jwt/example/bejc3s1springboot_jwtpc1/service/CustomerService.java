/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.jwt.example.bejc3s1springboot_jwtpc1.service;

import com.jwt.example.bejc3s1springboot_jwtpc1.domain.Customer;
import com.jwt.example.bejc3s1springboot_jwtpc1.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer addUser(Customer customer);
    public Customer findByCustomerNameAndPassword(String customerName, String password) throws CustomerNotFoundException;
    List<Customer> getAllUser();
    boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
}
