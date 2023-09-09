package com.humanassist.dao;

import com.humanassist.domain.Counselor;
import com.humanassist.domain.CounselorResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CounselorDAO {
    CounselorResponse insertCounselor(UUID id, Counselor counselor);
    List<Counselor> getCounselors();

    default CounselorResponse insertCounselor(Counselor counselor) {
        UUID id = UUID.randomUUID();
        return insertCounselor(id, counselor);
    }

    int deleteCounselorById(UUID id);
    Optional<Counselor> getCounselor(UUID id);
    Counselor updateCounselor(UUID id, Counselor counselor);
}
