/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50555
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50555
File Encoding         : 65001

Date: 2018-04-16 16:55:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'spf', '23');
INSERT INTO `tb_user` VALUES ('2', 'xdj', '24');
INSERT INTO `tb_user` VALUES ('3', 'fx', '24');
INSERT INTO `tb_user` VALUES ('4', 'sx', '23');
INSERT INTO `tb_user` VALUES ('5', 'xx', '23');
