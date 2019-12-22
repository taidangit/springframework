package com.ppm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "project_task")
public class ProjectTask {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="project_task_id")
	private int projectTaskId;
	
	@Column(name="project_sequence", updatable = false, unique = true)
	private String projectSequence;
	
	@NotBlank(message = "Please include a project summary")
	@Column(name="summary")
	private String summary;
	
	@Column(name="acceptance_criteria")
	private String acceptanceCriteria;
	
	@Column(name="status")
	private String status;
	
	@Column(name="priority")
	private int priority;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="project_identifier", updatable = false)
	private String projectIdentifier;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="created_at", updatable = false)
	private Date createdAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="updated_at")
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "backlog_id", updatable = false, nullable = false)
	@JsonIgnore
	private Backlog backlog;
	
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}

	public int getProjectTaskId() {
		return projectTaskId;
	}

	public void setProjectTaskId(int projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	public String getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}

	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	
	

}
