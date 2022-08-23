package hibernateJpa.Hibernate4;

import java.util.Scanner;

import org.hibernate.HibernateException;

import com.dao.MovieDaoImpl;


/**
 * Hello world!
 *
 */
public class App 
{
	private static String[] options = { "1: Find Maximum Revenue", "2: Find Minimum Revenue", "3: Find Sum of Revenue",
			"4: Find Average Revenue", "5: Find the count of movies By langauge", "6: Exit" };

	public static void main(String[] args) {
		System.out.println("------------Welcome-----------");

		try (Scanner scanner = new Scanner(System.in)) {
			boolean flag = true;
			while (flag) {
				printMenu(options);
				try {
					int option = scanner.nextInt();
					excecute(option);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					flag = true;
				}
			}
		}
	}

	public static void printMenu(String[] options) {
		System.out.println("Following are the Options: ");
		for (String option : options) {
			System.out.println(option);
		}
	}

	@SuppressWarnings("resource")
	public static void excecute(int option) throws IllegalArgumentException {
		Scanner scanner = new Scanner(System.in);
		MovieDaoImpl movieDAO = new MovieDaoImpl();
		try {
			switch (option) {
			case 1: {
				movieDAO.getMaximumMovieRevenue();
				break;
			}
			case 2: {
				movieDAO.getMinimumMovieRevenue();
				break;
			}
			case 3: {
				movieDAO.getSumOfRevenue();
				break;
			}
			case 4: {
				movieDAO.getAverageOfRevenue();
				break;
			}
			case 5: {
				movieDAO.getMovieCountByLanguage();
				break;
			}
			case 6: {
				System.out.println("------------Existing Application -----------");
				System.exit(0);
				break;
			}

			default:
				throw new IllegalArgumentException("Oops! Incorrect option");
			}
		} catch (HibernateException e) {
			System.out.println(e);
		}
	}
}