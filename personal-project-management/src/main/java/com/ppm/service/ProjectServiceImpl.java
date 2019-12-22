package com.ppm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppm.domain.Backlog;
import com.ppm.domain.Project;
import com.ppm.domain.User;
import com.ppm.exception.ProjectIdException;
import com.ppm.exception.ProjectNotFoundException;
import com.ppm.repository.BacklogRepository;
import com.ppm.repository.ProjectRepository;
import com.ppm.repository.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveOrUpdate(Project project, String username) {
		if(project.getProjectId() != 0) {
			Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
			if(existingProject != null && !existingProject.getProjectLeader().equals(username)) {
				throw new ProjectNotFoundException("Project not found in your account.");
			} else if(existingProject == null) {
				throw new ProjectNotFoundException("Project identifier: "+project.getProjectIdentifier()+" does not exist.");
			}
		}
		try {
			User user = userRepository.findByUsername(username);
			project.setUser(user);
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			project.setProjectLeader(user.getUsername());
			if(project.getProjectId() != 0) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier()));
				
			} else {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
				
			}
			
			projectRepository.save(project);
		} catch(Exception e) {
			throw new ProjectIdException("Project Identifier: "+project.getProjectIdentifier()+" already exists.");
		}
		
	}

	@Override
	public Project findByProjectIdentifier(String projectIdentifier, String username) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
		if(project == null) {
			throw new ProjectIdException("Project Identifier: "+projectIdentifier+" not found.");
		}
		
		if(!project.getProjectLeader().equals(username)) {
			throw new ProjectNotFoundException("Project not found in your account.");
		}
		return project;
	}

	@Override
	public List<Project> findByUser(String username) {
		User user = userRepository.findByUsername(username);
		return projectRepository.findByUser(user);
	}

	@Override
	public void deleteByProjectIndentifier(String projectIdentifier, String username) {
		Project project = findByProjectIdentifier(projectIdentifier, username);
	
		projectRepository.delete(project);
	}
	
	

}
