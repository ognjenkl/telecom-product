package com.bticonex.telecomproduct.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by ognjen on 6/20/2019
 */
@Entity
@Table(name = "user_has_product", schema = "telecom_product", catalog = "")
public class UserHasProduct {
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    /**
     * Indicates when subscription was activated
     */
    private Timestamp activationDate;
    /**
     * Indicates if subscription is activated
     */
    private Byte active;


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
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "product_id", nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "activation_date", nullable = true)
    public Timestamp getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Timestamp activationDate) {
        this.activationDate = activationDate;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasProduct that = (UserHasProduct) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(activationDate, that.activationDate) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, activationDate, active);
    }
}
