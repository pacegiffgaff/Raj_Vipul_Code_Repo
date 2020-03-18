package com.casestudy.greatOutdoors.dao;

import com.casestudy.greatOutdoors.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
