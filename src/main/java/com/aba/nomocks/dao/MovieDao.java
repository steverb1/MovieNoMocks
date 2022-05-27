package com.aba.nomocks.dao;

import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.aba.nomocks.biz.Movie;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.CreateIndexOptions;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.DropIndexOptions;
import com.mongodb.client.model.EstimatedDocumentCountOptions;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.RenameCollectionOptions;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class MovieDao {
	static String uri = "mongodb://127.0.0.1:27017";
	static String databaseName = "moviedb";
	static String collectionName = "movies";
	MongoCollection<Document> collection;
	
	private Movie lastWrite;
	
	public static MovieDao create() {
		MongoClient client = MongoClients.create(uri);
		MongoDatabase database = client.getDatabase(databaseName);
		MongoCollection<Document> collection = database.getCollection(collectionName);
		
		return new MovieDao(collection);
	}
	
	public static MovieDao createNull() {
		return new MovieDao(new NullMongoCollection());
	}
	
	public void insertMovie(Movie movie) {
		Document aMovie = new Document()
				.append("_id", new ObjectId())
				.append("title", movie.title)
				.append("year", movie.year);
		
		InsertOneResult result = collection.insertOne(aMovie);
		
		lastWrite = new Movie(movie.title, movie.year);
	}
	
	public Movie getLastWrite() {
		return lastWrite;
	}
	
	private MovieDao(MongoCollection<Document> collection) {
		this.collection = collection;
	}
}

class NullMongoCollection implements MongoCollection<Document> {
	@Override
	public MongoNamespace getNamespace() {
		return null;
	}

	@Override
	public Class<Document> getDocumentClass() {
		return null;
	}

	@Override
	public CodecRegistry getCodecRegistry() {
		return null;
	}

	@Override
	public ReadPreference getReadPreference() {
		return null;
	}

	@Override
	public WriteConcern getWriteConcern() {
		return null;
	}

	@Override
	public ReadConcern getReadConcern() {
		return null;
	}

	@Override
	public <NewTDocument> MongoCollection<NewTDocument> withDocumentClass(Class<NewTDocument> clazz) {
		return null;
	}

	@Override
	public MongoCollection<Document> withCodecRegistry(CodecRegistry codecRegistry) {
		return null;
	}

	@Override
	public MongoCollection<Document> withReadPreference(ReadPreference readPreference) {
		return null;
	}

	@Override
	public MongoCollection<Document> withWriteConcern(WriteConcern writeConcern) {
		return null;
	}

	@Override
	public MongoCollection<Document> withReadConcern(ReadConcern readConcern) {
		return null;
	}

	@Override
	public long countDocuments() {
		return 0;
	}

	@Override
	public long countDocuments(Bson filter) {
		return 0;
	}

	@Override
	public long countDocuments(Bson filter, CountOptions options) {
		return 0;
	}

	@Override
	public long countDocuments(ClientSession clientSession) {
		return 0;
	}

	@Override
	public long countDocuments(ClientSession clientSession, Bson filter) {
		return 0;
	}

	@Override
	public long countDocuments(ClientSession clientSession, Bson filter, CountOptions options) {
		return 0;
	}

	@Override
	public long estimatedDocumentCount() {
		return 0;
	}

	@Override
	public long estimatedDocumentCount(EstimatedDocumentCountOptions options) {
		return 0;
	}

	@Override
	public <TResult> DistinctIterable<TResult> distinct(String fieldName, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public <TResult> DistinctIterable<TResult> distinct(String fieldName, Bson filter, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String fieldName,
			Class<TResult> resultClass) {
		return null;
	}

	@Override
	public <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String fieldName, Bson filter,
			Class<TResult> resultClass) {
		return null;
	}

	@Override
	public FindIterable<Document> find() {
		return null;
	}

	@Override
	public <TResult> FindIterable<TResult> find(Class<TResult> resultClass) {
		return null;
	}

	@Override
	public FindIterable<Document> find(Bson filter) {
		return null;
	}

	@Override
	public <TResult> FindIterable<TResult> find(Bson filter, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public FindIterable<Document> find(ClientSession clientSession) {
		return null;
	}

	@Override
	public <TResult> FindIterable<TResult> find(ClientSession clientSession, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public FindIterable<Document> find(ClientSession clientSession, Bson filter) {
		return null;
	}

	@Override
	public <TResult> FindIterable<TResult> find(ClientSession clientSession, Bson filter, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public AggregateIterable<Document> aggregate(List<? extends Bson> pipeline) {
		return null;
	}

	@Override
	public <TResult> AggregateIterable<TResult> aggregate(List<? extends Bson> pipeline, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public AggregateIterable<Document> aggregate(ClientSession clientSession, List<? extends Bson> pipeline) {
		return null;
	}

	@Override
	public <TResult> AggregateIterable<TResult> aggregate(ClientSession clientSession, List<? extends Bson> pipeline,
			Class<TResult> resultClass) {
		return null;
	}

	@Override
	public ChangeStreamIterable<Document> watch() {
		return null;
	}

	@Override
	public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> resultClass) {
		return null;
	}

	@Override
	public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
		return null;
	}

	@Override
	public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
		return null;
	}

	@Override
	public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
		return null;
	}

