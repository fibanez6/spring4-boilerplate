package com.fibanez.springboot.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Application")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty
    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="application")
    private Set<Mediation> mediations = Collections.emptySet();

    public Application() {
    }

    public Application(Long id, String name, Set<Mediation> mediations) {
        this.id = id;
        this.name = name;
        this.mediations = mediations;
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

    public Set<Mediation> getMediations() {
        return mediations;
    }

    public void setMediations(Set<Mediation> mediations) {
        this.mediations = mediations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
