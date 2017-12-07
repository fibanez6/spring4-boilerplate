package com.fibanez.spring4basic.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by fernandoi on 07/12/17.
 */
public class Address implements Serializable {

    @Size(min=3, max=30)
    @NotBlank
    private String address;

    @Size(min=3, max=30)
    @NotBlank
    private String postcode;

    public Address() {
    }

    public Address(String address, String postcode) {
        this.address = address;
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
