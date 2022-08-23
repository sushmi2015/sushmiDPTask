package hibernateJpa.Hibernate5;

import java.util.HashSet;
import java.util.Set;

import com.dao.AssetsDaoImpl;
import com.dao.EmployeeDaoImpl;
import com.model.Assets;
import com.model.Employee;

public class App {
	public static void main(String[] args) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		AssetsDaoImpl assetsDao = new AssetsDaoImpl();

		Assets a1 = new Assets(1, 1001, 1, "TV", 68000, "Mumbai");
		Assets a2 = new Assets(2, 1002, 2, "Laptop", 10000, "Kerala");

		Set<Assets> s1 = new HashSet<Assets>();
		s1.add(a1);
		s1.add(a2);

		Employee e1 = new Employee(101, "SUSHMI", "Sushmi@2012", s1);

		System.out.println("Add employee");
		empDao.addEmployee(e1);

		System.out.println("Get asset by Id");
		assetsDao.getAssetById(11);

		System.out.println("Get max price asset");
		assetsDao.getMaxPriceAsset();

		System.out.println("Get Employee count");
		empDao.getEmployeeCount();

		System.out.println("Get all employees");
		empDao.getEmployees();

		System.out.println("Get employee by Id");
		empDao.getEmployeeById(11);

		System.out.println("Get avg asset price");
		assetsDao.getAvgAssetPrice();

		System.out.println("update Employee");
		empDao.updatePassword("newpassword", 11);

		System.out.println("Delete employee by Id");
		empDao.deleteEmployee(11);

	}
}