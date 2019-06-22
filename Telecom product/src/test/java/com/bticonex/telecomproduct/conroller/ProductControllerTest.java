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
import org.springframework.boot.web.server.LocalServerPort;
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

    @LocalServerPort
    private Integer port;

    private String getRootUrl() {
        return "/product";
    }

    private Integer userId = 5;

    @Test
    @Order(2)
    public void testGetAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println("test getAll");
        System.out.println(response.getBody());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    public void testGetByUserId() {
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + userId, ProductUserHasProduct.class);
        System.out.println("test getByUserId()");
        System.out.println(productUserHasProduct);
    }

    @Test
    @Order(1)
    public void testInsert() {
        UserHasProduct userHasProduct = new UserHasProduct();
        userHasProduct.setUserId(userId);
        userHasProduct.setProductId(1);
        userHasProduct.setActivationDate(new Timestamp(new java.util.Date().getTime()));
        userHasProduct.setActive((byte) 1);

        ProductUserHasProduct insertedProductUserHasProduct = testRestTemplate.postForObject(getRootUrl(), userHasProduct, ProductUserHasProduct.class);
        Assert.assertNotNull(insertedProductUserHasProduct);
        System.out.println("test insert");
        System.out.println(insertedProductUserHasProduct);


    }

    @Test
    @Order(4)
    public void testUpdate() {
        Integer updateProductValue = 2;
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + userId, ProductUserHasProduct.class);
        UserHasProduct userHasProduct = new UserHasProduct();
        userHasProduct.setId(productUserHasProduct.getUserHasProductId());
        userHasProduct.setUserId(productUserHasProduct.getUserId());
        userHasProduct.setProductId(updateProductValue);
        userHasProduct.setActivationDate(new Timestamp(productUserHasProduct.getActivationDate().getTime()));
        userHasProduct.setActive(productUserHasProduct.getActive());

        testRestTemplate.put(getRootUrl() + "/" + userId, userHasProduct);
        ProductUserHasProduct updateProductUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + userId, ProductUserHasProduct.class);
        Assert.assertNotNull(updateProductUserHasProduct);
        Assert.assertEquals(updateProductValue, updateProductUserHasProduct.getId());
    }

    @Test
    @Order(5)
    public void testDelete() {
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + userId, ProductUserHasProduct.class);
            Assert.assertNotNull(productUserHasProduct);
            testRestTemplate.delete(getRootUrl() + "/" + userId);

            ProductUserHasProduct deletedProductUserHasProduct = null;
            deletedProductUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + userId, ProductUserHasProduct.class);
            Assert.assertNull(deletedProductUserHasProduct);
    }
}