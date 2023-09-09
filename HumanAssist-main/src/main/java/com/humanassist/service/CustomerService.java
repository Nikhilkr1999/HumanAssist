package com.humanassist.service;

import com.humanassist.dao.CustomerDAO;
import com.humanassist.dao.CustomerDAOImpl;
import com.humanassist.domain.Customer;
import com.humanassist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@CacheConfig(cacheNames = "customer")
public class CustomerService {

    private Logger logger = Logger.getLogger(CustomerService.class.getCanonicalName());
    private CustomerDAO customerDAO;
    @Autowired
    public CustomerService(@Qualifier("customer-postgres")CustomerDAOImpl customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer addCustomer(Customer customer) {
        return this.customerDAO.insertCustomer(customer);
    }

    public List<Customer> getAllCustomer() {
        return this.customerDAO.getCustomers();
    }

    @Cacheable(key = "#id")
    public Customer getCustomer(UUID id) {
        return this.customerDAO.getCustomer(id).orElseThrow(() -> {
            logger.log(Level.SEVERE, "No Counselor exists with id :: " + id);
            return new NotFoundException("No customer found with id ::: " + id.toString());
        });
    }

    @CacheEvict(key = "#id")
    public Integer deleteCustomer(UUID id) {
        return this.customerDAO.deleteCustomerById(id);
    }

    @CachePut(key = "#id", value = "customer")
    public Customer updateCustomer(UUID id, Customer customer) {
        return this.customerDAO.updateCustomer(id, customer);
    }

}
