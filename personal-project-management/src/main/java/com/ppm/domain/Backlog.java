package com.ppm.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "backlog")
public class Backlog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="backlog_id")
	private int backlogId;
	
	@Column(name="pt_sequence")
	private int ptSequence = 0;
	
	@Column(name="project_identifier")
	private String projectIdentifier;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore
	private Project project;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "backlog")
	private List<ProjectTask> projectTasks;

	public int getBacklogId() {
		return backlogId;
	}

	public void setBacklogId(int backlogId) {
		this.backlogId = backlogId;
	}

	public int getPtSequence() {
		return ptSequence;
	}

	public void setPtSequence(int ptSequence) {
		this.ptSequence = ptSequence;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<ProjectTask> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<ProjectTask> projectTasks) {
		this.projectTasks = projectTasks;
	}


}
