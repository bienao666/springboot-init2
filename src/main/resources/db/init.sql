-- sys_menu
INSERT INTO
    "sys_menu"("menu_id", "menu_name", "parent_id", "order_num", "path", "component", "query", "is_frame", "is_cache", "menu_type", "visible", "status", "perms", "icon", "create_by", "update_by", "remark")
VALUES
    (1, '首页', 0, 1, '/firstIndex', '', 'firstIndex', 1, 0, 'C', '0', '0', '', '#', 'admin', 'admin', '首页'),
    (2, '系统用户', 0, 2, '/member', '', 'member', 1, 0, 'C', '0', '0', '', '#', 'admin', 'admin', '系统用户'),
    (3, '系统菜单', 0, 3, '/menu', '', 'menu', 1, 0, 'C', '0', '0', NULL, '#', 'admin', 'admin', '系统菜单'),
    (4, '系统角色', 0, 4, '/role', '', 'role', 1, 0, 'C', '0', '0', NULL, '#', 'admin', 'admin', '系统角色'),
    (5, '服务监控', 0, 5, '/monitor', '', 'monitor', 1, 0, 'C', '0', '0', '', '#', 'admin', 'admin', '服务监控'),
    (6, '系统参数', 0, 6, '/system', '', 'system', 1, 0, 'C', '0', '0', '', '#', 'admin', 'admin', '系统参数'),
    (7, '用户中心', 0, 7, '/user', '', 'user', 1, 0, 'C', '0', '0', NULL, '#', 'admin', 'admin', '用户中心'),

    (201, '系统用户成员列表', 2, 1, '/member/list', NULL, 'list', 1, 0, 'F', '0', '0', 'member:list', '#', 'admin', 'admin', '系统用户成员列表'),
    (202, '系统用户详细信息', 2, 2, '/member/query', NULL, 'query', 1, 0, 'F', '0', '0', 'member:query', '#', 'admin', 'admin', '系统用户详细信息'),
    (203, '系统用户新增', 2, 3, '/member/add', NULL, 'add', 1, 0, 'F', '0', '0', '', '#', 'admin', 'admin', '系统用户新增'),
    (204, '系统用户修改', 2, 4, '/member/update', NULL, 'update', 1, 0, 'F', '0', '0', 'member:update', '#', 'admin', 'admin', '系统用户修改'),
    (205, '系统用户注销账号', 2, 5, '/member/cancelled', NULL, 'cancelled', 1, 0, 'F', '0', '0', 'member:cancelled', '#', 'admin', 'admin', '系统用户注销账号'),
    (206, '系统用户激活账号', 2, 6, '/member/active', NULL, 'active', 1, 0, 'F', '0', '0', 'member:active', '#', 'admin', 'admin', '系统用户激活账号'),
    (207, '系统用户重置密码', 2, 7, '/member/resetpwd', NULL, 'resetpwd', 1, 0, 'F', '0', '0', 'member:resetpwd', '#', 'admin', 'admin', '系统用户重置密码'),
    (208, '系统用户删除账号', 2, 8, '/member/remove', NULL, 'remove', 1, 0, 'F', '0', '0', 'member:remove', '#', 'admin', 'admin', '系统用户删除账号'),
    (209, '系统用户查询登陆用户的所有角色', 2, 9, '/member/profile', NULL, 'profile', 1, 0, 'F', '0', '0', 'member:profile', '#', 'admin', 'admin', '系统用户查询登陆用户的所有角色'),
    (210, '系统用户更新用户信息', 2, 10, '/member/updateProfile', NULL, 'updateProfile', 1, 0, 'F', '0', '0', 'member:updateProfile', '#', 'admin', 'admin', '系统用户更新用户信息'),
    (211, '系统用户修改密码', 2, 11, '/member/updatePwd', NULL, 'updatePwd', 1, 0, 'F', '0', '0', 'member:updatePwd', '#', 'admin', 'admin', '系统用户修改密码'),

    (301, '系统菜单获取菜单列表', 3, 1, '/menu/list', NULL, 'list', 1, 0, 'F', '0', '0', 'menu:list', '#', 'admin', 'admin', '系统菜单获取菜单列表'),
    (302, '系统菜单获取菜单下拉树列表', 3, 2, '/menu/treeselect', NULL, 'treeselect', 1, 0, 'F', '0', '0', 'menu:treeselect', '#', 'admin', 'admin', '系统菜单获取菜单下拉树列表'),
    (303, '系统菜单加载对应角色菜单列表树', 3, 3, '/menu/roleMenuTree', NULL, 'roleMenuTree', 1, 0, 'F', '0', '0', 'menu:roleMenuTree', '#', 'admin', 'admin', '系统菜单加载对应角色菜单列表树'),
    (304, '系统菜单新增菜单', 3, 4, '/menu/add', NULL, 'add', 1, 0, 'F', '0', '0', 'menu:add', '#', 'admin', 'admin', '系统菜单系统菜单新增菜单'),
    (305, '系统菜单根据菜单编号获取详细信息', 3, 5, '/menu/query', NULL, 'query', 1, 0, 'F', '0', '0', 'menu:query', '#', 'admin', 'admin', '系统菜单根据菜单编号获取详细信息'),
    (306, '系统菜单修改菜单', 3, 6, '/menu/edit', NULL, 'edit', 1, 0, 'F', '0', '0', 'menu:edit', '#', 'admin', 'admin', '系统菜单修改菜单'),
    (307, '系统菜单删除菜单', 3, 7, '/menu/remove', NULL, 'remove', 1, 0, 'F', '0', '0', 'menu:remove', '#', 'admin', 'admin', '系统菜单删除菜单'),

    (401, '系统角色分页查询角色数据', 4, 1, '/role/list', NULL, 'list', 1, 0, 'F', '0', '0', 'role:list', '#', 'admin', 'admin', '系统角色分页查询角色数据'),
    (402, '系统角色根据角色编号获取详细信息', 4, 3, '/role/query', NULL, 'query', 1, 0, 'F', '0', '0', 'role:query', '#', 'admin', 'admin', '系统角色根据角色编号获取详细信息'),
    (403, '系统角色新增角色', 4, 4, '/role/add', NULL, 'add', 1, 0, 'F', '0', '0', 'role:add', '#', 'admin', 'admin', '系统角色新增角色'),
    (404, '系统角色修改角色', 4, 5, '/role/edit', NULL, 'edit', 1, 0, 'F', '0', '0', 'role:edit', '#', 'admin', 'admin', '系统角色修改角色'),
    (405, '系统角色删除角色', 4, 6, '/role/remove', NULL, 'remove', 1, 0, 'F', '0', '0', 'role:remove', '#', 'admin', 'admin', '系统角色删除角色'),
    (406, '系统角色查询已分配用户角色列表', 4, 7, '/role/allocatedList', NULL, 'allocatedList', 1, 0, 'F', '0', '0', 'role:allocatedList', '#', 'admin', 'admin', '系统角色查询已分配用户角色列表'),
    (407, '系统角色查询未分配用户角色列表', 4, 8, '/role/unallocatedList', NULL, 'unallocatedList', 1, 0, 'F', '0', '0', 'role:unallocatedList', '#', 'admin', 'admin', '系统角色查询未分配用户角色列表'),
    (408, '系统角色取消授权用户', 4, 9, '/role/cancel', NULL, 'cancel', 1, 0, 'F', '0', '0', 'role:cancel', '#', 'admin', 'admin', '系统角色取消授权用户'),
    (409, '系统角色批量选择用户授权', 4, 10, '/role/selectAll', NULL, 'selectAll', 1, 0, 'F', '0', '0', 'role:selectAll', '#', 'admin', 'admin', '系统角色批量选择用户授权'),
    (410, '系统角色批量取消授权用户', 4, 11, '/role/cancelAll', NULL, 'cancelAll', 1, 0, 'F', '0', '0', 'role:cancelAll', '#', 'admin', 'admin', '系统角色批量取消授权用户'),

    (501, '获取所有监控', 5, 1, '/monitor/getAllInfo', '', 'getAllInfo', 1, 0, 'F', '0', '0', 'monitor:getAllInfo', '#', 'admin', 'admin', '获取所有监控'),

    (601, '系统参数新增', 6, 1, '/system/insert', NULL, 'insert', 1, 0, 'F', '0', '0', 'system:insert', '#', 'admin', 'admin', '系统参数新增'),
    (602, '系统参数刪除', 6, 2, '/system/delete', NULL, 'delete', 1, 0, 'F', '0', '0', 'system:delete', '#', 'admin', 'admin', '系统参数刪除'),
    (603, '系统参数更新', 6, 3, '/system/update', NULL, 'update', 1, 0, 'F', '0', '0', 'system:update', '#', 'admin', 'admin', '系统参数更新'),
    (604, '系统参数查询详情', 6, 4, '/system/load', NULL, 'load', 1, 0, 'F', '0', '0', 'system:load', '#', 'admin', 'admin', '系统参数查询详情'),
    (605, '系统参数分页查询', 6, 5, '/system/pageList', NULL, 'pageList', 1, 0, 'F', '0', '0', 'system:pageList', '#', 'admin', 'admin', '系统参数分页查询'),

    (1000, '自增开始', 0, 3, '', NULL, '', 1, 0, '', '1', '0', '', '#', 'admin', 'admin', '自增开始');

