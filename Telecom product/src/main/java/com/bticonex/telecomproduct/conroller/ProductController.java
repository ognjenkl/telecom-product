package com.bticonex.telecomproduct.conroller;

import com.bticonex.telecomproduct.common.BadRequestException;
import com.bticonex.telecomproduct.model.UserHasProduct;
import com.bticonex.telecomproduct.model.modelCustom.ProductUserHasProduct;
import com.bticonex.telecomproduct.repository.ProductRepository;
import com.bticonex.telecomproduct.repository.UserHasProductRepository;
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

    /**
     * Get all the subscriptions for all userIds.
     *
     * @return list of subscriptions
     * @throws BadRequestException
     */
    @GetMapping
    public List<ProductUserHasProduct> getAll() throws BadRequestException {
        List<ProductUserHasProduct> productUserHasProducts = productRepository.getAllActiveCustom();
        if (productUserHasProducts != null && productUserHasProducts.size() > 0)
            return productUserHasProducts;
        throw new BadRequestException("Bad request");
    }

    /**
     * Finds the subscription for <code>userId</code>.
     *
     * @param userId fow which subscription is seeked.
     * @return the subscription for userId
     */
    @GetMapping("/{id}")
    public ProductUserHasProduct getByUserId(@PathVariable("id") Integer userId) {
        try {
            ProductUserHasProduct productUserHasProduct = productRepository.getActiveByUserIdCustom(userId);
            return productUserHasProduct;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates subscription by userId and productId.
     * POST request body carries the data in JSON format, to create a subscription.
     *
     * @param userHasProduct subscription data.
     * @return newly created subscription.
     * @throws BadRequestException
     */
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductUserHasProduct insert(@RequestBody UserHasProduct userHasProduct) throws BadRequestException {

        // set other params about subscription
        userHasProduct.setActivationDate(new Timestamp(new java.util.Date().getTime()));
        userHasProduct.setActive((byte) 1);

        // deactivate previous subscriptions for userId
        List<UserHasProduct> activeUserHasProducts = userHasProductRepository.getAllByUserIdAndActive(userHasProduct.getUserId(), (byte) 1);
        if (activeUserHasProducts != null && activeUserHasProducts.size() > 0) {
            for (UserHasProduct userHasProd : activeUserHasProducts) {
                userHasProductRepository.updateActive(userHasProd.getId(), (byte) 0);
            }
        }

        // activate subscription for userId
        if (userHasProductRepository.saveAndFlush(userHasProduct) != null) {
            ProductUserHasProduct productUserHasProduct = productRepository.getActiveByUserIdCustom(userHasProduct.getUserId());
            return productUserHasProduct;
        }
        throw new BadRequestException("Bad request");
    }

    /**
     * Changes subscription for userId.
     *
     * @param userHasProduct data for subscription changes.
     * @return <code>Success</code> if the subscription change is made successfully.
     * @throws BadRequestException
     */
    @PutMapping
    public String update(@RequestBody UserHasProduct userHasProduct) throws BadRequestException {
        UserHasProduct activeUserHasProduct = userHasProductRepository.getByUserIdAndActive(userHasProduct.getUserId(), (byte) 1);
        activeUserHasProduct.setProductId(userHasProduct.getProductId());
        if (userHasProductRepository.saveAndFlush(activeUserHasProduct) != null) {
            return "Success";
        }
        throw new BadRequestException("Bad request");
    }

    /**
     * Deactivates subscription for userId.
     *
     * @param userId for which the subscription is deactivated.
     * @return <code>Success</code> if the subscription deactivation is successfully.
     * @throws BadRequestException
     */
    @DeleteMapping(value = {"/{id}"})
    public String delete(@PathVariable("id") Integer userId) throws BadRequestException {
        try {
            UserHasProduct userHasProduct = userHasProductRepository.getByUserIdAndActive(userId, (byte) 1);
            userHasProductRepository.updateActive(userHasProduct.getId(), (byte) 0);
            return "Success";
        } catch (Exception e) {
            throw new BadRequestException("Bad Request");
        }
    }
}
