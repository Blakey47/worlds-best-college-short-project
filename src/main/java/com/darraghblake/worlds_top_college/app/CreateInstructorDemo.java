package com.darraghblake.worlds_top_college.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darraghblake.worlds_top_college.entity.Course;
import com.darraghblake.worlds_top_college.entity.Instructor;
import com.darraghblake.worlds_top_college.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		System.out.println("\n\nCreating Factory...\n\n");
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		System.out.println("\n\nCreation Session...\n\n");
		Session session = factory.getCurrentSession();

		try {
			Instructor tempInstructor = new Instructor("George", "Foreman", "Goergy@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("www.george.com", "Coding");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			session.beginTransaction();
			
			System.out.println("Saving Instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			
			System.out.println("Complete.");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
