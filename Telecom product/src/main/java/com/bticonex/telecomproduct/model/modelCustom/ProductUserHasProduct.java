package com.bticonex.telecomproduct.model.modelCustom;

import com.bticonex.telecomproduct.model.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ognjen on 6/20/2019
 */
@SqlResultSetMapping(
        name = "ProductUserHasProductMapping",
        classes = @ConstructorResult(
                targetClass = ProductUserHasProduct.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "upstream"),
                        @ColumnResult(name = "downstream"),
                        @ColumnResult(name = "technology_type"),
                        @ColumnResult(name = "device_type"),
                        @ColumnResult(name = "deleted", type = Byte.class),
                        @ColumnResult(name = "user_has_product_id"),
                        @ColumnResult(name = "user_id"),
                        @ColumnResult(name = "activation_date", type = java.util.Date.class),
                        @ColumnResult(name = "active", type = Byte.class)
                }
        )
)
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUserHasProduct extends Product {

    private Integer userHasProductId;
    private Integer userId;
    private Date activationDate;
    private Byte active;

    public ProductUserHasProduct(Integer id, Integer upstream, Integer downstream, String technologyType, String deviceType, Byte deleted, Integer usereHasProductId, Integer userId, java.util.Date activationDate, Byte active) {
        super();

        this.setId(id);
        this.setUpstream(upstream);
        this.setDownstream(downstream);
        this.setTechnologyType(technologyType);
        this.setDeviceType(deviceType);
        this.setDeleted(deleted);

        this.userHasProductId = usereHasProductId;
        this.userId = userId;
        this.activationDate = new Date(activationDate.getTime());
        this.active = active;
    }


    public Integer getUserHasProductId() {
        return userHasProductId;
    }

    public void setUserHasProductId(Integer userHasProductId) {
        this.userHasProductId = userHasProductId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
