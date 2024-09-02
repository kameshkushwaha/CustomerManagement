package com.cx.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cx.management.entity.Customer;



    public interface Repo extends JpaRepository<Customer,Integer> {

    //public Agent findByUserName(String agentFirstName);

}
    

