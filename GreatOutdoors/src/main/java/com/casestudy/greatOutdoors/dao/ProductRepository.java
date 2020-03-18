package com.casestudy.greatOutdoors.dao;

import com.casestudy.greatOutdoors.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
