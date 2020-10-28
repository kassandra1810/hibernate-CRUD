package hiberbate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Employee;

public class ReadEmployee {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
								
		// create a session
		Session session = factory.getCurrentSession();
		
		try {	
			int id = 1;
			
			// start the transaction
			session.beginTransaction();
			
			// query students
			Employee myEmployee = session.get(Employee.class, id);
			System.out.println("Get complete: " + myEmployee);
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			
			// start the transaction
			session.beginTransaction();
			
			Employee theEmployee = (Employee) session.createQuery("from Employee where id=1").getResultList().get(0);
			System.out.println("Get complete: " + theEmployee);
			
			// commit the transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}

	}

}
