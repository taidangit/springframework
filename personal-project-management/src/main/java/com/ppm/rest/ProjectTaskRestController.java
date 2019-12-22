package com.ppm.rest;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppm.domain.ProjectTask;
import com.ppm.service.MapValidationService;
import com.ppm.service.ProjectTaskService;

@CrossOrigin
@RestController
@RequestMapping("/api/projectTask")
public class ProjectTaskRestController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationService mapValidationService;
	
	@PostMapping("/{projectIdentifier}")
	public ResponseEntity<?> addProjectTask(
			@Valid @RequestBody ProjectTask projectTask,
			BindingResult result, 
			@PathVariable String projectIdentifier, 
			Principal principal) {
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		projectTaskService.add(projectIdentifier, projectTask, principal.getName());
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> getProjectTasks(@PathVariable String projectIdentifier, Principal principal) {
		List<ProjectTask> projectTasks = projectTaskService.findByProjetIdentifier(projectIdentifier, principal.getName());
		
		return new ResponseEntity<List<ProjectTask>>(projectTasks, HttpStatus.OK);
	}
	
	@GetMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> getProjectTask(
			@PathVariable String projectIdentifier, 
			@PathVariable String projectSequence,
			Principal principal) {
		ProjectTask projectTask = projectTaskService.findByProjectSequence(projectIdentifier, projectSequence, principal.getName());
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@PutMapping("/{projectIdentifier}")
	public ResponseEntity<?> updateProjectTask(
			@Valid @RequestBody ProjectTask projectTask, 
			BindingResult result,
			@PathVariable String projectIdentifier, 
			Principal principal) {
		
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		projectTaskService.update(projectTask, projectIdentifier, principal.getName());
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> deleteProjectTask(
			@PathVariable String projectIdentifier, 
			@PathVariable String projectSequence,
			Principal principal) {
		
		projectTaskService.deleteByProjectSequence(projectIdentifier, projectSequence, principal.getName());
		
		return new ResponseEntity<String>(projectSequence+" was deleted successfully!", HttpStatus.OK);
	}
}
