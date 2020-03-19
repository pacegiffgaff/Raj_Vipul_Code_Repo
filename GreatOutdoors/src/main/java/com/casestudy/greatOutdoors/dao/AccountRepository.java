package com.casestudy.greatOutdoors.dao;

import com.casestudy.greatOutdoors.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
