package com.humanassist.service;

import com.humanassist.dao.CounselorDAO;
import com.humanassist.domain.Counselor;
import com.humanassist.domain.CounselorResponse;
import com.humanassist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@CacheConfig(cacheNames = "counselor")
@Service
public class CounselorService {

    private final CounselorDAO counselorDAOImpl;

    private Logger logger = Logger.getLogger(CounselorService.class.getCanonicalName());

    @Autowired
    public CounselorService(@Qualifier("postgres") CounselorDAO counselorDAOImpl) {
        this.counselorDAOImpl = counselorDAOImpl;
    }

    public CounselorResponse addCounselor(Counselor counselor) {
        return this.counselorDAOImpl.insertCounselor(counselor);
    }

    public List<Counselor> getCounselors() {
        return this.counselorDAOImpl.getCounselors();
    }

    @CacheEvict(key= "#id")
    public int deleteCounselorById(UUID id) {
        return this.counselorDAOImpl.deleteCounselorById(id);
    }

    @Cacheable(key = "#id")
    public Counselor getCounselor(UUID id) {
        return this.counselorDAOImpl.getCounselor(id).orElseThrow(() -> {
            logger.log(Level.SEVERE, "No Counselor exists with id :: " + id);
            return new NotFoundException("No Counselor exists with id :: " + id);
        });
    }

    /**
     * @CachePut saves the returned Object under "VALUE" cache name with given key
     * @param id
     * @param counselor
     * @return Updated Counselor
     */
    @CachePut(key = "#id", value = "counselor")
    public Counselor updateCounselor(UUID id, Counselor counselor) {
        Counselor updatedCounselor = this.counselorDAOImpl.updateCounselor(id, counselor);
        return updatedCounselor;
    }
}

