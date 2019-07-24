package com.apollo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apollo.hibernate.demo.entity.Course;
import com.apollo.hibernate.demo.entity.Instructor;
import com.apollo.hibernate.demo.entity.InstructorDetail;
import com.apollo.hibernate.demo.entity.Review;
import com.apollo.hibernate.demo.entity.Student;

public class AddCoursesForStudentsDemo {

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
			
			//get student
			int theId=1;
			Student tempStudent=session.get(Student.class,theId);
			System.out.println("got: "+tempStudent);
			System.out.println("Courses: "+tempStudent.getCourses());
			
			//create more courses and add them to student
			Course tempCourse1=new Course("Paiting sum treeees n stuff");
			Course tempCourse2=new Course("Simple Mental Math, YOU CAN DO IT TOOO");

			//save courses
			System.out.println("Saving courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done! ");
			
			
		} finally {
			session.close();
			factory.close();
		}
		
		
	
	}

}
