package com.luv2code.hibernate.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int courseId;

	@Column(name = "title")
	private String title;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "course_student",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;

	public Course() {
		super();
	}

	public Course(String title) {
		super();
		this.title = title;
	}


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + "]";
	}

	
	
}
