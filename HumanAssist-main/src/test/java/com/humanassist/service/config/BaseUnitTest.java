package com.humanassist.service.config;

import com.humanassist.service.CounselorService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class BaseUnitTest {
//    @Bean
//    @Primary
//    public CounselorService counselorService() {
//        return Mockito.mock(CounselorService.class);
//    }
}
