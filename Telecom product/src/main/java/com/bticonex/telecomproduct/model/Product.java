package com.bticonex.telecomproduct.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by ognjen on 6/20/2019
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    private Integer id;
    private Integer upstream;
    private Integer downstream;
    private String technologyType;
    private String deviceType;
    private Byte deleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "upstream", nullable = true)
    public Integer getUpstream() {
        return upstream;
    }

    public void setUpstream(Integer upstream) {
        this.upstream = upstream;
    }

    @Basic
    @Column(name = "downstream", nullable = true)
    public Integer getDownstream() {
        return downstream;
    }

    public void setDownstream(Integer downstream) {
        this.downstream = downstream;
    }

    @Basic
    @Column(name = "technology_type", nullable = true, length = 45)
    public String getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    @Basic
    @Column(name = "device_type", nullable = true, length = 45)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Basic
    @Column(name = "deleted", nullable = true)
    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(upstream, product.upstream) &&
                Objects.equals(downstream, product.downstream) &&
                Objects.equals(technologyType, product.technologyType) &&
                Objects.equals(deviceType, product.deviceType) &&
                Objects.equals(deleted, product.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, upstream, downstream, technologyType, deviceType, deleted);
    }
}
