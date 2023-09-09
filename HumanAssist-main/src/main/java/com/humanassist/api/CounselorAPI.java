package com.humanassist.api;

import com.humanassist.domain.Counselor;
import com.humanassist.domain.CounselorResponse;
import com.humanassist.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/counselor")
@RestController
public class CounselorAPI {

    private CounselorService counselorService;

    @Autowired
    public CounselorAPI(CounselorService counselorService) {
        this.counselorService = counselorService;
    }

    @PostMapping
    public CounselorResponse addCounselor(@RequestBody Counselor counselor) {
        return this.counselorService.addCounselor(counselor);
    }
    @GetMapping
    public List<Counselor> getCounselors() {
        return this.counselorService.getCounselors();
    }

    @GetMapping(path = "{id}")
    public Counselor getCounselor(@PathVariable("id") UUID id) {
        return this.counselorService.getCounselor(id);
    }

    @PutMapping(path="{id}")
    public CounselorResponse updateCounselor(@PathVariable("id") UUID id, @RequestBody Counselor counselor) {
        Counselor updatedCounselor = this.counselorService.updateCounselor(id, counselor);
        return new CounselorResponse(updatedCounselor, LocalDate.now());
    }

    @DeleteMapping(path="{id}")
    public int deleteCounselor(@PathVariable("id") UUID id) {
        return this.counselorService.deleteCounselorById(id);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello India";
    }
}
