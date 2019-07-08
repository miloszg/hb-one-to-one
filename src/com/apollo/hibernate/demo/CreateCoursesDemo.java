package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Course;
import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		 
		//create session
		Session session = factory.getCurrentSession();
		try {	
			//start transaction
			session.beginTransaction();
			
			//get instructor from db
			int theId=1;
			Instructor tempInstructor = session.get(Instructor.class,theId);
			
			//create some courses
			Course coldOnesCourse=new Course("Smashing cold ones with broskis");
			Course kinoCourse=new Course("Every Ryan Gosling Movie ever");
			
			//add courses to instructor
			tempInstructor.add(coldOnesCourse);
			tempInstructor.add(kinoCourse);
			
			//save courses
			session.save(coldOnesCourse);
			session.save(kinoCourse);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			session.close();
			factory.close();
		}
		
		
	
	}

}
