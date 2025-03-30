package io.github.charlie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;

/**
 * @Date: 2025/3/16
 * @Description: HBaseConnection
 */
public class HBaseConnection {
    public Configuration conf;
    public Connection conn;
    public AggregationClient aggregationClient;

    HBaseConnection() {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "Dev");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "Dev:2181");
        try {
            conn = ConnectionFactory.createConnection(conf);
            System.out.println("HBase Connection Success");
        } catch (Exception e) {
            System.out.println("HBase Connection Failed");
            e.printStackTrace();
        }
        aggregationClient = new AggregationClient(conf);
    }

    public Configuration getConf() {
        return conf;
    }

    public Connection getConn() {
        return conn;
    }
}
