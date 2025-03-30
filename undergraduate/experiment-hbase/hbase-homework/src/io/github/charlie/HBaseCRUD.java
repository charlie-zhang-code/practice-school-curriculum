package io.github.charlie;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2025/3/16
 * @Description: HBaseCRUD
 */
public class HBaseCRUD {
    public HBaseConnection hBaseConnection;
    public Admin admin;
    TableName tableName;
    HTableDescriptor tableDescriptor;
    HColumnDescriptor columnDescriptor;
    Table table;

    HBaseCRUD() {
        hBaseConnection = new HBaseConnection();
        getAdmin();
    }

    public void getAdmin() {
        try {
            admin = hBaseConnection.getConn().getAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Table getTable(String tableName) throws IOException {
        table = hBaseConnection.getConn().getTable(TableName.valueOf(tableName));
        return table;
    }

    public void createTable(String name, String[] columnFamily) throws IOException {
        tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println(tableName.toString() + " table exists");
        }
        tableDescriptor = new HTableDescriptor(tableName);
        for (String column : columnFamily) {
            columnDescriptor = new HColumnDescriptor(column);
            tableDescriptor.addFamily(columnDescriptor);
        }
        admin.createTable(tableDescriptor);
        System.out.println(tableName.toString() + " table created");
    }

    public void insertAndModify(String name, String rowKey, String columnFamilyName, String columnName, long timestamp, String value) throws IOException {
        getTable(name);
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(columnFamilyName), Bytes.toBytes(columnName), timestamp, Bytes.toBytes(value));
        table.put(put);
        table.close();
    }


    public void selectByGet(String name, String rowKey, String columnFamilyName, String columnName) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = getTable(name).get(get);
        byte[] value = result.getValue(Bytes.toBytes(columnFamilyName), Bytes.toBytes(columnName));
        System.out.println(Bytes.toString(value));
    }

    public void selectByCell(String name, String rowKey, String columnFamilyName, String columnName, int maxVersion) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        get.setMaxVersions(maxVersion);
        Result result = getTable(name).get(get);
        List<Cell> cells = result.getColumnCells(Bytes.toBytes(columnFamilyName), Bytes.toBytes(columnName));
        for (Cell cell : cells) {
            byte[] value = CellUtil.cloneValue(cell);
            System.out.println(Bytes.toString(value));
        }
    }

    public void selectByRow(String name, String rowKey) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = getTable(name).get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println(new String(CellUtil.getCellKeyAsString(cell)));
            System.out.println(new String(CellUtil.cloneFamily(cell)));
            System.out.println(new String(CellUtil.cloneQualifier(cell)));
            System.out.println(new String(CellUtil.cloneValue(cell)));
            System.out.println(cell.getTimestamp());
        }
    }

    public void turncateTable(String name) throws IOException {
        getAdmin();
        tableName = TableName.valueOf(name);
        admin.disableTable(tableName);
        admin.truncateTable(tableName, false);
    }

    public static long timestampStringConvert2Long(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
        return LocalDateTime.from(localDateTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 根据行键删除指定表中的整行数据
     *
     * @param name   表名
     * @param rowKey 要删除的行的唯一标识键
     */
    public void deleteByRow(String name, String rowKey) throws IOException {
        // 创建一个Delete对象，指定要删除的行键
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 获取指定的表并执行删除操作
        getTable(name).delete(delete);
    }

    /**
     * 根据行键和列限定符删除指定表中的特定列数据
     *
     * @param name             表名
     * @param rowKey           要删除数据的行的唯一标识键
     * @param columnFamilyName 列族名称
     * @param columnName       列名称
     */
    public void deleteByColumn(String name, String rowKey, String columnFamilyName, String columnName) throws IOException {
        // 创建一个Delete对象，指定要删除的行键
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 添加要删除的列，包括列族和列名
        delete.addColumn(Bytes.toBytes(columnFamilyName), Bytes.toBytes(columnName));
        // 获取指定的表并执行删除操作
        getTable(name).delete(delete);
    }

    /**
     * 根据行键、列族和列名以及时间戳删除指定表中的特定版本的数据
     *
     * @param name             表名
     * @param rowKey           要删除数据的行的唯一标识键
     * @param columnFamilyName 列族名称
     * @param columnName       列名称
     * @param timestamp        数据的时间戳，用于指定要删除的具体版本
     */
    public void deleteByTime(String name, String rowKey, String columnFamilyName, String columnName, String timestamp) throws IOException {
        // 创建一个Delete对象，指定要删除的行键
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 添加要删除的列及其时间戳，时间戳需要转换为长整型
        delete.addColumns(Bytes.toBytes(columnFamilyName), Bytes.toBytes(columnName), timestampStringConvert2Long(timestamp));
        // 获取指定的表并执行删除操作
        getTable(name).delete(delete);
    }

    /**
     * 删除指定的表
     *
     * @param name 表名
     */
    public void deleteTable(String name) throws IOException {
        getAdmin();

        // 检查表是否存在
        if (admin.tableExists(TableName.valueOf(name))) {
            // 禁用表，确保在删除之前表处于禁用状态
            admin.disableTable(TableName.valueOf(name));
            // 删除表
            admin.deleteTable(TableName.valueOf(name));
            System.out.println("表 " + name + " 已删除。");
        } else {
            System.out.println("表 " + name + " 不存在。");
        }
    }
}
