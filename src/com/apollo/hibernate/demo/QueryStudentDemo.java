package com.apollo.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		 
		//create session
		Session session = factory.getCurrentSession();
		try {
			//start transaction
			session.beginTransaction();
			
			//query student
			List<Student> theStudents= session.createQuery("from Student").list();
			
			//display students
			displayStudents(theStudents);
			
			//query student where lastName=Duck
			theStudents= session.createQuery("from Student s where s.lastName='Duck'").list();
			
			//display students where lastName=Duck
			displayStudents(theStudents);
			
			//query student where lastName=Duck OR firstName=Daffy
			theStudents=session.createQuery("from Student s where s.lastName='Duck' OR s.firstName='Daffy'").list();
			
			//display students where lastName=Duck OR firstName=Daffy
			displayStudents(theStudents);
			
			//query students with email like '%cooltext.com'
			theStudents =session.createQuery("from Student s where s.email LIKE '%cooltext.com'").list();
			
			//display students with email like '%cooltext.com'
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			factory.close();
		}
		
		
	
	}

	private static void displayStudents(List<Student> theStudents) {
		System.out.println("\n");
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
