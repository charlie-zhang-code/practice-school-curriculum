package io.github.charlie;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Date: 2025/3/17
 * @Description: MongoDemo
 */
public class MongoDemo {
    public static void main(String[] args) {
        String host = "192.168.142.130";
        int port = 27017;
        String dbName = "test";

        MongoConnection mongoConnection = new MongoConnection(host, port, dbName);
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        MongoCRUD mongoCRUD = new MongoCRUD();
        String collectionName = "test";
//        mongoCRUD.createCollection(mongoConnection.getMongoDatabase(), collectionName);
    }
}
