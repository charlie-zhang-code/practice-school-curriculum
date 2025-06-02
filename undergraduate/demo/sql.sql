create database miniapp_db;
use miniapp_db;

drop table if exists t_user;
create table if not exists t_user
(
    id          int(11) auto_increment not null, -- id
    avatar      varchar(255)           not null, -- 头像
    nickname    varchar(255)           not null, -- 昵称
    username    varchar(255)           not null, -- 用户名
    password    varchar(255)           not null, -- 密码
    openid      VARCHAR(255)           NULL COMMENT '微信openid',
    session_key VARCHAR(255)           NULL COMMENT '微信session_key',
    cTime       timestamp default current_timestamp() on update current_timestamp(),
    primary key (id)
);

USE miniapp_db;

CREATE TABLE IF NOT EXISTS `t_test`
(
    `id`      int(11)      NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) NOT NULL,
    `age`     int(11)      NOT NULL,
    `sex`     int(11)      NOT NULL,
    `address` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO t_test(name, age, sex, address)
VALUES ('张三', 18, 1, '北京'),
       ('李四', 19, 1, '上海'),
       ('王五', 20, 0, '广州'),
       ('赵六', 21, 0, '深圳');

drop table if exists t_news;
create table if not exists t_news
(
    id      int(11) auto_increment not null,
    title   varchar(255)           not null,
    img     varchar(255)           not null,
    cTime   timestamp default current_timestamp() on update current_timestamp(),
    content text                   not null,
    primary key (id)
);