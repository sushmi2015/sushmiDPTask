package hibernateJpa.Hibernate2;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.Customer;
import com.model.Locker;
import com.util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		System.out.println("Create");
		Customer customer1 = new Customer();
		customer1.setCustomerName("Sushmi");
		try {
			customer1.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2003"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customer1.setAddress("Hogwarts");
		customer1.setPhoneNo(11233);

		Locker locker = new Locker();
		locker.setLockerType("small");
		locker.setRent(1000);

		customer1.setLockerId(locker);

		Transaction transcaction = session.beginTransaction();
		Serializable customerId = session.save(customer1);
		transcaction.commit();

		Customer customer = session.load(Customer.class, customerId);
		System.out.println(customer);
		session.close();
	}
}
