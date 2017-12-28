package com.fibanez.springboot.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long id;

    @NotEmpty
    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<NetworkTokenAuth> tokenAuths = Collections.emptyList();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="account")
    private Set<Application> applications = Collections.emptySet();

    public Account() {
    }

    public Account(Long id, String name, List<NetworkTokenAuth> tokenAuths, Set<Application> applications) {
        this.id = id;
        this.name = name;
        this.tokenAuths = tokenAuths;
        this.applications = applications;
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

    public List<NetworkTokenAuth> getTokenAuths() {
        return tokenAuths;
    }

    public void setTokenAuths(List<NetworkTokenAuth> tokenAuths) {
        this.tokenAuths = tokenAuths;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
