package com.humanassist.dao;

import com.humanassist.domain.Counselor;
import com.humanassist.domain.CounselorResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("inMemory")
public class CounselorDAOImpl implements CounselorDAO{

    private List<Counselor> CL = new ArrayList<>();
    public CounselorResponse insertCounselor(UUID id, Counselor counselor) {
        counselor.setId(id);
        CL.add(counselor);
        return new CounselorResponse(counselor, LocalDate.now());
    }

    @Override
    public List<Counselor> getCounselors() {
        return CL;
    }

    @Override
    public int deleteCounselorById(UUID id) {
        Optional<Counselor> counselor = getCounselor(id);
        if(counselor.isEmpty()) {
            return 0;
        }
        CL.remove(counselor.get());
        return 1;
    }

    @Override
    public Optional<Counselor> getCounselor(UUID id) {
        return CL.stream().filter(counselor -> counselor.getId().equals(id)).findFirst();
    }

    @Override
    public Counselor updateCounselor(UUID id, Counselor counselor) {
        counselor.setId(id);
        return getCounselor(id).map(c -> {
            int indexToDelete = CL.indexOf(c);
            if(indexToDelete >= 0) {
                CL.set(indexToDelete, counselor);
            }
            return counselor;
        }).orElse(null);
    }
}
