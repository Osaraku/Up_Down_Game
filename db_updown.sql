/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : db_updown

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 26/06/2024 10:53:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tscore
-- ----------------------------
DROP TABLE IF EXISTS `tscore`;
CREATE TABLE `tscore`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `score` int NOT NULL,
  `up` int NOT NULL,
  `down` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tscore
-- ----------------------------
INSERT INTO `tscore` VALUES (1, 'Renko', 450, 8, 8);
INSERT INTO `tscore` VALUES (2, 'Andrew', 390, 9, 8);
INSERT INTO `tscore` VALUES (3, 'Jacob', 680, 9, 10);
INSERT INTO `tscore` VALUES (4, 'Aaron', 1030, 19, 17);
INSERT INTO `tscore` VALUES (5, 'John', 1250, 26, 19);
INSERT INTO `tscore` VALUES (6, 'Michael', 860, 13, 17);
INSERT INTO `tscore` VALUES (7, 'Raphael', 500, 10, 7);
INSERT INTO `tscore` VALUES (8, 'Vice', 1930, 31, 31);
INSERT INTO `tscore` VALUES (30, 'Anna', 380, 10, 6);
INSERT INTO `tscore` VALUES (31, 'Aldo', 1280, 19, 20);
INSERT INTO `tscore` VALUES (32, 'Sasky', 240, 2, 3);
INSERT INTO `tscore` VALUES (33, 'Rudy', 170, 2, 4);
INSERT INTO `tscore` VALUES (46, 'Mary', 960, 17, 16);
INSERT INTO `tscore` VALUES (47, 'Sat', 230, 3, 3);
INSERT INTO `tscore` VALUES (48, 'asdasd', 200, 2, 4);
INSERT INTO `tscore` VALUES (49, 'Sky', 490, 9, 8);

SET FOREIGN_KEY_CHECKS = 1;
