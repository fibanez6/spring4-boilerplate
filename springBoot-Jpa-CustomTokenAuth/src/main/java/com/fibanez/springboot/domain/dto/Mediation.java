package com.fibanez.springboot.domain.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Mediation")
public class Mediation implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="network")
    private Network network;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="mediation")
    private Set<AdConfig> adConfigs = Collections.emptySet();

    public Mediation() {
    }

    public Mediation(Long id, Network network, Set<AdConfig> adConfigs) {
        this.id = id;
        this.network = network;
        this.adConfigs = adConfigs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Set<AdConfig> getAdConfigs() {
        return adConfigs;
    }

    public void setAdConfigs(Set<AdConfig> adConfigs) {
        this.adConfigs = adConfigs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mediation mediation = (Mediation) o;
        return Objects.equals(id, mediation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
