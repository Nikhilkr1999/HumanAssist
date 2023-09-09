package com.humanassist.api;

import com.humanassist.domain.Customer;
import com.humanassist.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerAPI {

    private CustomerService customerService;

    @Autowired
    public CustomerAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return this.customerService.getAllCustomer();
    }

    @GetMapping(path = "{id}")
    public Customer getCustomerById(@PathVariable("id")UUID id) {
        return this.customerService.getCustomer(id);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return this.customerService.addCustomer(customer);
    }


    @PutMapping(path = "{id}")
    public Customer updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer) {
        return this.customerService.updateCustomer(id, customer);
    }

    @DeleteMapping(path = "{id}")
    public Integer deleteCustomer(@PathVariable("id") UUID id) {
        return this.customerService.deleteCustomer(id);
    }

}
