//package io.github.charlie;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author: charlie-zhang-code
// * @Date: 2025/4/1
// * @Description: 批量插入数据并显示每批插入的用时
// */
//public class InsertThread extends Thread {
//    private static final String host = "47.98.108.68";
//    private static final int port = 27004;
//    private static final String databaseName = "test";
//    private static final String collectionName = "kpl";
//
//    private static final int BATCH_SIZE = 1000; // 每批插入的数据量
//    private static final int TOTAL_RECORDS = 2 * 50000 * 1000; // 总记录数（减少为5000万条以便测试）
//    private static final int THREAD_COUNT = 20; // 线程数量
//
//    private static MongoDatabase mongoDatabase;
//    private static MongoCollection<Document> mongoCollection;
//
//    static {
//        MongoConnection mongoConnection = new MongoConnection(host, port, databaseName);
//        mongoDatabase = mongoConnection.getMongoDatabase();
//        mongoCollection = mongoDatabase.getCollection(collectionName);
//    }
//
//    private final int threadId;
//    private long counts = 0;
//
//    public InsertThread(int threadId) {
//        this.threadId = threadId;
//    }
//
//    @Override
//    public void run() {
//        long threadStartTime = System.currentTimeMillis();
//        List<Document> list = new ArrayList<>(BATCH_SIZE);
//
//        for (int i = 1; i <= TOTAL_RECORDS / THREAD_COUNT; i++) {
//            // 生成数据
//            for (int j = 1; j <= BATCH_SIZE; j++) {
//                Document document = new Document("id", threadId * (TOTAL_RECORDS / THREAD_COUNT) + i)
//                        .append("name", "Name-" + (threadId * (TOTAL_RECORDS / THREAD_COUNT) + i))
//                        .append("english", Math.round(Math.random() * 100))
//                        .append("math", Math.round(Math.random() * 100))
//                        .append("chinese", Math.round(Math.random() * 100))
//                        .append("geography", Math.round(Math.random() * 100))
//                        .append("music", Math.round(Math.random() * 100))
//                        .append("love", Math.round(Math.random() * 100));
//                list.add(document);
//            }
//
//            // 记录批量插入开始时间
//            long batchStartTime = System.currentTimeMillis();
//            insertBatch(list, threadId, counts + 1);
//            list.clear();
//            counts += BATCH_SIZE;
//
//            // 打印每批插入的耗时
//            long batchEndTime = System.currentTimeMillis();
//            long batchDuration = batchEndTime - batchStartTime;
//            System.out.println("Thread " + threadId + " --> 第 " + (counts / BATCH_SIZE) + " 批插入用时：" + batchDuration + "ms" + "总用时 " + (batchEndTime - threadStartTime) + "ms");
//        }
//
//        // 插入剩余的数据（如果有）
//        if (!list.isEmpty()) {
//            long batchStartTime = System.currentTimeMillis();
//            insertBatch(list, threadId, counts + 1);
//            long batchEndTime = System.currentTimeMillis();
//            long batchDuration = batchEndTime - batchStartTime;
//            counts += list.size();
//            System.out.println("Thread " + threadId + " --> 第 " + (counts / BATCH_SIZE) + " 批插入用时：" + batchDuration + "ms" + "总用时 " + (batchEndTime - threadStartTime) + "ms");
//        }
//
//        long threadEndTime = System.currentTimeMillis();
//        System.err.println("Thread " + threadId + " --> 总运行时间：" + (threadEndTime - threadStartTime) + "ms");
//        System.err.println("Thread " + threadId + " --> 插入数据量：" + counts);
//    }
//
//    /**
//     * 插入一批数据并处理异常
//     *
//     * @param list     数据列表
//     * @param threadId 线程ID
//     * @param batchNum 批次编号
//     */
//    private void insertBatch(List<Document> list, int threadId, long batchNum) {
//        try {
//            mongoCollection.insertMany(list);
//            // 可选：记录成功日志
//        } catch (Exception e) {
//            System.err.println("Thread " + threadId + " --> 第 " + batchNum + " 批插入失败！");
//            e.printStackTrace();
//            // 可选：实现重试机制
//        }
//    }
//
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
//
//        // 记录所有线程的开始时间
//        long mainStartTime = System.currentTimeMillis();
//
//        for (int i = 0; i < THREAD_COUNT; i++) {
//            executorService.submit(new InsertThread(i));
//        }
//
//        // 关闭线程池并等待所有任务完成
//        executorService.shutdown();
//        try {
//            if (!executorService.awaitTermination(7, TimeUnit.DAYS)) { // 设置合理的超时时间
//                executorService.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            executorService.shutdownNow();
//            Thread.currentThread().interrupt(); // 恢复中断状态
//        }
//
//        // 记录所有线程的结束时间
//        long mainEndTime = System.currentTimeMillis();
//        System.err.println("所有线程总运行时间：" + (mainEndTime - mainStartTime) + "ms");
//    }
//}




