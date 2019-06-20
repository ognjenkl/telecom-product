package com.bticonex.telecomproduct.repository.repositoryCustom.repositoryImpl;

import com.bticonex.telecomproduct.repository.repositoryCustom.UserHasProductRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by ognjen on 6/20/2019
 */
public class UserHasProductRepositoryImpl implements UserHasProductRepositoryCustom {

    private static final String SQL_UPDATE_ACTIVE = "UPDATE user_has_product SET active=? WHERE id=?";

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Integer updateActive(Integer userHasProductId, Byte actitve) {
        Query query = entityManager.createNativeQuery(SQL_UPDATE_ACTIVE);
        query.setParameter(1, actitve);
        query.setParameter(2, userHasProductId);
        return query.executeUpdate();
    }
}
