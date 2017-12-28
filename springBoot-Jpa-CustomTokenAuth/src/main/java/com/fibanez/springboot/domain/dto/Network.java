package com.fibanez.springboot.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Network")
public class Network implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty
    @Column(name="name", nullable=false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Network_Countrycodes"
            , joinColumns = @JoinColumn(name = "network_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "country_code_id", referencedColumnName = "id"))
    private Set<CountryCode> countryCodes = Collections.emptySet();

    public Network() {
    }

    public Network(Long id, String name, Set<CountryCode> countryCodes) {
        this.id = id;
        this.name = name;
        this.countryCodes = countryCodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CountryCode> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(Set<CountryCode> countryCodes) {
        this.countryCodes = countryCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return Objects.equals(id, network.id) &&
                Objects.equals(name, network.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}