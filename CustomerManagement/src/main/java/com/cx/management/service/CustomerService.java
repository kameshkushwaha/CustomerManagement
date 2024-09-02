package com.cx.management.service;

import java.util.List;


import com.cx.management.entity.Customer;


public interface CustomerService {


    public List<Customer> getUser();

	public void save(Customer customer);

	public Customer findById(Integer id);

	//public Agent findbyUserName(String userName);

	public void delete(Customer customer);
    
}
