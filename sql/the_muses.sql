/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mac
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : the_muses

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 10/04/2018 11:06:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for uac_user
-- ----------------------------
DROP TABLE IF EXISTS `uac_user`;
CREATE TABLE `uac_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `serial_no` varchar(50) DEFAULT NULL COMMENT '业务号',
  `login_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `login_pwd` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `user_status` varchar(5) DEFAULT '0' COMMENT '用户状态',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号主表';

-- ----------------------------
-- Records of uac_user
-- ----------------------------
BEGIN;
INSERT INTO `uac_user` VALUES ('14645c445f8342a59f8d30ec6559f05d', '1', 'admin', 'CDA768EE390582B1D872B041A584496B', '1', NULL, NULL, '2018-04-09 10:46:50', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for uac_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `uac_user_login_log`;
CREATE TABLE `uac_user_login_log` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `login_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `system_id` varchar(100) DEFAULT NULL COMMENT '系统标识',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '登录IP地址',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录系统日志';

-- ----------------------------
-- Records of uac_user_login_log
-- ----------------------------
BEGIN;
INSERT INTO `uac_user_login_log` VALUES ('24a30acb59704d939fcae5cb88faa9f2', '14645c445f8342a59f8d30ec6559f05d', 'JFfBgY0YRObu0qPLYFJlGA==', 'PC', '0:0:0:0:0:0:0:1', '2018-04-09 20:35:18');
INSERT INTO `uac_user_login_log` VALUES ('e125e8c9c04c44119ff641a1b71bab80', '14645c445f8342a59f8d30ec6559f05d', 'JFfBgY0YRObu0qPLYFJlGA==', 'PC', '0:0:0:0:0:0:0:1', '2018-04-09 20:41:59');
COMMIT;

-- ----------------------------
-- Table structure for uac_user_register_log
-- ----------------------------
DROP TABLE IF EXISTS `uac_user_register_log`;
CREATE TABLE `uac_user_register_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `system_id` varchar(100) DEFAULT NULL COMMENT '系统标识',
  `register_ip` varchar(100) DEFAULT NULL COMMENT '注册IP地址',
  `register_time` varchar(100) DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户注册日志';

-- ----------------------------
-- Records of uac_user_register_log
-- ----------------------------
BEGIN;
INSERT INTO `uac_user_register_log` VALUES ('44107052f53f49f5bd22c12278558cf1', '1', 'PC', '0:0:0:0:0:0:0:1', '2018-04-09 10:46:50.434');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
