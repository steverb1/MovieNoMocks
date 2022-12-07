package com.aba.nomocks.dao;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.aba.nomocks.biz.Movie;
import com.aba.nomocks.service.ForPersistingMovies;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
	
	public void saveMovie(Movie movie) {
		Document aMovie = new Document()
				.append("_id", new ObjectId())
				.append("title", movie.title)
				.append("year", movie.year);
		
		InsertOneResult result = mongo.insertOne(aMovie);
		
		lastWrite = new Movie(movie.title, movie.year);
	}
	
	public Movie getLastWrite() {
		return lastWrite;
	}
	
	private MovieDaoMongo(ForWrappingMongo mongo) {
		this.mongo = mongo;
	}
	
	static class MongoWrapper implements ForWrappingMongo {
		private MongoCollection<Document> mongoCollection;
		
		MongoClient client = MongoClients.create(uri);
		MongoDatabase database = client.getDatabase(databaseName);
		
		public MongoWrapper() {
			mongoCollection = database.getCollection(collectionName);;
		}
		
		@Override
		public InsertOneResult insertOne(Document document) {
			return mongoCollection.insertOne(document);
		}
	}
	
	static class MongoStub implements ForWrappingMongo {
		@Override
		public InsertOneResult insertOne(Document document) {
			return null;
		}
	}
	
	interface ForWrappingMongo {
		InsertOneResult insertOne(Document document);
	}
}
