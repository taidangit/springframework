package com.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.UserBilling;

public interface UserBillingRepository extends JpaRepository<UserBilling, Integer> {

}
