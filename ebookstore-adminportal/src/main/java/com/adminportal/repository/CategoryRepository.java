package com.adminportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminportal.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
