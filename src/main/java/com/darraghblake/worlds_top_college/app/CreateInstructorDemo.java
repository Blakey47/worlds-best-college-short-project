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
			String firstName = randomFirstName();
			String lastName = randomLastName();
			
			Instructor tempInstructor = new Instructor(firstName, lastName, lastName + firstName +"@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("www." + firstName + lastName + ".com", randomHobby());
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			session.beginTransaction();
			
			System.out.println("Saving Instructor: " + tempInstructor);
			session.save(tempInstructor);
			
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
		names.add("Sarah");
		names.add("John");
		names.add("Michael");
		names.add("Megan");
		names.add("Patrick");
		names.add("Jade");
		names.add("Rachel");
		Random random = new Random();
		return names.get(random.nextInt(names.size() - 1));
	}
	
	public static String randomLastName() {
		List<String> names = new ArrayList<String>();
		names.add("Moreen");
		names.add("Blake");
		names.add("Hill");
		names.add("O' Shea");
		names.add("Leroy");
		names.add("Farrage");
		names.add("Gregore");
		Random random = new Random();
		return names.get(random.nextInt(names.size() - 1));
	}
	
	public static String randomHobby() {
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("Skiing");
		hobbies.add("Reading");
		hobbies.add("Coding");
		hobbies.add("Sailing");
		hobbies.add("Teaching");
		hobbies.add("Surfing");
		hobbies.add("Sleeping");
		Random random = new Random();
		return hobbies.get(random.nextInt(hobbies.size() - 1));
	}

}
