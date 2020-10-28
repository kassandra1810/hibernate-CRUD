package hiberbate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.*;

public class UpdateEmployee {

	public static void main(String[] args) {
		// create session factory
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Employee.class)
									.buildSessionFactory();
									
			// create a session
			Session session = factory.getCurrentSession();
			
			try {		
				int employeeId = 1;
				// start the transaction
				session.beginTransaction();
				
				// retrieve employee based on the id: primary key
				System.out.println("\nGetting employee with id: " + employeeId);
				
				Employee myEmployee = session.get(Employee.class, employeeId);
				
				System.out.println("Updating employee...");
				myEmployee.setFirstName("Scooby");
				
				// commit the transaction
				session.getTransaction().commit();
				
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				// update email for all employees
				System.out.println("Update company for all employees");
				session.createQuery("update Employee set company='TKSolutions'")
					.executeUpdate();
				
				// commit the transaction
				session.getTransaction().commit();
				
				System.out.println("Done!");
			}
			finally {
				factory.close();
			}

	}

}
