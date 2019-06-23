package com.bticonex.telecomproduct.conroller;

import com.bticonex.telecomproduct.TelecomProductApplication;
import com.bticonex.telecomproduct.model.UserHasProduct;
import com.bticonex.telecomproduct.model.modelCustom.ProductUserHasProduct;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

/**
 * Created by ognjen on 6/22/2019
 */
@SpringBootTest(classes = TelecomProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ProductControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private Integer userId = 5;

    @Test
    @Order(2)
    public void testGetAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/product", HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    public void testGetByUserId() {
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject("/product/" + userId, ProductUserHasProduct.class);
        Assert.assertNotNull(productUserHasProduct);
    }

    @Test
    @Order(1)
    public void testInsert() {
        // new subscription data
        UserHasProduct userHasProduct = new UserHasProduct();
        userHasProduct.setUserId(userId);
        userHasProduct.setProductId(1);
        userHasProduct.setActivationDate(new Timestamp(new java.util.Date().getTime()));
        userHasProduct.setActive((byte) 1);

        // activate new subscription
        ProductUserHasProduct insertedProductUserHasProduct = testRestTemplate.postForObject("/product", userHasProduct, ProductUserHasProduct.class);

        // check if new subscription is activated
        Assert.assertNotNull(insertedProductUserHasProduct);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        // new subscription data to make change
        Integer updateProductValue = 2;
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject("/product/" + userId, ProductUserHasProduct.class);
        UserHasProduct userHasProduct = new UserHasProduct();
        userHasProduct.setId(productUserHasProduct.getUserHasProductId());
        userHasProduct.setUserId(productUserHasProduct.getUserId());
        userHasProduct.setProductId(updateProductValue);
        userHasProduct.setActivationDate(new Timestamp(productUserHasProduct.getActivationDate().getTime()));
        userHasProduct.setActive(productUserHasProduct.getActive());

        // make change to subscription
        testRestTemplate.put("/product", userHasProduct);

        // check if subscription is changed for user userId
        ProductUserHasProduct updateProductUserHasProduct = testRestTemplate.getForObject("/product/" + userId, ProductUserHasProduct.class);
        Assert.assertNotNull(updateProductUserHasProduct);
        Assert.assertEquals(updateProductValue, updateProductUserHasProduct.getId());
    }

    @Test
    @Order(5)
    public void testDelete() {
        // check if there is subscription for userId to deactivate
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject("/product/" + userId, ProductUserHasProduct.class);
        Assert.assertNotNull(productUserHasProduct);

        // deactivate subscription for userId
        testRestTemplate.delete("/product/" + userId);

        // check if subscription is deactivated for userId
        ProductUserHasProduct deletedProductUserHasProduct = testRestTemplate.getForObject("/product/" + userId, ProductUserHasProduct.class);
        Assert.assertNull(deletedProductUserHasProduct);
    }
}