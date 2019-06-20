package com.bticonex.telecomproduct.conroller;

import com.bticonex.telecomproduct.common.BadRequestException;
import com.bticonex.telecomproduct.model.UserHasProduct;
import com.bticonex.telecomproduct.model.modelCustom.ProductUserHasProduct;
import com.bticonex.telecomproduct.repository.ProductRepository;
import com.bticonex.telecomproduct.repository.UserHasProductRepository;
import com.rits.cloning.Cloner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ognjen on 6/20/2019
 */
@RestController
@RequestMapping("/product")
@Scope("request")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserHasProductRepository userHasProductRepository;

    Cloner cloner;

    @GetMapping
    public List<ProductUserHasProduct> getAll() throws BadRequestException {
        List<ProductUserHasProduct> productUserHasProducts = productRepository.getAllActiveCustom();
        if (productUserHasProducts != null && productUserHasProducts.size() > 0)
            return productUserHasProducts;
        throw new BadRequestException("Bad request");
    }

    @GetMapping("/{id}")
    public ProductUserHasProduct getByUserId(@PathVariable("id") Integer userId) throws BadRequestException {
        ProductUserHasProduct productUserHasProduct = productRepository.getActiveCustom(userId);
        if (productUserHasProduct != null)
            return productUserHasProduct;
        else
            throw new BadRequestException("Bad request");
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ProductUserHasProduct insert(@RequestBody UserHasProduct userHasProduct) throws BadRequestException {
        UserHasProduct userHasProductTmp = null;
        userHasProduct.setActivationDate(new Timestamp(new java.util.Date().getTime()));
        userHasProduct.setActive((byte) 1);
        List<UserHasProduct> activeUserHasProducts = userHasProductRepository.getAllByUserIdAndActive(userHasProduct.getUserId(), (byte) 1);
        if (activeUserHasProducts != null && activeUserHasProducts.size() > 0) {
            for(UserHasProduct userHasProd : activeUserHasProducts) {
                userHasProductRepository.updateActive(userHasProd.getId(), (byte) 0);
            }
        }
        if ((userHasProductTmp = userHasProductRepository.saveAndFlush(userHasProduct)) != null) {
            ProductUserHasProduct productUserHasProduct = productRepository.getActiveCustom(userHasProduct.getProductId());
            return productUserHasProduct;
        }
        throw new BadRequestException("Bad request");
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public @ResponseBody
//    String update(@PathVariable ("id") Integer userId, @RequestBody ProductUserHasProduct productUserHasProduct) throws BadRequestException,ForbiddenException {
//        ProductUserHasProduct oldObject = cloner.deepClone(userHasProductRepository.getActiveCustom(userId));
//        if (userHasProductRepository.saveAndFlush(productUserHasProduct) != null) {
//            return "Success";
//        }
//        throw new BadRequestException("Bad request");
//    }
//
//    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
//    public @ResponseBody
//    String delete(@PathVariable Integer userId) throws BadRequestException {
//        try {
//            ProductUserHasProduct productUserHasProduct = userHasProductRepository.getActiveCustom(userId);
//            userHasProductRepository.deleteCustom(productUserHasProduct);
//            return "Success";
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new BadRequestException("Bad Request");
//        }
//    }

}
