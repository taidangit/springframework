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

import com.ppm.domain.Project;
import com.ppm.domain.User;
import com.ppm.service.MapValidationService;
import com.ppm.service.ProjectService;
import com.ppm.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/project")
public class ProjectRestController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationService mapValidationService;
	
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(
			@Valid @RequestBody Project project, 
			BindingResult result, 
			Principal principal) {
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		projectService.saveOrUpdate(project, principal.getName());
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIndentifier}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIndentifier, Principal principal) {
		Project project = projectService.findByProjectIdentifier(projectIndentifier, principal.getName());
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getProjectsByUser(Principal principal) {
		List<Project> projects = projectService.findByUser(principal.getName());
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectIndentifier}")
	public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIndentifier, Principal principal) {
		projectService.deleteByProjectIndentifier(projectIndentifier, principal.getName());
		return new ResponseEntity<String>("Deleted project identifier: "+projectIndentifier, HttpStatus.OK);
	}
	

}
