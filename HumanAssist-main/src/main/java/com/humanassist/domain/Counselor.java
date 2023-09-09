package com.humanassist.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Counselor implements Serializable {

    private static final long serialVersionUID = 1L;
    private UUID id;
    private LocalDate memberSince;
    private Person person;

    public Counselor(UUID id, LocalDate memberSince, Person person) {
        this.id = id;
        this.memberSince = memberSince;
        this.person = person;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setId(UUID id) {
        this.id = id;
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
