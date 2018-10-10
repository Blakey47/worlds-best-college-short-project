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
import com.darraghblake.worlds_top_college.entity.Review;
import com.darraghblake.worlds_top_college.entity.Student;

public class CreateStudentDemo {
	
	private static Random random = new Random();

	public static void main(String[] args) {
		
		System.out.println("\n\nCreating Factory...\n\n");
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		System.out.println("\n\nCreation Session...\n\n");
		Session session = factory.getCurrentSession();

		try {
			
			String firstName = randomFirstName();
			String lastName = randomLastName();
			
			Student tempStudent = new Student(firstName, lastName, lastName + firstName +"@gmail.com");
			Instructor tempInstructor = new Instructor(randomFirstName(), randomLastName(), "TestingEmail");
			Course courseOne = new Course(randomCourseOne(), tempInstructor);
			
			session.beginTransaction();
			
			tempStudent.addCourse(courseOne);
			
			System.out.println("Saving Instructor: " + tempStudent);
			session.save(tempStudent);
			session.save(tempInstructor);
			session.save(courseOne);
			
			session.getTransaction().commit();
			
			System.out.println("\nComplete.\n");
		}
		finally {
			session.close();
			factory.close();
		}
	}
	
	public static String randomFirstName() {
		List<String> names = new ArrayList<String>();
		names.add("Prihah");
		names.add("Corey");
		names.add("Rachel");
		names.add("Megan");
		names.add("Moreen");
		names.add("Kathleen");
		names.add("Billy");
		return names.get(random.nextInt(names.size() - 1));
	}
	
	public static String randomLastName() {
		List<String> names = new ArrayList<String>();
		names.add("Troy");
		names.add("Sanders");
		names.add("Martin");
		names.add("Doe");
		names.add("Leroy");
		names.add("Gordon");
		names.add("Phelps");
		return names.get(random.nextInt(names.size() - 1));
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
		return course.get(random.nextInt(course.size() - 1));
	}
	
	public static String randomCourseTwo() {
		List<String> course = new ArrayList<String>();
		course.add("Computer Science");
		course.add("Engineering");
		course.add("Medicinal Chemistry");
		course.add("Business");
		course.add("Religion");
		return course.get(random.nextInt(course.size() - 1));
	}
	
}
