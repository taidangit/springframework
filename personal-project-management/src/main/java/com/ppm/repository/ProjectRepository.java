package com.ppm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppm.domain.Project;
import com.ppm.domain.User;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findByUser(User user);
	Project findByProjectIdentifier(String projectIdentifier);

}
