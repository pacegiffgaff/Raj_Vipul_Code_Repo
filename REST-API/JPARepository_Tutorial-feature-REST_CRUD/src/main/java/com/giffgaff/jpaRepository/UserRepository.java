package com.giffgaff.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giffgaff.entity.User;


/**
 * @EnableJpaRepositories this will scan the interfaces which are extending jpa repository
 * For JpaRepositrory we need to pass Entity class and primary key of entity class
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
