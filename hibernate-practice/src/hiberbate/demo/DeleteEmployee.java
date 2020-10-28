package hiberbate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Employee;

public class DeleteEmployee {

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
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting employee with id: " + employeeId);
			
			Employee myEmployee = session.get(Employee.class, employeeId);
			
			// delete the student
			System.out.println("Deleting employee: " + myEmployee);
			session.delete(myEmployee);
			
			// delete student id
			System.out.println("Deleting student id=2");
			session.createQuery("delete from Employee where id=2")
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
