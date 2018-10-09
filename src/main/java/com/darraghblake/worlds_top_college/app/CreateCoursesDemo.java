package com.darraghblake.worlds_top_college.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darraghblake.worlds_top_college.entity.Course;
import com.darraghblake.worlds_top_college.entity.Instructor;
import com.darraghblake.worlds_top_college.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			session.beginTransaction();
			
			// Get a selected Instructor
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			Course courseOne = new Course(randomCourseOne(), tempInstructor);
			Course courseTwo = new Course(randomCourseTwo(), tempInstructor);
			
			session.save(courseOne);
			session.save(courseTwo);
			
			session.getTransaction().commit();
			
			System.out.println("Complete.");
		}
		finally {
			session.close();
			factory.close();
		}
	}
	
	public static String randomCourseOne() {
		List<String> course = new ArrayList<String>();
		course.add("Maths 101");
		course.add("Geography 101");
		course.add("Biology 101");
		course.add("Physical Education");
		course.add("Physics 101");
		course.add("Chemistry 101");
		course.add("History 101");
		Random random = new Random();
		return course.get(random.nextInt(course.size() - 1));
	}
	
	public static String randomCourseTwo() {
		List<String> course = new ArrayList<String>();
		course.add("Computer Science");
		course.add("Engineering");
		course.add("Medicinal Chemistry");
		course.add("Business");
		course.add("Religion");
		Random random = new Random();
		return course.get(random.nextInt(course.size() - 1));
	}

}
