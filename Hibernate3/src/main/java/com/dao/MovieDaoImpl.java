package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.HollyWood;
import com.model.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import com.model.HollyWood;
import com.model.Movie;
import com.util.HibernateUtil;
public class MovieDaoImpl implements IMovie {


	public List<Movie> getMoviesByLanguage(String language) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Movie m where m.language =:language");
		query.setParameter("language", language);
		List<Movie> movies = query.list();
		session.close();
		return movies.isEmpty() ? new ArrayList<Movie>() : movies;
	}

	public List<Movie> getMoviesByDirector(String directorName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Movie m where m.director.directorName =:directorName");
		query.setParameter("directorName", directorName);
		List<Movie> movies = query.list();
		session.close();
		return movies.isEmpty() ? new ArrayList<Movie>() : movies;
	}

	public void addMovie(Movie movie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transcaction = session.beginTransaction();
		if (movie.getLanguage().equalsIgnoreCase("english")) {
			HollyWood hollyWood = new HollyWood(movie);
			session.save(hollyWood);
		}
		session.saveOrUpdate(movie);
		transcaction.commit();
		System.out.println("Movie added");
		session.close();
	}

	public Movie getMovie(String movieId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Movie m where m.movieId =:movieId");
		query.setParameter("movieId", movieId);
		List<Movie> movies = query.list();
		session.close();
		return movies.isEmpty() ? null : movies.get(0);
	}

	public void updateRevenue(String movieId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transcaction = session.beginTransaction();
		Movie movie = getMovie(movieId);
		if (movie != null) {
			Query query = session
					.createQuery("update Movie m set m.revenueInDollar=:revenue where m.movieId =:movieId");
			query.setParameter("movieId", movieId);
			query.setParameter("revenue", movie.getRevenueInDollars() + 100000);
			int row = query.executeUpdate();
			System.out.println(row + " movie details updated");
			transcaction.commit();
		}
		session.close();
	}

	public void deleteMovie(String movieId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transcaction = session.beginTransaction();
		Query query = session.createQuery("delete from  Movie m where m.movieId =:movieId");
		query.setParameter("movieId", movieId);

		int row = query.executeUpdate();
		System.out.println(row + " movie deleted...");
		transcaction.commit();
		session.close();
	}

}