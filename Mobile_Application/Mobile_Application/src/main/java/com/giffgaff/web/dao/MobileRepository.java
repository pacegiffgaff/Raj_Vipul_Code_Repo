package com.giffgaff.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giffgaff.web.entity.Mobile;

/**
 * MobileRepository
 */
public interface MobileRepository extends JpaRepository<Mobile, Integer> {
	List<Mobile> findByBrand(String brand);

}
