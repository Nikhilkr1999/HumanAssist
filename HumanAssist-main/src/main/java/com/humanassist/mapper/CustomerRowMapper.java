package com.humanassist.mapper;

import com.humanassist.domain.Customer;
import com.humanassist.domain.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class CustomerRowMapper implements RowMapper<Customer> {
    /**
     * Implementations must implement this method to map each row of data in the
     * {@code ResultSet}. This method should not call {@code next()} on the
     * {@code ResultSet}; it is only supposed to map values of the current row.
     *
     * @param rs     the {@code ResultSet} to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered while getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                UUID.fromString(rs.getString("id")),
                rs.getInt("planId"),
                LocalDate.parse(rs.getString("memberSince")),
                new Person(rs.getString("name"),
                        rs.getString("phoneNo"),
                        rs.getString("address")
                )
        );
    }
}
