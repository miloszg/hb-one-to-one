package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		 
		//create session
		Session session = factory.getCurrentSession();
		try {
			//create a student object		
			System.out.println("Creating new student object...");
			Student tempStudent=new Student("Apollo","Dickson","apollo@cooltext.com");
			
			//start transaction
			session.beginTransaction();
			
			//save student object
			System.out.println("Saving student...");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			factory.close();
		}
		
		
	
	}

}
