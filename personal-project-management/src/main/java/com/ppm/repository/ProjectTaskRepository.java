package com.ppm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppm.domain.ProjectTask;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Integer> {

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);
	
	ProjectTask findByProjectSequence(String projectSequence);
	
}
