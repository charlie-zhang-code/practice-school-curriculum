-- 自习室管理系统数据库
DROP DATABASE IF EXISTS study_room_management;

CREATE DATABASE study_room_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用自习室管理系统数据库
USE study_room_management;

-- 用户表
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    nikename VARCHAR(50) NULL COMMENT '昵称',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '哈希之后的密码',
    email VARCHAR(100) NOT NULL COMMENT '邮箱',
    gender INT DEFAULT 0 COMMENT '性别,0:未知,1:男,2:女',
    avatar VARCHAR(255) NULL COMMENT '头像',
    `status` INT DEFAULT 1 COMMENT '状态,0:禁用,1:正常',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 权限表
DROP TABLE IF EXISTS permissions;

CREATE TABLE permissions (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID',
    `name` VARCHAR(50) NOT NULL COMMENT '权限名称',
    `description` TEXT NULL COMMENT '权限描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 用户权限表
DROP TABLE IF EXISTS user_permissions;

CREATE TABLE user_permissions (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户权限ID',
    user_id INT NOT NULL COMMENT '用户ID',
    permission_id INT NOT NULL COMMENT '权限ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 自习室表
DROP TABLE IF EXISTS study_rooms;

CREATE TABLE study_rooms (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '自习室ID',
    `name` VARCHAR(50) NOT NULL COMMENT '自习室名称',
    `address` VARCHAR(255) NOT NULL COMMENT '自习室地址',
    `location` VARCHAR(255) NOT NULL COMMENT '自习室位置',
    `description` TEXT NULL COMMENT '自习室描述',
    open_time TIME NOT NULL COMMENT '自习室开放时间',
    close_time TIME NOT NULL COMMENT '自习室关闭时间',
    cover VARCHAR(255) NULL COMMENT '自习室封面',
    capacity INT NOT NULL COMMENT '自习室容量',
    `status` INT DEFAULT 1 COMMENT '状态,0:禁用,1:正常',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

INSERT INTO
    study_rooms (
        `name`,
        `address`,
        `location`,
        `description`,
        open_time,
        close_time,
        capacity,
        `status`
    )
VALUES
    (
        '自习室1',
        '地址1',
        '位置1',
        '描述1',
        '08:00:00',
        '22:00:00',
        20,
        1
    ),
    (
        '自习室2',
        '地址2',
        '位置2',
        '描述2',
        '08:00:00',
        '22:00:00',
        30,
        1
    );

-- 座位表
DROP TABLE IF EXISTS seats;

CREATE TABLE seats (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '座位ID',
    study_room_id INT NOT NULL COMMENT '自习室ID',
    seat_number VARCHAR(25) NOT NULL COMMENT '座位号',
    `status` INT DEFAULT 0 COMMENT '状态,0:空闲,1:占用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

INSERT INTO
    seats (study_room_id, seat_number, `status`)
VALUES
    (1, 'A1', 0),
    (1, 'A2', 0),
    (2, 'B1', 0),
    (2, 'B2', 0);

-- 自习室类型表
DROP TABLE IF EXISTS study_room_types;

CREATE TABLE study_room_types (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '自习室类型ID',
    `name` VARCHAR(50) NOT NULL COMMENT '自习室类型名称',
    `description` TEXT NULL COMMENT '自习室类型描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 关联表
DROP TABLE IF EXISTS study_room_type_relation;

CREATE TABLE study_room_type_relation (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '关联ID',
    study_room_id INT NOT NULL COMMENT '自习室ID',
    study_room_type_id INT NOT NULL COMMENT '自习室类型ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 预约表
DROP TABLE IF EXISTS reservations;

CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '预约ID',
    user_id INT NOT NULL COMMENT '用户ID',
    study_room_id INT NOT NULL COMMENT '自习室ID',
    seat_id INT NOT NULL COMMENT '座位ID',
    start_time DATETIME NOT NULL COMMENT '预约开始时间',
    end_time DATETIME NOT NULL COMMENT '预约结束时间',
    `status` INT DEFAULT 0 COMMENT '状态,0:未开始,1:进行中,2:已完成,3:已取消',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

SELECT
    t1.id AS reservation_id,
    t3.name AS study_room_name,
    t2.seat_number,
    t1.start_time,
    t1.end_time,
    t1.status
FROM
    reservations t1
    LEFT JOIN seats t2 ON t1.seat_id = t2.id
    LEFT JOIN study_rooms t3 ON t1.study_room_id = t3.id
    LEFT JOIN users t4 ON t1.user_id = t4.id
WHERE
    t1.status = 0;

-- 创建取消未确认预约的事件
-- 每分钟执行一次，查找10分钟内未确认的预约并取消它们
DELIMITER $$

CREATE EVENT IF NOT EXISTS event_cancel_unconfirmed_reservations
ON SCHEDULE EVERY 1 MINUTE
DO
BEGIN
  UPDATE reservations
  SET status = '3'
  WHERE status = '0' AND NOW() > created_at + INTERVAL 10 MINUTE;
END $$

DELIMITER ;

-- 创建更新座位状态为空闲的事件
-- 每分钟执行一次，查找已过期的预约并更新座位状态为空闲
DELIMITER $$
CREATE EVENT IF NOT EXISTS event_update_seat_status
ON SCHEDULE EVERY 1 MINUTE
DO
BEGIN
  UPDATE seats
  SET status = '0'
  WHERE EXISTS (
    SELECT 1
    FROM reservations
    WHERE reservations.seat_id = seats.id AND reservations.end_time < NOW()
  );
END $$

DELIMITER ;