package hiberbate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Employee;

public class CreateEmployee {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		// create a session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a employee object
			System.out.println("Creating a new Employee object");
			Employee employee1 = new Employee ("Lily", "Collins", "Google");
			Employee employee2 = new Employee ("Matt", "Berry", "CMS");
			Employee employee3 = new Employee ("Scott", "Asckham", "Miletis");
			Employee employee4 = new Employee ("Marlena", "Ronardo", "Google");
			Employee employee5 = new Employee ("Dominic", "Bravo", "Samsung");
			
			// start the transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the employee...");
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);
			session.save(employee5);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}
}
