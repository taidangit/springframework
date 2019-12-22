package com.ppm.service;

import java.util.List;

import com.ppm.domain.ProjectTask;

public interface ProjectTaskService {

	void add(String projectIdentifier, ProjectTask projectTask, String username);
	
	List<ProjectTask> findByProjetIdentifier(String projectIdentifier, String username);
	
	ProjectTask findByProjectSequence(String projectIdentifier, String projectSequence, String username);
	
	void update(ProjectTask projectTask, String projectIdentifier, String username);
	
	void deleteByProjectSequence(String projectIdentifier, String projectSequence, String username);
}
