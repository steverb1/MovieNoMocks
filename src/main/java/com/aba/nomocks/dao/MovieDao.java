package com.aba.nomocks.dao;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

public class MovieDao {
	String uri = "mongodb://127.0.0.1:27017";
	String databaseName = "moviedb";
	
	public void InsertMovie(String title, int year) {
		MongoClient client = MongoClients.create(uri);
		MongoDatabase database = client.getDatabase(databaseName);
		MongoCollection<Document> collection = database.getCollection("movies");
		
		Document aMovie = new Document()
				.append("_id", new ObjectId())
				.append("title", title)
				.append("year", year);
		
		InsertOneResult result = collection.insertOne(aMovie);
	}
}
