package com.cx.management.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.management.dao.Repo;
import com.cx.management.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private Repo repo;

    @Override
    public List<Customer> getUser() {

        return repo.findAll();
        
    }

    @Override
    public void save(Customer customer) {
         repo.save(customer);
        
    }

    @Override
    public Customer findById(Integer id) {

        return repo.findById(id).get();
        
    }

    @Override
    public void delete(Customer customer) {

        repo.delete(customer);
       
    }
    
}
