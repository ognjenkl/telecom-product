package com.bticonex.telecomproduct.repository;

import com.bticonex.telecomproduct.model.Product;
import com.bticonex.telecomproduct.repository.repositoryCustom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ognjen on 6/20/2019
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {

    Product getById (Integer productId);

}
