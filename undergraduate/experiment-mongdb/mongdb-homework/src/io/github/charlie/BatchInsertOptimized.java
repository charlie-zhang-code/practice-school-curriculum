package io.github.charlie;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BatchInsertOptimized {

    private static final Logger LOGGER = Logger.getLogger(BatchInsertOptimized.class.getName());

    private static final String HOST = "101.201.174.43";
    private static final int PORT = 27004;
    private static final String DATABASE_NAME = "test";
    private static final String COLLECTION_NAME = "kpl";

    private static final int BATCH_SIZE = 10000; // 每批插入的文档数
    private static final int TOTAL_RECORDS = 1_00_000_000; // 总记录数
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2; // 线程数
    private static final int LOG_INTERVAL = 100; // 每处理批打印一次日志
    private static final int RETRY_COUNT = 3; // 插入失败时的重试次数

    private static MongoDatabase mongoDatabase;
    private static MongoCollection<Document> mongoCollection;

    private static final AtomicLong totalCount = new AtomicLong(0);
    private static final AtomicLong batchCounter = new AtomicLong(0);

    // 主线程启动时间
    private static final long mainStartTime = System.currentTimeMillis();
    // 总批次数
    private static final long totalBatches = (long) TOTAL_RECORDS / (BATCH_SIZE * THREAD_COUNT);

    static {
        MongoConnection mongoConnection = new MongoConnection(HOST, PORT, DATABASE_NAME);
        mongoDatabase = mongoConnection.getMongoDatabase();
        mongoCollection = mongoDatabase.getCollection(COLLECTION_NAME);
        mongoCollection = mongoCollection.withWriteConcern(com.mongodb.WriteConcern.UNACKNOWLEDGED);
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                THREAD_COUNT, THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy() // 避免任务被丢弃
        );

        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(new InsertTask(i));
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(7, TimeUnit.DAYS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long mainEndTime = System.currentTimeMillis();
        LOGGER.info(() -> "所有线程总运行时间：" + (mainEndTime - mainStartTime) + "ms");
        LOGGER.info(() -> "总插入数据量：" + totalCount.get());
    }

    static class InsertTask implements Runnable {
        private final int threadId;

        public InsertTask(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            long threadStartTime = System.currentTimeMillis();
            List<Document> list = new ArrayList<>(BATCH_SIZE);

            try {
                for (long i = 1; i <= TOTAL_RECORDS / THREAD_COUNT; i++) {
                    for (int j = 1; j <= BATCH_SIZE; j++) {
                        long id = threadId * (TOTAL_RECORDS / THREAD_COUNT) + i;
                        list.add(createDocument(id, j));
                    }

                    insertBatchWithRetry(list, threadId);
                    list.clear();

                    // 打印日志，包含 (System.currentTimeMillis() - mainStartTime)
                    if (totalCount.get() % LOG_INTERVAL == 0) {
                        long currentTime = System.currentTimeMillis();
                        LOGGER.info(() -> "Thread " + threadId + " --> 已处理 " + (totalCount.get() / BATCH_SIZE) + " 批"
                                + " 总插入 " + totalCount.get() + " 本批用时 " + (currentTime - threadStartTime) + "ms"
                                + " 总用时 " + (currentTime - mainStartTime) + "ms");
                    }
                }

                if (!list.isEmpty()) {
                    insertBatchWithRetry(list, threadId);
                }

                long threadEndTime = System.currentTimeMillis();
                // 打印线程总运行时间，包含 (threadEndTime - mainStartTime)
                LOGGER.info(() -> "Thread " + threadId + " --> 本批用时总运行时间：" + (threadEndTime - threadStartTime) + "ms"
                        + " 总用时 " + (threadEndTime - mainStartTime) + "ms");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Thread " + threadId + " 遇到异常：", e);
            }
        }

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

        private void insertBatchWithRetry(List<Document> list, int threadId) {
            int retry = 0;
            while (retry < RETRY_COUNT) {
                try {
                    mongoCollection.insertMany(list, new InsertManyOptions().ordered(false));
                    long currentBatch = batchCounter.incrementAndGet();
                    totalCount.addAndGet(list.size());

                    // 打印当前批次和剩余批次
                    if (currentBatch % LOG_INTERVAL == 0) {
                        long remainingBatches = totalBatches - (currentBatch / THREAD_COUNT);
                        LOGGER.info(() -> "Total batches processed: " + currentBatch
                                + " | Remaining batches: " + remainingBatches);
                    }

                    return;
                } catch (Exception e) {
                    retry++;
                    LOGGER.log(Level.WARNING, "Thread " + threadId + " --> 第 " + list.size() + " 批插入失败，重试次数：" + retry, e);
                    if (retry == RETRY_COUNT) {
                        LOGGER.log(Level.SEVERE, "Thread " + threadId + " --> 第 " + list.size() + " 批插入失败，已达到最大重试次数", e);
                    }
                }
            }
        }
    }
}