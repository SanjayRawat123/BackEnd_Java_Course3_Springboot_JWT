/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.jwt.example.bejc3s1springboot_jwtpc1.service;

import com.jwt.example.bejc3s1springboot_jwtpc1.domain.Customer;
import com.jwt.example.bejc3s1springboot_jwtpc1.exception.CustomerNotFoundException;
import com.jwt.example.bejc3s1springboot_jwtpc1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addUser(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByCustomerNameAndPassword(String customerName, String password) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByCustomerNameAndPassword(customerName, password);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        return customer;
    }

    @Override
    public List<Customer> getAllUser() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean result = false;

        if (customerRepository.findById(customerId).isEmpty()) {
            throw new CustomerNotFoundException();
        } else {
            customerRepository.deleteById(customerId);
            result = true;
        }
        return result;

    }
}
