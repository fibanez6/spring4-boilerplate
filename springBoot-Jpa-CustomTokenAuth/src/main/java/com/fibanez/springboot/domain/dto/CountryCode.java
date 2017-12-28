package com.fibanez.springboot.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Country_Code")
public class CountryCode implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="country")
    private Country country;

    @NotEmpty
    @Column(name="code", nullable=false)
    private String code;

    @ManyToMany(mappedBy = "countryCodes")
    private Set<Network> networks = Collections.emptySet();

    public CountryCode() {
    }

    public CountryCode(Long id, Country country, String code) {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(Set<Network> networks) {
        this.networks = networks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryCode that = (CountryCode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code);
    }
}
