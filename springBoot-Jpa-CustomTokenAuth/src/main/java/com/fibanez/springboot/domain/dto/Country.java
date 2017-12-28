package com.fibanez.springboot.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty
    @Column(name="country", nullable=false)
    private String country;

    @NotEmpty
    @Column(name="code", nullable=false)
    private String code;

    public Country() {
    }

    public Country(Long id, String country, String code) {
        this.id = id;
        this.country = country;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
