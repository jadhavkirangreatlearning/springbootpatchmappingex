package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<Customer> saveData(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerServiceImpl.saveData(customer), HttpStatus.CREATED);
    }

    @GetMapping("/getdatabyid/{custId}")
    public ResponseEntity<Optional<Customer>> getDataById(@PathVariable int custId) {
        return ResponseEntity.ok(customerServiceImpl.getDataById(custId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Customer>> getAllData() {
        return ResponseEntity.ok(customerServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{custId}")
    public ResponseEntity<Customer> updateData(@PathVariable int custId, @Valid @RequestBody Customer customer) {

        Customer customer1 = customerServiceImpl.getDataById(custId).orElseThrow(() -> new RecordNotFoundException("Customer ID Does Not Exist"));

        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustContactNumber(customer.getCustContactNumber());

        return new ResponseEntity<>(customerServiceImpl.updateData(customer1), HttpStatus.CREATED);
    }

    @PatchMapping("/addressupdate/{custId}/{custAddress}")
    public ResponseEntity<Customer> updateAddress(@PathVariable int custId, @PathVariable String custAddress) {
        Customer customer1 = customerServiceImpl.getDataById(custId).orElseThrow(() -> new RecordNotFoundException("Customer ID Does Not Exist"));


        customer1.setCustAddress(custAddress);

        return ResponseEntity.ok(customerServiceImpl.partialUpdate(customer1));
    }

    @DeleteMapping("/deletedata/{custId}")
    public ResponseEntity<String> deleteData(@PathVariable int custId) {
        customerServiceImpl.deleteData(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }
}
