package com.fibanez.springboot.domain.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Ad_Config")
public class AdConfig implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="format")
    private AdFormat format;

    @Column(name="enable")
    private Boolean enable = false;

    public AdConfig() {
    }

    public AdConfig(Long id, AdFormat format, Boolean enable) {
        this.id = id;
        this.format = format;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdFormat getFormat() {
        return format;
    }

    public void setFormat(AdFormat format) {
        this.format = format;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdConfig adConfig = (AdConfig) o;
        return Objects.equals(id, adConfig.id) &&
                Objects.equals(enable, adConfig.enable);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, enable);
    }
}
