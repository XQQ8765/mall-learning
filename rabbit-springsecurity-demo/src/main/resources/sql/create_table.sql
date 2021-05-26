create table sys_user
(
    id                      int auto_increment
        primary key,
    account                 varchar(32)          not null comment '账号',
    user_name               varchar(32)          not null comment '用户名',
    password                varchar(64)          null comment '用户密码',
    last_login_time         datetime             null comment '上一次登录时间',
    enabled                 tinyint(1) default 1 null comment '账号是否可用。默认为1（可用）',
    not_expired             tinyint(1) default 1 null comment '是否过期。默认为1（没有过期）',
    account_not_locked      tinyint(1) default 1 null comment '账号是否锁定。默认为1（没有锁定）',
    credentials_not_expired tinyint(1) default 1 null comment '证书（密码）是否过期。默认为1（没有过期）',
    create_time             datetime             null comment '创建时间',
    update_time             datetime             null comment '修改时间',
    create_user             int                  null comment '创建人',
    update_user             int                  null comment '修改人'
)
    comment '用户表';

create table sys_role
(
    id               int auto_increment comment '主键id'
        primary key,
    role_code       varchar(32) not null comment '角色code',
    role_name        varchar(32) null comment '角色名',
    role_description varchar(64) null comment '角色说明'
)
    comment '用户角色表';

create table sys_permission
(
    id              int auto_increment comment '主键id'
        primary key,
    permission_code varchar(32) null comment '权限code',
    permission_name varchar(32) null comment '权限名'
)
    comment '权限表';

create table sys_user_role_relation
(
    id      int auto_increment comment '主键id'
        primary key,
    user_id int null comment '用户id',
    role_id int null comment '角色id'
)
    comment '用户角色关联关系表';

create table sys_role_permission_relation
(
    id            int auto_increment comment '主键id'
        primary key,
    role_id       int null comment '角色id',
    permission_id int null comment '权限id'
)
    comment '角色-权限关联关系表';

create table sys_request_path
(
    id          int auto_increment comment '主键id'
        primary key,
    url         varchar(64)  not null comment '请求路径',
    description varchar(128) null comment '路径描述'
)
    comment '请求路径';

create table sys_request_path_permission_relation
(
    id            int null comment '主键id',
    url_id        int null comment '请求路径id',
    permission_id int null comment '权限id'
)
    comment '路径权限关联表';