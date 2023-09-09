package com.humanassist.domain;

import java.time.LocalDate;

public class CounselorResponse {
    private Counselor counselor;
    private LocalDate addedOn;
    public CounselorResponse(Counselor counselor, LocalDate addedOn) {
        this.counselor = counselor;
        this.addedOn = addedOn;
    }

    public Counselor getCounselor() {
        return counselor;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }
}
