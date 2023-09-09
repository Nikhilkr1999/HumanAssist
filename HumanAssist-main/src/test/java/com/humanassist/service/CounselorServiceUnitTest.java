package com.humanassist.service;

import com.humanassist.dao.CounselorDAO;
import com.humanassist.dao.CounselorDAOImplPostgres;
import com.humanassist.domain.Counselor;
import com.humanassist.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class CounselorServiceUnitTest {

    @Autowired
    private CounselorService counselorService;

    @Mock
    private CounselorDAO counselorDao;


    @BeforeEach
    public void initialize() {
        this.counselorDao = Mockito.mock(CounselorDAOImplPostgres.class);
        this.counselorService = new CounselorService(this.counselorDao);
    }
    @Test
    public void testGetCounselor() {
        UUID uid = UUID.randomUUID();
        when(counselorDao.getCounselor(uid)).thenReturn(Optional.of(new Counselor(uid, LocalDate.now(), new Person("Ram", "12345", "London"))));
        Assertions.assertEquals("Ram", counselorService.getCounselor(uid).getPerson().getName());
    }
}
