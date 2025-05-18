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
public class MongoDemo02 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 27017;
        String dbName = "mydoc";
        String collectionName = "mydoc";

        MongoConnection mongoConnection = new MongoConnection(host, port, dbName);
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        MongoCRUD mongoCRUD = new MongoCRUD();

        int totalDocuments = 20;
        String name = "李白";
        int startingAge = 15;
        int startingHeight = 170;
        String[] sexPattern = {"男", "男", "男", "女", "女", "女"};


        System.out.println("==============Inserting documents...==============");
        // 插入多条数据
        List<Document> documents = new ArrayList<>();
        for (int i = 1; i <= totalDocuments; i++) {
            documents.add(new Document("_id", i)
                    .append("name", name)
                    .append("age", startingAge + 2 * (i - 1))
                    .append("sex", sexPattern[(i - 1) % sexPattern.length])
                    .append("height", startingHeight + (i - 1))
            );
        }
        mongoCRUD.insertMany(mongoDatabase, collectionName, documents);

        // Display All Documents
        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName));

        System.out.println();

        System.out.println("==============Querying documents...==============");
        // 将mydoc集合的各文档中"height"为奇数的文档找出并将查找结果显示出来
        Bson heightFilter = Filters.mod("height", 2, 1);
        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName, heightFilter));

        System.out.println();

        System.out.println("==============Querying documents...==============");
        // 将mydoc集合的各文档中"age"能被2整除的文档找出并将结果显示出来
        Bson ageFilter = Filters.mod("age", 2, 0);
        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName, ageFilter));

        System.out.println();

        System.out.println("==============Updating documents...==============");
        // 将mydoc集合的各文档中"sex"为"女"的找出并将"name"修改为"我是你的小可爱"
        Bson sexFilter = Filters.eq("sex", "女");
        Bson update = new Document("$set", new Document("name", "我是你的小可爱"));
        mongoCRUD.updateMany(mongoDatabase, collectionName, sexFilter, update);
        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName));

        System.out.println();

        System.out.println("==============Deleting documents...==============");
        // 将mydoc集合中各文档的"_id"值在区间[5,10]的文档删除
        Bson idFilter = Filters.and(Filters.gte("_id", 5), Filters.lte("_id", 10));
        mongoCRUD.deleteMany(mongoDatabase, collectionName, idFilter);
        mongoCRUD.displayCollection(mongoCRUD.find(mongoDatabase, collectionName));
    }
}
