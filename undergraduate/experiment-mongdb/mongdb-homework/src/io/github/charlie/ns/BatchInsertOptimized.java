package io.github.charlie.ns;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import io.github.charlie.MongoConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BatchInsertOptimized {

    private static final Logger LOGGER = Logger.getLogger(BatchInsertOptimized.class.getName());

    // 配置常量
    private static final String HOST = "101.201.174.43";
    private static final int PORT = 27004;
    private static final String DATABASE_NAME = "test";
    private static final String COLLECTION_NAME = "kpl";

    // 性能参数
    private static final int BATCH_SIZE = 10_000; // 每批插入的文档数
    private static final int TOTAL_RECORDS = 100_000_000; // 总记录数
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2; // 线程数
    private static final int LOG_INTERVAL = 100; // 日志打印间隔
    private static final int RETRY_COUNT = 3; // 插入失败时的重试次数
    private static final int MAX_QUEUE_SIZE = 100; // 线程池队列大小

    // MongoDB资源
    private static final MongoCollection<Document> mongoCollection;

    // 计数器
    private static final AtomicLong totalCount = new AtomicLong(0);
    private static final AtomicLong batchCounter = new AtomicLong(0);
    private static final AtomicLong failedBatches = new AtomicLong(0);

    // 计时
    private static final long mainStartTime = System.currentTimeMillis();

    static {
        // 初始化MongoDB连接
        MongoConnection mongoConnection = new MongoConnection(HOST, PORT, DATABASE_NAME);
        mongoCollection = mongoConnection.getMongoDatabase()
                .getCollection(COLLECTION_NAME)
                .withWriteConcern(com.mongodb.WriteConcern.UNACKNOWLEDGED);
    }

    public static void main(String[] args) {
        LOGGER.info("Starting batch insert with " + THREAD_COUNT + " threads");

        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                THREAD_COUNT, THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(MAX_QUEUE_SIZE),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        // 提交任务
        List<Future<?>> futures = new ArrayList<>(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(executorService.submit(new InsertTask(i)));
        }

        // 关闭线程池并等待完成
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                LOGGER.warning("Forcing shutdown after timeout");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Main thread interrupted", e);
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // 打印最终统计信息
        long mainEndTime = System.currentTimeMillis();
        long totalTime = mainEndTime - mainStartTime;
        double recordsPerSecond = (totalCount.get() * 1000.0) / totalTime;

        LOGGER.info(() -> String.format(
                "All threads completed in %d ms (%.2f records/sec)%n" +
                        "Total inserted: %,d%n" +
                        "Failed batches: %,d",
                totalTime, recordsPerSecond, totalCount.get(), failedBatches.get()));
    }

    static class InsertTask implements Runnable {
        private final int threadId;
        private final long recordsPerThread;
        private final List<Document> batchBuffer;

        public InsertTask(int threadId) {
            this.threadId = threadId;
            this.recordsPerThread = (long) Math.ceil((double) TOTAL_RECORDS / THREAD_COUNT);
            this.batchBuffer = new ArrayList<>(BATCH_SIZE);
        }

        @Override
        public void run() {
            long threadStartTime = System.currentTimeMillis();
            long recordsProcessed = 0;

            try {
                while (recordsProcessed < recordsPerThread) {
                    batchBuffer.clear();

                    // 填充批次
                    int batchRecords = (int) Math.min(BATCH_SIZE, recordsPerThread - recordsProcessed);
                    for (int j = 0; j < batchRecords; j++) {
                        long id = threadId * recordsPerThread + recordsProcessed + j + 1;
                        batchBuffer.add(createDocument(id));
                    }

                    // 插入批次
                    if (!batchBuffer.isEmpty()) {
                        insertBatchWithRetry(batchBuffer);
                        recordsProcessed += batchBuffer.size();
                    }

                    // 定期日志
                    if (recordsProcessed % (BATCH_SIZE * LOG_INTERVAL) == 0) {
                        logProgress(threadStartTime, recordsProcessed);
                    }
                }

                logCompletion(threadStartTime, recordsProcessed);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Thread " + threadId + " encountered exception", e);
            }
        }

        private Document createDocument(long id) {
            return new Document("id", id)
                    .append("name", "Name-" + id)
                    .append("english", ThreadLocalRandom.current().nextInt(101))
                    .append("math", ThreadLocalRandom.current().nextInt(101))
                    .append("chinese", ThreadLocalRandom.current().nextInt(101))
                    .append("geography", ThreadLocalRandom.current().nextInt(101))
                    .append("music", ThreadLocalRandom.current().nextInt(101))
                    .append("love", ThreadLocalRandom.current().nextInt(101));
        }

        private void insertBatchWithRetry(List<Document> batch) {
            int retry = 0;
            while (retry < RETRY_COUNT) {
                try {
                    mongoCollection.insertMany(batch, new InsertManyOptions().ordered(false));
                    totalCount.addAndGet(batch.size());
                    batchCounter.incrementAndGet();
                    return;
                } catch (Exception e) {
                    retry++;
                    if (retry == RETRY_COUNT) {
                        failedBatches.incrementAndGet();
                        LOGGER.log(Level.SEVERE,
                                String.format("Thread %d failed to insert batch of %d after %d retries",
                                        threadId, batch.size(), RETRY_COUNT), e);
                    } else {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100 * retry); // 指数退避
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        }

        private void logProgress(long threadStartTime, long recordsProcessed) {
            long currentTime = System.currentTimeMillis();
            long threadTime = currentTime - threadStartTime;
            long totalTime = currentTime - mainStartTime;

            LOGGER.info(() -> String.format(
                    "Thread %d: %,d records (%.1f%%) | Thread time: %,dms | Total time: %,dms",
                    threadId, recordsProcessed,
                    (recordsProcessed * 100.0 / recordsPerThread),
                    threadTime, totalTime));
        }

        private void logCompletion(long threadStartTime, long recordsProcessed) {
            long threadEndTime = System.currentTimeMillis();
            double recordsPerSec = (recordsProcessed * 1000.0) / (threadEndTime - threadStartTime);

            LOGGER.info(() -> String.format(
                    "Thread %d completed: %,d records in %,dms (%.1f records/sec)",
                    threadId, recordsProcessed,
                    (threadEndTime - threadStartTime), recordsPerSec));
        }
    }
}