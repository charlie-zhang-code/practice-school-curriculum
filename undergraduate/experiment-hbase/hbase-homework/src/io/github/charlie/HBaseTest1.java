package io.github.charlie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2025/3/16
 * @Description: TODO
 */
public class HBaseTest1 {
    public static void main(String[] args) throws IOException {
        // 操作测试
        HBaseCRUD hBaseCRUD = new HBaseCRUD();

        String tableName = "kpl";

        long timestamp = 5L;

        String[] columnFamily = {"role", "attack", "defence"};

        String roleColumnFamilyName = "role";
        String roleAttrName = "name";
        String roleAttrType = "type";
        String roleAttrDuty = "duty";

        String attackColumnFamilyName = "attack";
        String attackAttrSkill = "skill";
        String attackAttrDamage = "damage";

        String defenceColumnFamilyName = "defence";
        String defenceAttrArmor = "armor";
        String defenceAttrResistance = "resistance";

        hBaseCRUD.createTable(tableName, columnFamily);

        hBaseCRUD.insertAndModify(tableName, "0001", roleColumnFamilyName, roleAttrName, timestamp, "Hanxin");
        hBaseCRUD.insertAndModify(tableName, "0001", roleColumnFamilyName, roleAttrType, timestamp, "Warrior");
        hBaseCRUD.insertAndModify(tableName, "0001", roleColumnFamilyName, roleAttrDuty, timestamp, "Wild");
        hBaseCRUD.insertAndModify(tableName, "0001", attackColumnFamilyName, attackAttrSkill, timestamp, "Slash");
        hBaseCRUD.insertAndModify(tableName, "0001", attackColumnFamilyName, attackAttrDamage, timestamp, "150");
        hBaseCRUD.insertAndModify(tableName, "0001", defenceColumnFamilyName, defenceAttrArmor, timestamp, "100");
        hBaseCRUD.insertAndModify(tableName, "0001", defenceColumnFamilyName, defenceAttrResistance, timestamp, "80");

        hBaseCRUD.insertAndModify(tableName, "0002", roleColumnFamilyName, roleAttrName, timestamp, "Libai");
        hBaseCRUD.insertAndModify(tableName, "0002", roleColumnFamilyName, roleAttrType, timestamp, "Warrior");
        hBaseCRUD.insertAndModify(tableName, "0002", roleColumnFamilyName, roleAttrDuty, timestamp, "Wild");
        hBaseCRUD.insertAndModify(tableName, "0002", attackColumnFamilyName, attackAttrSkill, timestamp, "Fireball");
        hBaseCRUD.insertAndModify(tableName, "0002", attackColumnFamilyName, attackAttrDamage, timestamp, "120");
        hBaseCRUD.insertAndModify(tableName, "0002", defenceColumnFamilyName, defenceAttrArmor, timestamp, "80");
        hBaseCRUD.insertAndModify(tableName, "0002", defenceColumnFamilyName, defenceAttrResistance, timestamp, "60");

        hBaseCRUD.insertAndModify(tableName, "0003", roleColumnFamilyName, roleAttrName, timestamp, "Zhuangzhou");
        hBaseCRUD.insertAndModify(tableName, "0003", roleColumnFamilyName, roleAttrType, timestamp, "Support");
        hBaseCRUD.insertAndModify(tableName, "0003", roleColumnFamilyName, roleAttrDuty, timestamp, "Controller");
        hBaseCRUD.insertAndModify(tableName, "0003", attackColumnFamilyName, attackAttrSkill, timestamp, "Heal");
        hBaseCRUD.insertAndModify(tableName, "0003", attackColumnFamilyName, attackAttrDamage, timestamp, "50");
        hBaseCRUD.insertAndModify(tableName, "0003", defenceColumnFamilyName, defenceAttrArmor, timestamp, "60");
        hBaseCRUD.insertAndModify(tableName, "0003", defenceColumnFamilyName, defenceAttrResistance, timestamp, "90");
    }
}
