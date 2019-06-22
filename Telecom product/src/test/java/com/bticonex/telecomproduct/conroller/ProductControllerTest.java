package com.bticonex.telecomproduct.conroller;

import com.bticonex.telecomproduct.TelecomProductApplication;
import com.bticonex.telecomproduct.model.UserHasProduct;
import com.bticonex.telecomproduct.model.modelCustom.ProductUserHasProduct;
import com.bticonex.telecomproduct.repository.UserHasProductRepository;
import com.bticonex.telecomproduct.repository.repositoryCustom.ProductRepositoryCustom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

/**
 * Created by ognjen on 6/22/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TelecomProductApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;

    private String getRootUrl() {
        return "/product";
    }

    @Test
    public void testGetAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class);
        System.out.println("test getAll");
        System.out.println(response.getBody());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetByUserId() {
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/1", ProductUserHasProduct.class);
        System.out.println("test getByUserId()");
        System.out.println(productUserHasProduct);
    }

    @Test
    public void testInsert() {
        UserHasProduct userHasProduct = new UserHasProduct();
        userHasProduct.setUserId(5);
        userHasProduct.setProductId(1);
        userHasProduct.setActivationDate(new Timestamp(new java.util.Date().getTime()));
        userHasProduct.setActive((byte) 1);

//        ResponseEntity<UserHasProduct> postResponse = testRestTemplate.postForEntity(getRootUrl(), userHasProduct, UserHasProduct.class);
        ProductUserHasProduct insertedProductUserHasProduct = testRestTemplate.postForObject(getRootUrl(), userHasProduct, ProductUserHasProduct.class);
//        Assert.assertNotNull(postResponse);
//        Assert.assertNotNull(postResponse.getBody());
        Assert.assertNotNull(insertedProductUserHasProduct);
        System.out.println("test insert");
//        System.out.println(postResponse.getBody());
        System.out.println(insertedProductUserHasProduct);



    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Integer updateProductValue = 2;
        ProductUserHasProduct productUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + id, ProductUserHasProduct.class);
        UserHasProduct userHasProduct = new UserHasProduct();
        userHasProduct.setId(productUserHasProduct.getUserHasProductId());
        userHasProduct.setUserId(productUserHasProduct.getUserId());
        userHasProduct.setProductId(updateProductValue);
        userHasProduct.setActivationDate(new Timestamp(productUserHasProduct.getActivationDate().getTime()));
        userHasProduct.setActive(productUserHasProduct.getActive());

        testRestTemplate.put(getRootUrl() + "/" + id, userHasProduct);
        ProductUserHasProduct updateProductUserHasProduct = testRestTemplate.getForObject(getRootUrl() + "/" + id, ProductUserHasProduct.class);
        Assert.assertNotNull(updateProductUserHasProduct);
        Assert.assertEquals(updateProductValue, updateProductUserHasProduct.getId());
    }

//    @Test
//    public void testDelete() {
//    }
}