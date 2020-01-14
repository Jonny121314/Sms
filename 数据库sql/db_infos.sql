/*
Navicat MySQL Data Transfer

Source Server         : 127-33066
Source Server Version : 50723
Source Host           : localhost:33066
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-01-14 14:23:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_infos
-- ----------------------------
DROP TABLE IF EXISTS `db_infos`;
CREATE TABLE `db_infos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schoolId` int(11) NOT NULL COMMENT '学校id',
  `db_name` varchar(50) NOT NULL COMMENT '数据库名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_infos
-- ----------------------------
