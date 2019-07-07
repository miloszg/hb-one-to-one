package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;
import com.apollo.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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
			
			
			//get the instructor detail
			int theId=3;
			InstructorDetail instructorDetail=session.get(InstructorDetail.class,theId);
			
			//print instructor detail
			System.out.println("instructor detail: "+instructorDetail );
			
			//print associated instructor
			System.out.println("the associated instructor: "+instructorDetail.getInstructor());
			
			//deleting instructor detail
			System.out.println("Deleting..."+instructorDetail);
			
			//remove associated object reference break the link
			instructorDetail.getInstructor().setInstructorDetail(null);
			
			
			session.delete(instructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			} catch(Exception e) {	
			e.printStackTrace();
			} finally {
			session.close();
			factory.close();
		}
	}
}


