package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.apollo.hibernate.demo.entity.Course;
import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			Query<Instructor> query=session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId",Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId",theId);
			
			//execute query and get Instructor
			Instructor tempInstructor=query.getSingleResult();
			System.out.println("TEST: Instructor: "+tempInstructor);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("TEST: Done! ");
			
			
		} finally {
			session.close();
			factory.close();
		}
		
		
	
	}

}
