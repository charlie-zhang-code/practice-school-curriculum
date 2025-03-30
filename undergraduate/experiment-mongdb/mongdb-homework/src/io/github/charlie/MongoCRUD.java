package io.github.charlie;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * @Date: 2025/3/17
 * @Description: MongoCRUD
 */
public class MongoCRUD {
    public void createCollection(MongoDatabase mongoDatabase, String collectionName) {
        mongoDatabase.createCollection(collectionName);
    }

    public MongoCollection<Document> getCollection(MongoDatabase mongoDatabase, String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }

    public FindIterable<Document> getIterator(MongoDatabase mongoDatabase, String collectionName) {
        return mongoDatabase.getCollection(collectionName).find();
    }

    public FindIterable<Document> getIterator(MongoDatabase mongoDatabase, String collectionName, Bson filter) {
        return mongoDatabase.getCollection(collectionName).find(filter);
    }

    public Document findOne(MongoDatabase mongoDatabase, String collectionName) {
        return mongoDatabase.getCollection(collectionName).find().first();
    }

    public MongoCursor<Document> find(MongoDatabase mongoDatabase, String collectionName) {
        return mongoDatabase.getCollection(collectionName).find().iterator();
    }

    public MongoCursor<Document> find(MongoDatabase mongoDatabase, String collectionName, Bson filter) {
        return mongoDatabase.getCollection(collectionName).find(filter).iterator();
    }

    public void displayCollection(Document document) {
        System.out.println(document);
    }

    public void displayCollection(MongoCursor<Document> cursor) {
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }
    }

    public boolean insertOne(MongoDatabase mongoDatabase, String collectionName, Document document) {
        try {
            getCollection(mongoDatabase, collectionName).insertOne(document);
            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        } catch (MongoWriteConcernException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertMany(MongoDatabase mongoDatabase, String collectionName, List<Document> document) {
        try {
            getCollection(mongoDatabase, collectionName).insertMany(document);
            return true;
        } catch (MongoBulkWriteException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOne(MongoDatabase mongoDatabase, String collectionName, Bson filter) {
        try {
            getCollection(mongoDatabase, collectionName).deleteOne(filter);
            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        } catch (MongoWriteConcernException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteMany(MongoDatabase mongoDatabase, String collectionName, Bson filter) {
        try {
            getCollection(mongoDatabase, collectionName).deleteMany(filter);
            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        } catch (MongoWriteConcernException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateOne(MongoDatabase mongoDatabase, String collectionName, Bson filter, Bson update) {
        try {
            getCollection(mongoDatabase, collectionName).updateOne(filter, update);
            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        } catch (MongoWriteConcernException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateMany(MongoDatabase mongoDatabase, String collectionName, Bson filter, Bson update) {
        try {
            getCollection(mongoDatabase, collectionName).updateMany(filter, update);
            return true;
        } catch (MongoWriteException e) {
            e.printStackTrace();
        } catch (MongoWriteConcernException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return false;
    }
}
