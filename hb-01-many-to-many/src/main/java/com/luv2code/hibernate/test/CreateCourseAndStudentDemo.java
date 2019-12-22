package com.luv2code.hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory=
				new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).
				addAnnotatedClass(Course.class).
				
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Course course=new Course("Pacman - How to Score One Million Points");
			session.save(course);
			
			Student student = new Student("John", "Doe", "john@luv2code.com");
			Student student2 = new Student("Mary", "Public", "mary@luv2code.com");
			
			List<Student> students = new ArrayList<Student>();
			students.add(student);
			students.add(student2);
			course.setStudents(students);
			
			session.save(student);
			session.save(student2);
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
	
}
