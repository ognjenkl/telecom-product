package com.bticonex.telecomproduct.repository.repositoryCustom;

import com.bticonex.telecomproduct.model.modelCustom.ProductUserHasProduct;

import java.util.List;

/**
 * Created by ognjen on 6/20/2019
 */
public interface ProductRepositoryCustom {

    List<ProductUserHasProduct> getAllActiveCustom();

    ProductUserHasProduct getActiveCustom(Integer userId);
}
