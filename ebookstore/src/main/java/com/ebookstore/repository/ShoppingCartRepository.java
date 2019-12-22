package com.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}
