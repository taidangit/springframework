package com.luv2code.hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;
import com.luv2code.hibernate.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		SessionFactory factory=
				new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Course course = new Course("Pacman - How to Score One Million Points");
		
			
			List<Review> reviews = new ArrayList<Review>();
			reviews.add(new Review("Great course... Loved it!"));
			reviews.add(new Review("Cool course... Job web done"));
			reviews.add(new Review("What a dumb course, you are an idiot!"));
		
			course.setReviews(reviews);
			
			session.save(course);
			
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
	}
	
}
