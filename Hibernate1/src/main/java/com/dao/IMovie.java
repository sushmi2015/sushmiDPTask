package com.dao;

import com.model.Movie;

public interface IMovie {
	

	public void addMovie(Movie movie);

	public Movie getMovie(String movieId);

	public void updateMovie(String movieId,Integer revenueInDollar);

	public void deleteMovie(String movieId);
	
}