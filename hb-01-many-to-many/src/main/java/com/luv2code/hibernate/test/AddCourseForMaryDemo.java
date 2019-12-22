package com.luv2code.hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Student;

public class AddCourseForMaryDemo {

	public static void main(String[] args) {
		SessionFactory factory=
				new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			
			
			Student student=session.get(Student.class, 17);
			
			Course course=new Course("Rubik's Cube - How to Speed Cube");
			Course course2=new Course("Atari 2600 - Game Development");
			
			List<Student> students = new ArrayList<Student>();
			
			students.add(student);
			
			course.setStudents(students);
			course2.setStudents(students);
			
			session.save(course);	
			session.save(course2);
		
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
	
}
