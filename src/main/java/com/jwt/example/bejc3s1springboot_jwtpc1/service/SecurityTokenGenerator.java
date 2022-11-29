/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.jwt.example.bejc3s1springboot_jwtpc1.service;

import com.jwt.example.bejc3s1springboot_jwtpc1.domain.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String, String> generateToken(Customer customer) ;


}
