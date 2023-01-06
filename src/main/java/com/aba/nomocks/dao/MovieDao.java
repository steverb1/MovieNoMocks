package com.aba.nomocks.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.service.ForPersistingMovies;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;

public class MovieDao implements ForPersistingMovies {
	static String uri = "mongodb://127.0.0.1:27017";
	static String databaseName = "moviedb";
	static String collectionName = "movies";
	
	private Movie lastWrite;
	
	private ForWrappingMongo mongo;
	
	public static MovieDao create() {
		return new MovieDao(new MongoWrapper());
	}
	
	public static MovieDao createNull() {
		return new MovieDao(new MongoStub());
	}
	
	public String saveMovie(Movie movie) {
		String id = mongo.insertOne(movie);
		
		lastWrite = new Movie(movie.title, movie.year);
		
		return id;
	}
	
	public Movie retrieveMovie(String title, int year) {
		return mongo.find(title, year);
	}

	public Movie getLastWrite() {
		return lastWrite;
	}
	
	MovieDao(ForWrappingMongo mongo) {
		this.mongo = mongo;
	}
	
	static class MongoWrapper implements ForWrappingMongo {
		private MongoCollection<Document> mongoCollection;
		
		MongoClient client = MongoClients.create(uri);
		MongoDatabase database = client.getDatabase(databaseName);
		
		public MongoWrapper() {
			mongoCollection = database.getCollection(collectionName);;
		}
		
		public String insertOne(Movie movie) {
			Document document = new Document()
					.append("_id", new ObjectId())
					.append("title", movie.title)
					.append("year", movie.year);
			InsertOneResult result = mongoCollection.insertOne(document);
			return result.getInsertedId().toString();
		}

		public Movie find(String title, int year) {
			Bson projectionFields = Projections.fields(Projections.include("title", "year"));
			Document document = mongoCollection.find(and(eq("title", title), eq("year", year))).projection(projectionFields).first();
			return new Movie(document.getString("title"), document.getInteger("year"));
		}
	}
	
	static class MongoStub implements ForWrappingMongo {
		public String insertOne(Movie movie) {
			return new ObjectId().toString();
		}

		public Movie find(String title, int year) {
			return new Movie(title, year);
		}
	}
	
	interface ForWrappingMongo {
		String insertOne(Movie movie);

		Movie find(String title, int year);
	}
}
