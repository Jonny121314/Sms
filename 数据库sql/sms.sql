/*
Navicat MySQL Data Transfer

Source Server         : 127-33066
Source Server Version : 50723
Source Host           : localhost:33066
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-01-14 14:33:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `accessId` varchar(68) NOT NULL COMMENT '访问id',
  `accessKey` varchar(255) DEFAULT NULL COMMENT '访问密码',
  `signName` varchar(255) DEFAULT NULL COMMENT '短信签名',
  `codeTemplate` varchar(255) DEFAULT NULL COMMENT '短信模板',
  `product` varchar(255) DEFAULT NULL COMMENT '短信API产品名称',
  `domain` varchar(255) DEFAULT NULL COMMENT '短信API产品域名',
  PRIMARY KEY (`accessId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms
-- ----------------------------
INSERT INTO `sms` VALUES ('', '', '', '', 'Dysmsapi', 'dysmsapi.aliyuncs.com');
