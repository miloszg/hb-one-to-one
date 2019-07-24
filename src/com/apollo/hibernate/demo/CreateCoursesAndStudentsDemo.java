package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Course;
import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;
import com.apollo.hibernate.demo.entity.Review;
import com.apollo.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {
		//create session factory	
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		 
		//create session
		Session session = factory.getCurrentSession();
		try {	
			//start transaction
			session.beginTransaction();
			
			//print the course
			Course tempCourse=new Course("Chugging down cold ones with Broskis");
			System.out.println(tempCourse);
			
			//creating and adding students
			Student tempStudent1 = new Student("APollo","Butt","apollo@cooltext.com");
			Student tempStudent2 = new Student("Gnacy","Retard","retard@cooltext.com");
			Student tempStudent3 = new Student("Dood","Doodskie","dood@cooltext.com");
			
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			tempCourse.addStudent(tempStudent3);
			
			System.out.println("Saving students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//saving the course
			System.out.println("Saving...");
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
