package com.dao;
import com.model.*;
import java.util.List;
import java.util.Map;

public interface IMovie {
	public List<Movie> getMoviesByLanguage(String language);

	public List<Movie> getMoviesByDirector(String directorName);

	public void addMovie(Movie movie);

	public void deleteMovie(String movieName);

	public List<String> getDistinctLanguage();
	public Movie getMovie(String movieId);

	public void updateRevenue(String movieId);
	public Map<String, String> getMovieDirectorNameMap();

	public List<Movie> getAllMovie();
}
