package com.ppm.service;

import java.util.List;

import com.ppm.domain.Project;
import com.ppm.domain.User;

public interface ProjectService {

	void saveOrUpdate(Project project, String username);
	
	Project findByProjectIdentifier(String projectIdentifier, String username);
	
	List<Project> findByUser(String username);
	
	void deleteByProjectIndentifier(String projectIdentifier, String username);
}
