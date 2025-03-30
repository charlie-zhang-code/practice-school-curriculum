package io.github.charlie;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2025/3/17
 * @Description: MongoDemo
 */
public class MongoDemo01 {
    public static void main(String[] args) {
        String host = "192.168.142.130";
        int port = 27017;
        String dbName = "stuinfo";
        String collectionName = "stuinfo";

        MongoConnection mongoConnection = new MongoConnection(host, port, dbName);
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        MongoCRUD mongoCRUD = new MongoCRUD();

        // 查询一条数据
        Document document = mongoCRUD.findOne(mongoDatabase, collectionName);
        mongoCRUD.displayCollection(document);

        System.out.println("==========================");

        // 查询多条数据
        MongoCursor<Document> cursor = mongoCRUD.find(mongoDatabase, collectionName);
        mongoCRUD.displayCollection(cursor);

        System.out.println("==========================");

        // 过滤条件查询
        String filedName = "name";
        String filedValue = "张三";
        Bson filter = Filters.eq(filedName, filedValue);
        MongoCursor<Document> cursor1 = mongoCRUD.find(mongoDatabase, collectionName, filter);
        mongoCRUD.displayCollection(cursor1);

        System.out.println("==========================");

        // 插入一条数据
//        Document document1 = new Document("_id", 105).append("name", "大李四").append("age", 18);
//        boolean b = mongoCRUD.insertOne(mongoDatabase, collectionName, document1);
//        Bson filter1 = Filters.eq("_id", 105);
//        MongoCursor<Document> cursor2 = mongoCRUD.find(mongoDatabase, collectionName, filter1);
//        mongoCRUD.displayCollection(cursor2);

        // 插入多条数据
//        List<Document> documents = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Document document1 = new Document("_id", 106 + i).append("name", "大李四" + i).append("age", 18 + i);
//            documents.add(document1);
//        }
//        boolean b = mongoCRUD.insertMany(mongoDatabase, collectionName, documents);
//        MongoCursor<Document> cursor3 = mongoCRUD.find(mongoDatabase, collectionName);
//        mongoCRUD.displayCollection(cursor3);

        // 删除一条数据
//        Bson filter2 = Filters.eq("_id", 105);
//        boolean b = mongoCRUD.deleteOne(mongoDatabase, collectionName, filter2);
//        MongoCursor<Document> cursor4 = mongoCRUD.find(mongoDatabase, collectionName);
//        mongoCRUD.displayCollection(cursor4);

        // 删除多条数据
//        Bson filter2 = Filters.and(Filters.gt("_id", 108), Filters.lt("_id", 114));
//        boolean b = mongoCRUD.deleteMany(mongoDatabase, collectionName, filter2);
//        MongoCursor<Document> cursor4 = mongoCRUD.find(mongoDatabase, collectionName);
//        mongoCRUD.displayCollection(cursor4);

        // 更新一条数据
//        Bson filter2 = Filters.eq("_id", 116);
//        Document document1 = new Document("_id", 116).append("name", "大李四116").append("age", 18);
//        mongoCRUD.insertOne(mongoDatabase, collectionName, document1);
//        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName));
//
//       Document update = new Document("$set", new Document("name", "大李四116789").append("age", 14));
//       boolean b = mongoCRUD.updateOne(mongoDatabase, collectionName, filter2, update);
//        MongoCursor<Document> cursor4 = mongoCRUD.find(mongoDatabase, collectionName);
//        mongoCRUD.displayCollection(cursor4);

        // 更新多条数据
//        List<Document> documents = new ArrayList<>();
//        for (int i = 150; i < 155; i++) {
//            Document document1 = new Document("_id", i + 5).append("name", "LacksTSC").append("age", i);
//            documents.add(document1);
//        }
//        mongoCRUD.insertMany(mongoDatabase, collectionName, documents);
//        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName));
//
//        Bson filter2 = Filters.eq("name", "LacksTSC");
//        Document update = new Document("$set", new Document("name", "LacksCoco").append("age", 99));
//        boolean b = mongoCRUD.updateMany(mongoDatabase, collectionName, filter2, update);
//        MongoCursor<Document> cursor4 = mongoCRUD.find(mongoDatabase, collectionName);
//        mongoCRUD.displayCollection(cursor4);
    }
}
