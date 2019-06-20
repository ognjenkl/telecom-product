package com.bticonex.telecomproduct.repository;

import com.bticonex.telecomproduct.model.UserHasProduct;
import com.bticonex.telecomproduct.repository.repositoryCustom.UserHasProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ognjen on 6/20/2019
 */
public interface UserHasProductRepository extends JpaRepository<UserHasProduct, Integer>, UserHasProductRepositoryCustom {

    List<UserHasProduct> getAllByUserId(Integer userId);

    List<UserHasProduct> getAllByUserIdAndActive(Integer userId, Byte active);

    UserHasProduct getByUserId(Integer userId);

    UserHasProduct getByUserIdAndActive(Integer userId, Byte active);

}
