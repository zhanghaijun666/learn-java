-- CREATE DATABASE IF NOT EXISTS learn DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11)      NOT NULL,
    `username` varchar(255) NOT NULL,
    `sex`      varchar(50) DEFAULT NULL,
    `birthday` timestamp,
    `address` varchar(255),
    PRIMARY KEY (`id`)
);
INSERT INTO `user`(id,username,sex,birthday,address)
VALUES (1, '千年老亚瑟', '男',now(),'北京'),
       (2, '小妲己', '女',now(),'上海'),
       (3, '凯哥', '男',now(),'杭州');