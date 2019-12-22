package com.ppm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppm.domain.Backlog;

public interface BacklogRepository extends JpaRepository<Backlog, Integer>{

	Backlog findByProjectIdentifier(String identifier);
}
