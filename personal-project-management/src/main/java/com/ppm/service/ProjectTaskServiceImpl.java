package com.ppm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppm.domain.Backlog;
import com.ppm.domain.Project;
import com.ppm.domain.ProjectTask;
import com.ppm.exception.ProjectNotFoundException;
import com.ppm.repository.ProjectTaskRepository;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public void add(String projectIdentifier, ProjectTask projectTask, String username) {
		Project project = projectService.findByProjectIdentifier(projectIdentifier, username);
		Backlog backlog = project.getBacklog();

		if(backlog == null) {
			throw new ProjectNotFoundException(projectIdentifier+" not found.");
		}
		
		projectTask.setBacklog(backlog);
		
		int backlogSequence = backlog.getPtSequence();
		backlogSequence++;
		backlog.setPtSequence(backlogSequence);
		
		projectTask.setProjectSequence(projectIdentifier.toUpperCase()+"-"+backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier.toUpperCase());
		
		projectTaskRepository.save(projectTask);
		
		
	}

	@Override
	public List<ProjectTask> findByProjetIdentifier(String projectIdentifier, String username) {
		
		projectService.findByProjectIdentifier(projectIdentifier, username);
		
		List<ProjectTask> projectTasks = projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
		return projectTasks;
	}

	@Override
	public ProjectTask findByProjectSequence(String projectIdentifier, String projectSequence, String username) {
		projectService.findByProjectIdentifier(projectIdentifier, username);
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
		if(projectTask == null) {
			throw new ProjectNotFoundException(projectSequence+" not found.");
		}
		
		return projectTask;
	}

	@Override
	public void update(ProjectTask projectTask, String projectIdentifier, String username) {
		projectService.findByProjectIdentifier(projectIdentifier, username);
		
		projectTaskRepository.save(projectTask);
	}

	@Override
	public void deleteByProjectSequence(String projectIdentifier, String projectSequence, String username) {
		ProjectTask projectTask = findByProjectSequence(projectIdentifier, projectSequence, username);
		
		projectTaskRepository.delete(projectTask);
	}
	
	
}
