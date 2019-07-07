package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
				 
		//create session
		Session session = factory.getCurrentSession();
		try {
			//create 3 students objects		
			System.out.println("Creating 3 students object...");
			Student tempStudent1=new Student("Apollo","Dickson","apollo@cooltext.com");
			Student tempStudent2=new Student("lele","lolo","lololele@cooltext.com");
			Student tempStudent3=new Student("Sai","Tama","sai.t@cooltext.com");
			
			//start transaction
			session.beginTransaction();
					
			//save student object
			System.out.println("Saving student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
					
					
		} finally {
			factory.close();
		}
	}

}
