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

public class MovieDaoMongo implements ForPersistingMovies {
	static String uri = "mongodb://127.0.0.1:27017";
	static String databaseName = "moviedb";
	static String collectionName = "movies";
	
	private Movie lastWrite;
	
	private ForWrappingMongo mongo;
	
	public static MovieDaoMongo create() {
		return new MovieDaoMongo(new MongoWrapper());
	}
	
	public static MovieDaoMongo createNull() {
		return new MovieDaoMongo(new MongoStub());
	}
	
	public String saveMovie(Movie movie) {
		String title = movie.title;
		int year = movie.year;
		Document document = new Document()
				.append("_id", new ObjectId())
				.append("title", title)
				.append("year", year);
		String id = mongo.insertOne(document);
		
		lastWrite = new Movie(title, year);
		
		return id;
	}
	
	public Movie retrieveMovie(String title, int year) {
		Document document = mongo.find(title, year);
		return new Movie(document.getString("title"), document.getInteger("year"));
	}

	public Movie getLastWrite() {
		return lastWrite;
	}
	
	MovieDaoMongo(ForWrappingMongo mongo) {
		this.mongo = mongo;
	}
	
	static class MongoWrapper implements ForWrappingMongo {
		private MongoCollection<Document> mongoCollection;
		
		MongoClient client = MongoClients.create(uri);
		MongoDatabase database = client.getDatabase(databaseName);
		
		public MongoWrapper() {
			mongoCollection = database.getCollection(collectionName);;
		}
		
		public String insertOne(Document document) {
			InsertOneResult result = mongoCollection.insertOne(document);
			return result.getInsertedId().toString();
		}

		public Document find(String title, int year) {
			Bson projectionFields = Projections.fields(Projections.include("title", "year"));
			return mongoCollection.find(and(eq("title", title), eq("year", year))).projection(projectionFields).first();
		}
	}
	
	static class MongoStub implements ForWrappingMongo {
		public String insertOne(Document document) {
			return new ObjectId().toString();
		}

		public Document find(String title, int year) {
			return new Document()
					.append("_id", new ObjectId())
					.append("title", title)
					.append("year", year);
		}
	}
	
	interface ForWrappingMongo {
		String insertOne(Document document);

		Document find(String title, int year);
	}
}
