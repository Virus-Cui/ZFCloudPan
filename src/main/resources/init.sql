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

 Date: 25/07/2024 22:17:21
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
-- Records of t_access_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_file_info
-- ----------------------------
DROP TABLE IF EXISTS `t_file_info`;
CREATE TABLE `t_file_info`  (
  `file_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小 单位 byte',
  `file_abs_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件位置',
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件MD5值',
  `file_owner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件所属',
  `file_pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件父ID',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '文件是否放入回收站',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传状态',
  `chunk_num` int NULL DEFAULT NULL COMMENT '分片数',
  `chunk_index` int NULL DEFAULT NULL COMMENT '断点续传分片id',
  `file_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频文件缩略图',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file_info
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '仪表板', 'sys:home', '/dashboard', 0, '首页', 'DashboardOutlined', 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:56');
INSERT INTO `t_menu` VALUES (2, '系统管理', 'sys:sys', '/sys', 0, '系统管理', 'SettingOutlined', 'FOLDER', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (3, '用户管理', 'sys:sys:user', '/sys/user', 2, '用户管理', '', 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (4, '添加用户', 'sys:user:add', '', 3, '添加用户按钮', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (5, '修改用户', 'sys:user:update', '', 3, '修改用户按钮', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (6, '删除用户', 'sys:user:remove', '', 3, '删除用户按钮', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (7, '角色管理', 'sys:sys:role', '/sys/role', 2, '角色管理', '', 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (8, '添加角色', 'sys:role:new', '', 7, '添加角色按钮', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (9, '修改角色', 'sys:role:update', '', 7, '修改角色按钮', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (10, '删除角色', 'sys:role:remove', '', 7, '删除角色按钮', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (11, '菜单管理', 'sys:menu', '/sys/menu', 2, '菜单管理', '', 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (23, '日志审计', 'sys:log', '/sys/log', 2, '日志页面', '', 'ITEM', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (28, '查询菜单', 'sys:menu:select', '', 11, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (29, '新建菜单', 'sys:menu:new', '', 11, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (30, '删除菜单', 'sys:menu:remove', '', 11, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (31, '修改菜单', 'sys:menu:update', '', 11, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (32, '查询角色', 'sys:role:select', '', 7, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (33, '查询用户', 'sys:user:select', '', 3, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (39, '查询日志', 'sys:log:select', '', 23, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (43, '清空日志', 'sys:log:remove', '', 23, '', '', 'ACTIVE', 0, 0, '2024-07-04 13:11:54', '2024-07-04 13:11:54');
INSERT INTO `t_menu` VALUES (48, '系统设置', 'sys:settings', '/sys/settings', 2, '', NULL, 'ITEM', 0, 0, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1880809478 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', '[1,4,5,6,33,8,9,10,32,28,29,30,31,39,43,3,7,11,23,48,2]', '[1,4,5,6,33,8,9,10,32,28,29,30,31,39,43,48]', 0, '2024-07-13 14:17:16', '2024-07-13 14:17:18');

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting`  (
  `id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `login_bg_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `logo_small` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `logo_text_black` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `logo_text_white` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_setting
-- ----------------------------
INSERT INTO `t_setting` VALUES (0, '致飞网盘', 'http://127.0.0.1:9000/develop/dde856924e7a44f08efb54f69374423a.jpg', 'http://127.0.0.1:9000/develop/97d1fbb6245e4170a87f264a42c0d6f7.png', 'http://127.0.0.1:9000/develop/b54ef44bfdff439abaf0c0965b22b169.png', 'http://127.0.0.1:9000/develop/470739f910e3435999aa73179f689389.png', 'http://127.0.0.1:9000/develop/6b16c4a2cddf4adf83debabe76d8b6b7.png');

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

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1811753528800489472', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, 'example@example.com', '', '{}', 0, '2024-07-12 21:24:37', '2024-07-12 21:24:37');
INSERT INTO `t_user` VALUES ('1816302914903920640', 'Virus_Cui', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, 'null', NULL, '{}', 0, '2024-07-25 10:42:15', '2024-07-25 10:42:15');

SET FOREIGN_KEY_CHECKS = 1;
