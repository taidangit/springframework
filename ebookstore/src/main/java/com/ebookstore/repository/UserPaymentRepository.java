package com.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.UserPayment;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Integer> {

}
