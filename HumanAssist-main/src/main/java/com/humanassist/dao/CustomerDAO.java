package com.humanassist.dao;

import com.humanassist.domain.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDAO {
    Customer insertCustomer(UUID id, Customer Customer);
    List<Customer> getCustomers();

    default Customer insertCustomer(Customer Customer) {
        UUID id = UUID.randomUUID();
        return insertCustomer(id, Customer);
    }

    int deleteCustomerById(UUID id);
    Optional<Customer> getCustomer(UUID id);
    Customer updateCustomer(UUID id, Customer Customer);
}
