DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `menu_id` INTEGER PRIMARY KEY AUTOINCREMENT,
                             `menu_name` varchar(50)  NOT NULL ,
                             `parent_id` INTEGER NULL DEFAULT 0,
                             `order_num` INTEGER NULL DEFAULT 0,
                             `path` varchar(200) NULL DEFAULT '',
                             `component` varchar(255) NULL DEFAULT NULL,
                             `query` varchar(255) NULL DEFAULT NULL,
                             `is_frame` INTEGER NULL DEFAULT 1,
                             `is_cache` INTEGER NULL DEFAULT 0,
                             `menu_type` varchar(1) NULL DEFAULT '',
                             `visible` varchar(1) NULL DEFAULT '0',
                             `status` varchar(1) NULL DEFAULT '0',
                             `perms` varchar(100) NULL DEFAULT NULL,
                             `icon` varchar(100) NULL DEFAULT '#',
                             `create_by` varchar(64) NULL DEFAULT '',
                             `create_time` varchar(255) DEFAULT (datetime('now', 'localtime')),
                             `update_by` varchar(64) NULL DEFAULT '',
                             `update_time` varchar(255) DEFAULT (datetime('now', 'localtime')),
                             `remark` varchar(500) NULL DEFAULT ''
);


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `role_id` INTEGER PRIMARY KEY AUTOINCREMENT,
                             `role_name` varchar(30) NOT NULL,
                             `role_key` varchar(100) NOT NULL,
                             `role_sort` INTEGER NOT NULL,
                             `data_scope` varchar(1) NULL DEFAULT '1',
                             `menu_check_strictly` INTEGER NULL DEFAULT 1,
                             `status` varchar(1) NULL DEFAULT '0',
                             `del_flag` varchar(1) NULL DEFAULT '0',
                             `create_by` varchar(64) NULL DEFAULT '',
                             `create_time` varchar(255) DEFAULT (datetime('now', 'localtime')),
                             `update_by` varchar(64) NULL DEFAULT '',
                             `update_time` varchar(255) DEFAULT (datetime('now', 'localtime')),
                             `remark` varchar(500) NULL DEFAULT NULL
);

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
                                  `role_id` INTEGER NOT NULL,
                                  `menu_id` INTEGER NOT NULL,
                                  PRIMARY KEY (`role_id`, `menu_id`)
);

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `user_id` INTEGER PRIMARY KEY AUTOINCREMENT,
                             `user_name` varchar(30) NOT NULL,
                             `nick_name` varchar(30) NOT NULL,
                             `user_type` INTEGER NULL DEFAULT 0,
                             `email` varchar(50) NULL DEFAULT '',
                             `phonenumber` varchar(11) NULL DEFAULT '',
                             `sex` varchar(1) NULL DEFAULT '0',
                             `avatar` varchar(255) NULL DEFAULT '',
                             `password` varchar(100) NULL DEFAULT '',
                             `status` varchar(1) NULL DEFAULT '0',
                             `del_flag` varchar(1) NULL DEFAULT '0',
                             `login_ip` varchar(128) NULL DEFAULT '',
                             `login_date` varchar(255) NULL DEFAULT NULL,
                             `create_by` varchar(64) NULL DEFAULT '',
                             `create_time` varchar(255) DEFAULT (datetime('now', 'localtime')),
                             `update_by` varchar(64) NULL DEFAULT '',
                             `update_time` varchar(255) DEFAULT (datetime('now', 'localtime')),
                             `remark` varchar(500) NULL DEFAULT NULL,
                             `real_name` varchar(30) NULL DEFAULT '',
                             `per_img` varchar(255) NULL DEFAULT ''
);

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` INTEGER NOT NULL,
                                  `role_id` INTEGER NOT NULL,
                                  PRIMARY KEY (`user_id`, `role_id`)
);