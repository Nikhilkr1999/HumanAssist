package com.humanassist.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JWTClaims implements Serializable {

    private static final long serialVersionUID = 123L;
    //@JsonProperty("schema")
    private String schema;
    //@JsonProperty("ownerEmail")
    private String ownerEmail;
    //public JWTClaims(){}

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
