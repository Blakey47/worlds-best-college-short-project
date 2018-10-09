package com.darraghblake.worlds_top_college.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.darraghblake.worlds_top_college.entity.Course;
import com.darraghblake.worlds_top_college.entity.Instructor;
import com.darraghblake.worlds_top_college.entity.InstructorDetail;
import com.darraghblake.worlds_top_college.entity.Review;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		
		System.out.println("\n\nCreating Factory...\n\n");
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		System.out.println("\n\nCreation Session...\n\n");
		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
			// Get a selected Instructor
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			String instructorName = tempInstructor.getFirstName() + " " + tempInstructor.getLastName();
			
			String courseInformation = "\n\n" + instructorName + "\nCourses:";
			for (Course course : tempInstructor.getCourses()) {
				courseInformation += "\n\n" + course.getTitle() + "\nReviews:\n";
				System.out.println(course);
				for (Review review : course.getReviews()) {
					courseInformation += "\n'" + review.getComment() + "'";
					System.out.println(review);
				}
			}
			
			System.out.println(courseInformation);
			
			session.getTransaction().commit();
			
			System.out.println("\nComplete.");
		}
		finally {
			session.close();
			factory.close();
		}
	}
	
}
