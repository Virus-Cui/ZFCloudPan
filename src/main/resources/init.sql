/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : zfcloudpandb

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 12/07/2024 16:46:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_access_log
-- ----------------------------
DROP TABLE IF EXISTS `t_access_log`;
CREATE TABLE `t_access_log`  (
                                 `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求ID',
                                 `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求路径',
                                 `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
                                 `access_as` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求人',
                                 `access_from` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求IP',
                                 `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '结果',
                                 `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '参数',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '请求时间',
                                 `update_time` datetime NULL DEFAULT NULL,
                                 `deleted` tinyint(1) NULL DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                           `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
                           `menu_purview` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
                           `menu_router_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路由标识',
                           `menu_pid` int NULL DEFAULT NULL COMMENT '菜单父ID',
                           `menu_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单备注',
                           `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
                           `menu_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                           `outline` tinyint(1) NULL DEFAULT NULL COMMENT '是否为外链',
                           `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
                           `create_time` datetime NULL DEFAULT NULL COMMENT '菜单创建时间',
                           `update_time` datetime NULL DEFAULT NULL COMMENT '菜单修改时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '仪表板', 'sys:home', '/dashborad', 0, '首页', 'DashboardOutlined', 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:56');
INSERT INTO `t_menu` VALUES (2, '系统管理', 'sys:sys', '/', 0, '系统管理', 'SettingOutlined', 'FOLDER', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (3, '用户管理', 'sys:sys:user', '/sys/user', 2, '用户管理', NULL, 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (4, '添加用户', 'sys:sys:user:add', NULL, 3, '添加用户按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (5, '修改用户', 'sys:sys:user:update', NULL, 3, '修改用户按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (6, '删除用户', 'sys:sys:user:remove', NULL, 3, '删除用户按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (7, '角色管理', 'sys:sys:role', '/sys/role', 2, '角色管理', NULL, 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (8, '添加角色', 'sys:sys:role:add', NULL, 7, '添加角色按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (9, '修改角色', 'sys:sys:role:update', NULL, 7, '修改角色按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (10, '删除角色', 'sys:sys:role:remove', NULL, 7, '删除角色按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (11, '菜单管理', 'sys:sys:menu', '/sys/menu', 2, '菜单管理', NULL, 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (12, '添加角色', 'sys:sys:menu:add', NULL, 7, '添加角色按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (13, '修改角色', 'sys:sys:menu:update', NULL, 7, '修改角色按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (14, '删除角色', 'sys:sys:menu:remove', NULL, 7, '删除角色按钮', NULL, 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (23, '日志审计', 'sys:log', '/sys/log', 2, '日志页面', '', 'ITEM', 0, 0, NULL, NULL);
INSERT INTO `t_menu` VALUES (24, '运营管理', 'bank', '/bank', 0, '运营目录', 'BarChartOutlined', 'FOLDER', 0, 0, NULL, NULL);
INSERT INTO `t_menu` VALUES (27, '工单管理', 'bank:msg', '/bank/msg', 24, '工单管理页面', '', 'ITEM', 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                           `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
                           `menu_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '该角色拥有的权限列表',
                           `auth_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                           `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
                           `create_time` datetime NULL DEFAULT NULL COMMENT '角色创建时间',
                           `update_time` datetime NULL DEFAULT NULL COMMENT '角色修改时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1880809475 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (-233132031, '管理员', '[1,2,3,4,5,6,7,8,9,10,12,13,14,11,23,24,27]', '[1,2,3,4,5,6,7,8,9,10,12,13,14,11,23,24,27]', 0, NULL, NULL);
INSERT INTO `t_role` VALUES (-31805439, '游客', '[1]', '[1]', 0, NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
                           `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码 SHA256 加密',
                           `role` int NULL DEFAULT NULL COMMENT '角色ID',
                           `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
                           `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像URL',
                           `settings` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设置',
                           `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
                           `create_time` datetime NULL DEFAULT NULL COMMENT '用户创建时间',
                           `update_time` datetime NULL DEFAULT NULL COMMENT '用户修改时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