	@Override
	public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline,
			Class<TResult> resultClass) {
		return null;
	}

	@Override
	public MapReduceIterable<Document> mapReduce(String mapFunction, String reduceFunction) {
		return null;
	}

	@Override
	public <TResult> MapReduceIterable<TResult> mapReduce(String mapFunction, String reduceFunction,
			Class<TResult> resultClass) {
		return null;
	}

	@Override
	public MapReduceIterable<Document> mapReduce(ClientSession clientSession, String mapFunction,
			String reduceFunction) {
		return null;
	}

	@Override
	public <TResult> MapReduceIterable<TResult> mapReduce(ClientSession clientSession, String mapFunction,
			String reduceFunction, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public BulkWriteResult bulkWrite(List<? extends WriteModel<? extends Document>> requests) {
		return null;
	}

	@Override
	public BulkWriteResult bulkWrite(List<? extends WriteModel<? extends Document>> requests,
			BulkWriteOptions options) {
		return null;
	}

	@Override
	public BulkWriteResult bulkWrite(ClientSession clientSession,
			List<? extends WriteModel<? extends Document>> requests) {
		return null;
	}

	@Override
	public BulkWriteResult bulkWrite(ClientSession clientSession,
			List<? extends WriteModel<? extends Document>> requests, BulkWriteOptions options) {
		return null;
	}

	@Override
	public InsertOneResult insertOne(Document document) {
		return null;
	}

	@Override
	public InsertOneResult insertOne(Document document, InsertOneOptions options) {
		return null;
	}

	@Override
	public InsertOneResult insertOne(ClientSession clientSession, Document document) {
		return null;
	}

	@Override
	public InsertOneResult insertOne(ClientSession clientSession, Document document, InsertOneOptions options) {
		return null;
	}

	@Override
	public InsertManyResult insertMany(List<? extends Document> documents) {
		return null;
	}

	@Override
	public InsertManyResult insertMany(List<? extends Document> documents, InsertManyOptions options) {
		return null;
	}

	@Override
	public InsertManyResult insertMany(ClientSession clientSession, List<? extends Document> documents) {
		return null;
	}

	@Override
	public InsertManyResult insertMany(ClientSession clientSession, List<? extends Document> documents,
			InsertManyOptions options) {
		return null;
	}

	@Override
	public DeleteResult deleteOne(Bson filter) {
		return null;
	}

	@Override
	public DeleteResult deleteOne(Bson filter, DeleteOptions options) {
		return null;
	}

	@Override
	public DeleteResult deleteOne(ClientSession clientSession, Bson filter) {
		return null;
	}

	@Override
	public DeleteResult deleteOne(ClientSession clientSession, Bson filter, DeleteOptions options) {
		return null;
	}

	@Override
	public DeleteResult deleteMany(Bson filter) {
		return null;
	}

	@Override
	public DeleteResult deleteMany(Bson filter, DeleteOptions options) {
		return null;
	}

	@Override
	public DeleteResult deleteMany(ClientSession clientSession, Bson filter) {
		return null;
	}

	@Override
	public DeleteResult deleteMany(ClientSession clientSession, Bson filter, DeleteOptions options) {
		return null;
	}

	@Override
	public UpdateResult replaceOne(Bson filter, Document replacement) {
		return null;
	}

	@Override
	public UpdateResult replaceOne(Bson filter, Document replacement, ReplaceOptions replaceOptions) {
		return null;
	}

	@Override
	public UpdateResult replaceOne(ClientSession clientSession, Bson filter, Document replacement) {
		return null;
	}

	@Override
	public UpdateResult replaceOne(ClientSession clientSession, Bson filter, Document replacement,
			ReplaceOptions replaceOptions) {
		return null;
	}

	@Override
	public UpdateResult updateOne(Bson filter, Bson update) {
		return null;
	}

	@Override
	public UpdateResult updateOne(Bson filter, Bson update, UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateOne(ClientSession clientSession, Bson filter, Bson update) {
		return null;
	}

	@Override
	public UpdateResult updateOne(ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateOne(Bson filter, List<? extends Bson> update) {
		return null;
	}

	@Override
	public UpdateResult updateOne(Bson filter, List<? extends Bson> update, UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateOne(ClientSession clientSession, Bson filter, List<? extends Bson> update) {
		return null;
	}

	@Override
	public UpdateResult updateOne(ClientSession clientSession, Bson filter, List<? extends Bson> update,
			UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateMany(Bson filter, Bson update) {
		return null;
	}

	@Override
	public UpdateResult updateMany(Bson filter, Bson update, UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateMany(ClientSession clientSession, Bson filter, Bson update) {
		return null;
	}

	@Override
	public UpdateResult updateMany(ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateMany(Bson filter, List<? extends Bson> update) {
		return null;
	}

	@Override
	public UpdateResult updateMany(Bson filter, List<? extends Bson> update, UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public UpdateResult updateMany(ClientSession clientSession, Bson filter, List<? extends Bson> update) {
		return null;
	}

	@Override
	public UpdateResult updateMany(ClientSession clientSession, Bson filter, List<? extends Bson> update,
			UpdateOptions updateOptions) {
		return null;
	}

	@Override
	public Document findOneAndDelete(Bson filter) {
		return null;
	}

	@Override
	public Document findOneAndDelete(Bson filter, FindOneAndDeleteOptions options) {
		return null;
	}

	@Override
	public Document findOneAndDelete(ClientSession clientSession, Bson filter) {
		return null;
	}

	@Override
	public Document findOneAndDelete(ClientSession clientSession, Bson filter, FindOneAndDeleteOptions options) {
		return null;
	}

	@Override
	public Document findOneAndReplace(Bson filter, Document replacement) {
		return null;
	}

	@Override
	public Document findOneAndReplace(Bson filter, Document replacement, FindOneAndReplaceOptions options) {
		return null;
	}

	@Override
	public Document findOneAndReplace(ClientSession clientSession, Bson filter, Document replacement) {
		return null;
	}

	@Override
	public Document findOneAndReplace(ClientSession clientSession, Bson filter, Document replacement,
			FindOneAndReplaceOptions options) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(Bson filter, Bson update) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(Bson filter, Bson update, FindOneAndUpdateOptions options) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(ClientSession clientSession, Bson filter, Bson update) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(ClientSession clientSession, Bson filter, Bson update,
			FindOneAndUpdateOptions options) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(Bson filter, List<? extends Bson> update) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(Bson filter, List<? extends Bson> update, FindOneAndUpdateOptions options) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(ClientSession clientSession, Bson filter, List<? extends Bson> update) {
		return null;
	}

	@Override
	public Document findOneAndUpdate(ClientSession clientSession, Bson filter, List<? extends Bson> update,
			FindOneAndUpdateOptions options) {
		return null;
	}

	@Override
	public void drop() {
		
	}

	@Override
	public void drop(ClientSession clientSession) {
		
	}

	@Override
	public String createIndex(Bson keys) {
		return null;
	}

	@Override
	public String createIndex(Bson keys, IndexOptions indexOptions) {
		return null;
	}

	@Override
	public String createIndex(ClientSession clientSession, Bson keys) {
		return null;
	}

	@Override
	public String createIndex(ClientSession clientSession, Bson keys, IndexOptions indexOptions) {
		return null;
	}

	@Override
	public List<String> createIndexes(List<IndexModel> indexes) {
		return null;
	}

	@Override
	public List<String> createIndexes(List<IndexModel> indexes, CreateIndexOptions createIndexOptions) {
		return null;
	}

	@Override
	public List<String> createIndexes(ClientSession clientSession, List<IndexModel> indexes) {
		return null;
	}

	@Override
	public List<String> createIndexes(ClientSession clientSession, List<IndexModel> indexes,
			CreateIndexOptions createIndexOptions) {
		return null;
	}

	@Override
	public ListIndexesIterable<Document> listIndexes() {
		return null;
	}

	@Override
	public <TResult> ListIndexesIterable<TResult> listIndexes(Class<TResult> resultClass) {
		return null;
	}

	@Override
	public ListIndexesIterable<Document> listIndexes(ClientSession clientSession) {
		return null;
	}

	@Override
	public <TResult> ListIndexesIterable<TResult> listIndexes(ClientSession clientSession, Class<TResult> resultClass) {
		return null;
	}

	@Override
	public void dropIndex(String indexName) {
		
	}

	@Override
	public void dropIndex(String indexName, DropIndexOptions dropIndexOptions) {
		
	}

	@Override
	public void dropIndex(Bson keys) {
		
	}

	@Override
	public void dropIndex(Bson keys, DropIndexOptions dropIndexOptions) {
		
	}

	@Override
	public void dropIndex(ClientSession clientSession, String indexName) {
		
	}

	@Override
	public void dropIndex(ClientSession clientSession, Bson keys) {
		
	}

	@Override
	public void dropIndex(ClientSession clientSession, String indexName, DropIndexOptions dropIndexOptions) {
		
	}

	@Override
	public void dropIndex(ClientSession clientSession, Bson keys, DropIndexOptions dropIndexOptions) {
		
	}

	@Override
	public void dropIndexes() {
		
	}

	@Override
	public void dropIndexes(ClientSession clientSession) {
		
	}

	@Override
	public void dropIndexes(DropIndexOptions dropIndexOptions) {
		
	}

	@Override
	public void dropIndexes(ClientSession clientSession, DropIndexOptions dropIndexOptions) {
		
	}

	@Override
	public void renameCollection(MongoNamespace newCollectionNamespace) {
		
	}

	@Override
	public void renameCollection(MongoNamespace newCollectionNamespace,
			RenameCollectionOptions renameCollectionOptions) {
		
	}

	@Override
	public void renameCollection(ClientSession clientSession, MongoNamespace newCollectionNamespace) {
		
	}

	@Override
	public void renameCollection(ClientSession clientSession, MongoNamespace newCollectionNamespace,
			RenameCollectionOptions renameCollectionOptions) {
		
	}
}
