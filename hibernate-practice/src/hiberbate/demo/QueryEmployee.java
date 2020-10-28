package hiberbate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernate.demo.entity.Employee;


public class QueryEmployee {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
								
		// create a session
		Session session = factory.getCurrentSession();
		
		try {			

			// start the transaction
			session.beginTransaction();
			
			// query students
			List<Employee> theEployees = session.createQuery("from Employee").getResultList();
			
			//display
			System.out.println("\nAll employees");
			displayEmployees(theEployees);
			
			//query employees: company is "Samsung"
			theEployees = session.createQuery("from Employee e where e.company='Samsung'").getResultList();
			
			//display
			System.out.println("\nEmployees with company = Samsung");
			displayEmployees(theEployees);
			
			//query employees: name starts with: 'M'"
			theEployees = session.createQuery("from Employee e where e.firstName LIKE 'M%'").getResultList();
			
			//display
			System.out.println("\nEmployees name starts with: 'M'");
			displayEmployees(theEployees);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} 
		finally {
			
		}

	}
	private static void displayEmployees(List<Employee> theEmployees) {
		for(Employee tempStudent : theEmployees) {
			System.out.println(tempStudent);
		}
	}

}
