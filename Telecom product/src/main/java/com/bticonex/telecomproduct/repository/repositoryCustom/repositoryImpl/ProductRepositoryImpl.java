package com.bticonex.telecomproduct.repository.repositoryCustom.repositoryImpl;

import com.bticonex.telecomproduct.model.modelCustom.ProductUserHasProduct;
import com.bticonex.telecomproduct.repository.repositoryCustom.ProductRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ognjen on 6/20/2019
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private static final String SQL_GET_ALL_ACTIVE_CUSTOM = "select p.*, uhp.id as user_has_product_id, uhp.user_id, uhp.activation_date, uhp.active from user_has_product uhp join product p on uhp.product_id = p.id where uhp.active = 1";
    private static final String SQL_GET_ACTIVE_BY_USER_ID_CUSTOM = "select p.*, uhp.id as user_has_product_id, uhp.user_id, uhp.activation_date, uhp.active from user_has_product uhp join product p on uhp.product_id = p.id where uhp.active = 1 and uhp.user_id = ?";

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductUserHasProduct> getAllActiveCustom() {
        return entityManager.createNativeQuery(SQL_GET_ALL_ACTIVE_CUSTOM, "ProductUserHasProductMapping")
                .getResultList();
    }

    @Override
    public ProductUserHasProduct getActiveByUserIdCustom(Integer userId) {
        return (ProductUserHasProduct) entityManager.createNativeQuery(SQL_GET_ACTIVE_BY_USER_ID_CUSTOM, "ProductUserHasProductMapping")
                .setParameter(1, userId)
                .getSingleResult();

    }
}
