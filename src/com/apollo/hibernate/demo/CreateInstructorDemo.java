package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Course;
import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			//create the objects
			Instructor instructor = new Instructor("Apollo","Randomowski","apollo@cooltext.com");
			InstructorDetail instructorDetail= new InstructorDetail("http://www.youtube.com/apollo","gaming");
			
			//associate objects 
			instructor.setInstructorDetail(instructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			//save the instructor
			System.out.println("Saving...");
			session.save(instructor);

			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			session.close();
			factory.close();
		}
		
		
	
	}

}
