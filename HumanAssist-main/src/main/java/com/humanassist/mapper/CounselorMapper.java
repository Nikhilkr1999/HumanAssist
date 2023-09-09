package com.humanassist.mapper;

import org.springframework.data.jdbc.repository.query.Query;

public class CounselorMapper {

    @Query("""
            Select * from Counselor where id = ?""")
    public String getCounselorQuery() {
        return null;
    }
}
