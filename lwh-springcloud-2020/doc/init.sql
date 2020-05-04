create database db2020;

CREATE TABLE `db2020`.`payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial` varchar(200) NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;