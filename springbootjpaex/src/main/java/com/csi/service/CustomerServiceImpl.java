package com.csi.service;

import com.csi.model.Customer;
import com.csi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    @Autowired
    CustomerRepo customerRepoImpl;

    public Customer saveData(Customer customer){
        return customerRepoImpl.save(customer);
    }

    @Cacheable(value = "custId")
    public Optional<Customer> getDataById(int custId){
        return customerRepoImpl.findById(custId);
    }

    public List<Customer> getAllData(){
        return customerRepoImpl.findAll();
    }

    public Customer updateData(Customer customer){
        return customerRepoImpl.save(customer);
    }

    public Customer partialUpdate(Customer customer){
        return customerRepoImpl.save(customer);
    }

    public void deleteData(int custId){
        customerRepoImpl.deleteById(custId);
    }
}
