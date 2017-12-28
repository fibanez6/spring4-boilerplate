package com.fibanez.springboot.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Ad_Format")
public class AdFormat implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty
    @Column(name="format", nullable=false)
    private String format;

    public AdFormat() {
    }

    public AdFormat(Long id, String format) {
        this.id = id;
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdFormat adFormat = (AdFormat) o;
        return Objects.equals(id, adFormat.id) &&
                Objects.equals(format, adFormat.format);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, format);
    }
}