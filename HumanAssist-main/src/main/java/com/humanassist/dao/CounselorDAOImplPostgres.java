package com.humanassist.dao;

import com.humanassist.domain.Counselor;
import com.humanassist.domain.CounselorResponse;
import com.humanassist.mapper.CounselorRowMapper;
import com.humanassist.scope.HumanAssistThreadLocal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("postgres")
public class CounselorDAOImplPostgres implements CounselorDAO{
    private JdbcTemplate jdbcTemplate;

    Logger logger = Logger.getLogger(CounselorDAOImplPostgres.class.getCanonicalName());
    public CounselorDAOImplPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public CounselorResponse insertCounselor(UUID id, Counselor counselor) {
        String sql = """
                    INSERT INTO\s""" +  setSchemaToTableName("counselor") + """
                (id, name, phoneNo, memberSince, address) VALUES(?,?,?,?,?);
                """;
        jdbcTemplate.update(sql, id, counselor.getPerson().getName(), counselor.getPerson().getPhoneNo(),
                counselor.getMemberSince(), counselor.getPerson().getAddress());
        counselor.setId(id);
        return new CounselorResponse(counselor, LocalDate.now());
    }

    @Override
    public List<Counselor> getCounselors() {
        return jdbcTemplate.query("Select * from " +  setSchemaToTableName("counselor"), new CounselorRowMapper());
    }

    @Override
    public int deleteCounselorById(UUID id) {
        return jdbcTemplate.update("Delete from " + setSchemaToTableName("counselor") +" where id = ?", id.toString());
    }

    @Override
    public Optional<Counselor> getCounselor(UUID id) {
        logger.log(Level.INFO, "Fetching Counselor details from DB for id = {0}", new String[]{id.toString()});
        String query = "Select * from " + setSchemaToTableName("counselor") +" where id = ?";
        List<Counselor> counselors = jdbcTemplate.query(query, new CounselorRowMapper(), id.toString());
        return counselors.stream().findFirst();
    }

    @Override
    public Counselor updateCounselor(UUID id, Counselor counselor) {
        String query = "Update " +  setSchemaToTableName("counselor") + " set name = ?, phoneNo = ?, memberSince = ?, address = ? where id = ?";
        Integer count = jdbcTemplate.update(query, counselor.getPerson().getName(), counselor.getPerson().getPhoneNo(),
                counselor.getMemberSince(), counselor.getPerson().getAddress(), id.toString());
        counselor.setId(id);
        return count > 0 ? counselor : null;
    }

    private String setSchemaToTableName(String tableName) {
        String schemaName = HumanAssistThreadLocal.getThreadLocal().toString();
        logger.log(Level.INFO, "Going to use {0} schema to set to tableName ::: {1}", new String[] {schemaName, tableName});
        return schemaName + "." + tableName;
    }

}
