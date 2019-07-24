package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Course;
import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;
import com.apollo.hibernate.demo.entity.Review;

public class GetCoursesWithReviewsDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		 
		//create session
		Session session = factory.getCurrentSession();
		try {	
			//start transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse=new Course("Chugging down with broskis");
		
			
			//add some reviews
			tempCourse.addReview(new Review("Hmmm...pretty guuud"));
			tempCourse.addReview(new Review("LAmmeeeeeeeeeeeeeeee"));
			tempCourse.addReview(new Review("FREE AMAZON GIFT CARD GO TO: SHADYLINK.COM"));
			
			//save course
			System.out.println("Saving..."+tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			session.close();
			factory.close();
		}
		
		
	
	}

}
