package io.github.charlie;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @Date: 2025/3/17
 * @Description: MongoConnection
 */
public class MongoConnection {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public MongoConnection(String host, int port, String dbName) {
        try {
            this.mongoClient = new MongoClient(host, port);
            this.mongoDatabase = mongoClient.getDatabase(dbName);
            System.out.println("MongoDB连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeMongo() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB连接关闭！");
        }
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
