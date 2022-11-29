/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.jwt.example.bejc3s1springboot_jwtpc1.repository;

import com.jwt.example.bejc3s1springboot_jwtpc1.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByCustomerNameAndPassword(String customerName, String password);
}
