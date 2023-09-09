package com.humanassist.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private Integer planId;
    private LocalDate memberSince;
    private Person person;

    public Customer(UUID id, Integer planId, LocalDate memberSince, Person person) {
        this.id = id;
        this.planId = planId;
        this.memberSince = memberSince;
        this.person = person;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
