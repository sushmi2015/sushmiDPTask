package hibernateJpa.Hibernate3;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dao.MovieDaoImpl;
import com.model.Director;
import com.model.Movie;
import com.util.HibernateUtil;
/**
 * Hello world!
 *
 */
public class App 
{
	private static String[] options = { "1: Get Movie By Language", "2: Get Movie By Director", "3: Add Movies",
			"4: Update Movie revenue", "5: Delete Movie details", "6: Get List Of Languages", "7: Get Movie And Director Name",
			"8: Exit" };

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
		System.out.println("Options: ");
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
				System.out.println("Enter langauge of the movie");
				String lang = scanner.next();
				List<Movie> movByLang = movieDAO.getMoviesByLanguage(lang);
				movByLang.stream().forEach(System.out::println);
				break;
			}
			case 2: {
				System.out.println("Enter Movie Director's name");
				String directorName = scanner.next();
				List<Movie> movByLang = movieDAO.getMoviesByDirector(directorName);
				movByLang.stream().forEach(System.out::println);
				break;
			}
			case 3: {
				System.out.println("Enter the Movie ID");
				String id = scanner.next();
				System.out.println("Enter the Movie name");
				String name = scanner.next();
				System.out.println("Enter the Language");
				String language = scanner.next();
				System.out.println("Enter the released year");
				int year = scanner.nextInt();
				System.out.println("Enter the revenue");
				int revenue = scanner.nextInt();
				System.out.println("Enter the Director ID");
				String directorId = scanner.next();
				Session session = HibernateUtil.getSessionFactory().openSession();
				Query query = session.createQuery("from Director m where m.directorId =:directorId");
				query.setParameter("directorId", directorId);
				List<Director> directors = query.list();
				session.close();
				Director director = null;
				if (directors != null && !directors.isEmpty()) {
					director = directors.get(0);
				}
				Movie createMovie = new Movie(id, name, language, year, revenue, director);
				movieDAO.addMovie(createMovie);
				break;
			}
			case 4: {
				System.out.println("Enter the Movie ID to update revenue");
				String id = scanner.next();
				movieDAO.updateRevenue(id);
				break;
			}
			case 5: {
				System.out.println("Enter the Movie ID to delete");
				String id = scanner.next();
				movieDAO.deleteMovie(id);
				break;
			}
			case 6: {
				movieDAO.getDistinctLanguage().stream().forEach(System.out::println);
				break;
			}
			case 7: {
				movieDAO.getMovieDirectorNameMap().entrySet().stream().forEach(System.out::println);
				break;
			}
			case 8: {
				System.out.println("------------Existing Application -----------");
				System.exit(0);
				break;
			}
			default:
				throw new IllegalArgumentException("incorrect option selected");
			}
		} catch (HibernateException e) {
			System.out.println(e);
		}
	}
}
