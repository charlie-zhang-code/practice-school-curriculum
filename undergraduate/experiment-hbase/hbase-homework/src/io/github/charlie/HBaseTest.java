package io.github.charlie;

import java.io.IOException;

/**
 * @Date: 2025/3/16
 * @Description: HBaseTest
 */
public class HBaseTest {
    public static void main(String[] args) throws IOException {
        // HBase连接测试
        // HBaseConnection hBaseConnection = new HBaseConnection();

        // 操作测试
        HBaseCRUD hBaseCRUD = new HBaseCRUD();

        String name = "kpl";
        String rowKey = "0001";
        long timestamp = 5L;
        String columnFamilyName = "role";
        String attr_name = "name";
        String attr_type = "type";
        String attr_duty = "duty";
        String value_name = "Hanxin";
        String value_type = "Warrior";
        String value_duty = "Wild";
        String[] columnFamily = {"role", "attack", "defence"};

//        hBaseCRUD.createTable(name, columnFamily);
//
//        hBaseCRUD.insertAndModify(name, rowKey, columnFamilyName, attr_name, timestamp, value_name);
//        hBaseCRUD.insertAndModify(name, rowKey, columnFamilyName, attr_type, timestamp, value_type);
//        hBaseCRUD.insertAndModify(name, rowKey, columnFamilyName, attr_duty, timestamp, value_duty);

//        hBaseCRUD.selectByGet(name, rowKey, columnFamilyName, attr_name);
//        hBaseCRUD.selectByRow(name, rowKey);

//        hBaseCRUD.turncateTable(name);

        // 删除数据
//         hBaseCRUD.deleteByRow(name, rowKey);
//         hBaseCRUD.deleteByColumn(name, rowKey, columnFamilyName, attr_name);
        // hBaseCRUD.deleteByTime(name, rowKey, columnFamilyName, attr_name, String.valueOf(timestamp));

        // 删除表
//         hBaseCRUD.deleteTable(name);
    }
}
