package com.humanassist.mapper;

import com.humanassist.domain.Counselor;
import com.humanassist.domain.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;


public class CounselorRowMapper implements RowMapper<Counselor> {
    @Override
    public Counselor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Counselor(
                UUID.fromString(rs.getString("id")),
                LocalDate.parse(rs.getString("memberSince")),
                new Person(rs.getString("name"),
                        rs.getString("phoneNo"),
                        rs.getString("address"))
        );
    }
}
