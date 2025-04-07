package io.github.charlie;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/4/1
 * @Description: TODO
 */
public class MongoDemo03 {
    public static void main(String[] args) {
        String host = "47.98.108.68";
        int port = 27004;
        String dbName = "test";
        String collectionName = "kpl";

        MongoConnection mongoConnection = new MongoConnection(host, port, dbName);
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        MongoCRUD mongoCRUD = new MongoCRUD();

        // Display All Documents
        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName));

    }
}