package io.github.charlie;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/4/1
 * @Description: 批量插入数据并显示每批插入的用时
 */
public class InsertThread extends Thread {
    private static final String host = "47.98.108.68";
    private static final int port = 27004;
    private static final String databaseName = "test";
    private static final String collectionName = "kpl";

    private static final int BATCH_SIZE = 1000; // 每批插入的数据量
    private static final int TOTAL_RECORDS = 2 * 50000 * 1000; // 总记录数 模拟为一亿
    private static final int THREAD_COUNT = 20; // 线程数量

    private static MongoDatabase mongoDatabase;
    private static MongoCollection<Document> mongoCollection;

    static {
        MongoConnection mongoConnection = new MongoConnection(host, port, databaseName);
        mongoDatabase = mongoConnection.getMongoDatabase();
        mongoCollection = mongoDatabase.getCollection(collectionName);
        // 设置写关注，减少等待时间
        mongoCollection = mongoCollection.withWriteConcern(com.mongodb.WriteConcern.UNACKNOWLEDGED);
    }

    private final int threadId;
    private long counts = 0;

    public InsertThread(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        long threadStartTime = System.currentTimeMillis();
        List<Document> list = new ArrayList<>(BATCH_SIZE);

        for (int i = 1; i <= TOTAL_RECORDS / THREAD_COUNT; i++) {
            // 生成数据
            for (int j = 1; j <= BATCH_SIZE; j++) {
                long id = threadId * (TOTAL_RECORDS / THREAD_COUNT) + i;
                list.add(createDocument(id, j));
            }

            // 记录批量插入开始时间
            long batchStartTime = System.currentTimeMillis();
            insertBatch(list, threadId, counts + 1);
            list.clear();
            counts += BATCH_SIZE;

            // 打印每批插入的耗时
            long batchEndTime = System.currentTimeMillis();
            long batchDuration = batchEndTime - batchStartTime;
            System.out.println("Thread " + threadId + " --> 第 " + (counts / BATCH_SIZE) + " 批插入用时：" + batchDuration + "ms" + " 总用时 " + (batchEndTime - threadStartTime) + "ms" + " 总插入 " + counts);
        }

        // 插入剩余的数据（如果有）
        if (!list.isEmpty()) {
            long batchStartTime = System.currentTimeMillis();
            insertBatch(list, threadId, counts + 1);
            long batchEndTime = System.currentTimeMillis();
            long batchDuration = batchEndTime - batchStartTime;
            counts += list.size();
            System.out.println("Thread " + threadId + " --> 第 " + (counts / BATCH_SIZE) + " 批插入用时：" + batchDuration + "ms" + " 总用时 " + (batchEndTime - threadStartTime) + "ms" + " 总插入 " + counts);
        }

        long threadEndTime = System.currentTimeMillis();
        System.err.println("Thread " + threadId + " --> 总运行时间：" + (threadEndTime - threadStartTime) + "ms");
        System.err.println("Thread " + threadId + " --> 插入数据量：" + counts);
    }

    /**
     * 创建文档对象
     *
     * @param id   文档ID
     * @param seq  序列号
     * @return 文档对象
     */
    private Document createDocument(long id, int seq) {
        return new Document("id", id)
                .append("name", "Name-" + seq)
                .append("english", Math.round(Math.random() * 100))
                .append("math", Math.round(Math.random() * 100))
                .append("chinese", Math.round(Math.random() * 100))
                .append("geography", Math.round(Math.random() * 100))
                .append("music", Math.round(Math.random() * 100))
                .append("love", Math.round(Math.random() * 100));
    }

    /**
     * 插入一批数据并处理异常
     *
     * @param list     数据列表
     * @param threadId 线程ID
     * @param batchNum 批次编号
     */
    private void insertBatch(List<Document> list, int threadId, long batchNum) {
        try {
            // 使用无确认写入，减少等待时间
            mongoCollection.insertMany(list, new InsertManyOptions().ordered(false));
        } catch (Exception e) {
            System.err.println("Thread " + threadId + " --> 第 " + batchNum + " 批插入失败！");
            e.printStackTrace();
            // 可选：实现重试机制
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        // 记录所有线程的开始时间
        long mainStartTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(new InsertThread(i));
        }

        // 关闭线程池并等待所有任务完成
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(7, TimeUnit.DAYS)) { // 设置合理的超时时间
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt(); // 恢复中断状态
        }

        // 记录所有线程的结束时间
        long mainEndTime = System.currentTimeMillis();
        System.err.println("所有线程总运行时间：" + (mainEndTime - mainStartTime) + "ms");
    }
}