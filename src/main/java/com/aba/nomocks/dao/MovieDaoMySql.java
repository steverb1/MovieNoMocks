package com.aba.nomocks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.service.ForPersistingMovies;

public class MovieDaoMySql implements ForPersistingMovies {
	Movie lastWrite;
	
	private ForWrappingMySql mySql;
	
	public String saveMovie(Movie movie) {
		String id = mySql.insert(movie);
		lastWrite = new Movie(movie.title, movie.year);
		return id;
	}
	
	public Movie retrieveMovie(String title, int year) {
		return mySql.select(title, year);
	}
	
	public static MovieDaoMySql create() {
		return new MovieDaoMySql(new MySqlWrapper());
	}

	public static MovieDaoMySql createNull() {
		return new MovieDaoMySql(new MySqlStub());
	}

	public Movie getLastWrite() {
		return lastWrite;
	}
	
	MovieDaoMySql(ForWrappingMySql mySql) {
		this.mySql = mySql;
	}
	
	static class MySqlWrapper implements ForWrappingMySql {
		public String insert(Movie movie) {
			String url = "jdbc:mysql://localhost:3306/moviedb";
			String username = "root";
			String password = "";
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(url, username, password);
				Statement statement = connection.createStatement();
				statement.executeUpdate("insert into movies (title, year) " +
						"values('" + movie.title + "'," + movie.year + ")", Statement.RETURN_GENERATED_KEYS);
				ResultSet result = statement.getGeneratedKeys();
				String id = null;
				if (result.next()) {
					id = String.valueOf(result.getInt(1));
				}
				
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return "";
		}

		public Movie select(String title, int year) {
			String url = "jdbc:mysql://localhost:3306/moviedb";
			String username = "root";
			String password = "";
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(url, username, password);
				Statement statement = connection.createStatement();
				String query = String.format("select * from movies where title = '%s' and year = '%d'", title, year);
				ResultSet result = statement.executeQuery(query);
				
				String foundTitle = null;
				int foundYear = 0;
				if (result.next()) {
					foundTitle = result.getString("title");
					foundYear = result.getInt("year");
				}
				
				return new Movie(foundTitle, foundYear);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}
	
	static class MySqlStub implements ForWrappingMySql {
		public String insert(Movie movie) {
			return "123";
		}

		public Movie select(String title, int year) {
			return new Movie(title, year);
		}
	}
	
	interface ForWrappingMySql {
		String insert(Movie movie);
		Movie select(String title, int year);
	}
}
