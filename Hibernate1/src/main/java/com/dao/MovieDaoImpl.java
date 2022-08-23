package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.util.HibernateUtil;
public class MovieDaoImpl implements IMovie {

	public void addMovie(Movie movie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transcaction = session.beginTransaction();
		session.save(movie);
		transcaction.commit();
		session.close();
	}

	public Movie getMovie(String movieId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Movie movie = session.get(Movie.class, movieId);
		session.close();
		return movie;
	}

	public void updateMovie(String movieId, Integer revenueInDollar) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transcaction = session.beginTransaction();
		Movie movie = session.get(Movie.class, movieId);
		movie.setRevenueInDollar(movie.getRevenueInDollar() + revenueInDollar);
		session.merge(movie);
		transcaction.commit();
		session.close();
	}

	public void deleteMovie(String movieId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transcaction = session.beginTransaction();
		Movie movie = session.get(Movie.class, movieId);
		session.delete(movie);
		transcaction.commit();
		session.close();
	}

}
