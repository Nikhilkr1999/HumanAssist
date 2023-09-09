package com.humanassist.dao;

import com.humanassist.domain.Customer;
import com.humanassist.mapper.CustomerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("customer-postgres")
public class CustomerDAOImpl implements CustomerDAO{

    private Logger logger = Logger.getLogger(CustomerDAOImpl.class.getCanonicalName());
    private JdbcTemplate jdbcTemplate;
    
    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * @param id
     * @param customer
     * @return
     */
    @Override
    public Customer insertCustomer(UUID id, Customer customer) {
        logger.log(Level.INFO, "Inserting customer ::: " + customer);
        String query = """
                    INSERT INTO CUSTOMER (id, name, phoneNo, memberSince, address, planId) VALUES(?,?,?,?,?,?);
                """;
        jdbcTemplate.update(query, id.toString(), customer.getPerson().getName(), customer.getPerson().getPhoneNo(), customer.getMemberSince(),
                customer.getPerson().getAddress(), customer.getPlanId());
        customer.setId(id);
        return customer;
    }

    /**
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
        String query = """
                    SELECT * from CUSTOMER;
                """;
        return jdbcTemplate.query(query, new CustomerRowMapper());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteCustomerById(UUID id) {
        return jdbcTemplate.update("Delete from CUSTOMER where id = ?", id.toString());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Customer> getCustomer(UUID id) {
        logger.log(Level.INFO, "Fetching customer with id ::: " + id);
        String query = """
                SELECT * from CUSTOMER where id = ?
                """;
        return jdbcTemplate.query(query, new CustomerRowMapper(), id.toString()).stream().findFirst();
    }

    /**
     * @param id
     * @param customer
     * @return
     */
    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        String query = "Update CUSTOMER set name = ?, phoneNo = ?, memberSince = ?, address = ?, planId = ?, where id = ?";
        Integer count = jdbcTemplate.update(query, customer.getPerson().getName(), customer.getPerson().getPhoneNo(),
                customer.getMemberSince(), customer.getPerson().getAddress(), id.toString());
        customer.setId(id);
        return count > 0 ? customer : null;
    }
}