-- sys_role
INSERT INTO
    "sys_role"
    ("role_id", "role_name", "role_key", "role_sort", "data_scope", "menu_check_strictly", "status", "del_flag", "create_by", "update_by", "remark")
VALUES
    (1, '管理员', 'admin', 1, '1', 1, '0', '0', 'admin', 'admin', '管理员'),
    (2, '普通成员', 'user', 2, '2', 1, '0', '0', 'admin', 'admin', '普通成员');

-- sys_user
INSERT INTO
    "sys_user"
    ("user_id", "user_name", "nick_name", "user_type", "email", "phonenumber", "sex", "avatar", "password", "status", "del_flag", "login_ip", "login_date", "create_by", "update_by", "remark", "real_name", "per_img")
VALUES
    (1, 'admin', 'admin', 1, '', '', '0', '', '$2a$10$i8QBTH0Xaj.PRKUIz7D0u.nHMcpl3NLfjfjWkQgwYiBZkL61CoD9S', '0', '0', '', NULL, 'admin', 'admin', NULL, '', ''),
    (2, 'user', 'user', 0, '', '', '0', '', '$2a$10$c54Li/xoEeDc85FqRM0FFOqtmy1QPuqVXDt7JU0Jr.TCxqZ4qaQ92', '0', '0', '', NULL, 'admin', 'admin', NULL, '', '');

-- sys_user_role
INSERT INTO
    "sys_user_role"
("user_id", "role_id")
VALUES
    (1, 1),
    (2, 2);

-- sys_role_menu
INSERT INTO
    "sys_role_menu"
    ("role_id", "menu_id")
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 201),
    (1, 202),
    (1, 203),
    (1, 204),
    (1, 205),
    (1, 206),
    (1, 207),
    (1, 208),
    (1, 209),
    (1, 210),
    (1, 211),
    (1, 301),
    (1, 302),
    (1, 303),
    (1, 304),
    (1, 305),
    (1, 306),
    (1, 307),
    (1, 401),
    (1, 402),
    (1, 403),
    (1, 404),
    (1, 405),
    (1, 406),
    (1, 407),
    (1, 408),
    (1, 409),
    (1, 410),
    (1, 501),
    (1, 601),
    (1, 602),
    (1, 603),
    (1, 604),
    (1, 605),
    (2, 1),
    (2, 7),
    (2, 202),
    (2, 204),
    (2, 210),
    (2, 211),
    (2, 303);
