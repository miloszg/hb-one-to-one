package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;


public class DeleteDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		 
		//create session
		Session session = factory.getCurrentSession();
		try {
			//start transaction
			session.beginTransaction();
			
			//get instructor by primary key
			int theId=1;
			Instructor instructor = session.get(Instructor.class,theId);
			System.out.println("found: "+instructor);
			
			//delete instructor (cascade delete)
			if(instructor!=null) {
				System.out.println("Deleting instructor");
				session.delete(instructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			factory.close();
		}
		
		
	
	}

}
