package com.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.util.HibernateUtil;


public class MovieDaoImpl implements IMovie {

	public void getMaximumMovieRevenue() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("SELECT MAX(revenueInDollars) FROM Movie");
		System.out.println("Maximun revenue is : " + query.list());
		session.close();
	}

	public void getMinimumMovieRevenue() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("SELECT MIN(revenueInDollars) FROM Movie");
		System.out.println("Minimum revenue is : " + query.list());
		session.close();
	}

	public void getSumOfRevenue() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("SELECT SUM(revenueInDollars) FROM Movie");
		System.out.println("Sum of revenue is : " + query.list());
		session.close();
	}

	public void getAverageOfRevenue() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("SELECT AVG(revenueInDollars) FROM Movie");
		System.out.println("Average revenue is : " + query.list());
		session.close();
	}

	public void getMovieCountByLanguage() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("SELECT m.language, COUNT(*) FROM Movie m GROUP BY m.language");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(objects[0] + ":" + objects[1]);
		}
		session.close();
	}

}


