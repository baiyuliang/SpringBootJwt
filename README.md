# SpringBootJwt
SpringBoot整合Security+Jwt，前后端分离

![在这里插入图片描述](https://img-blog.csdnimg.cn/4b96098b9a31495bbd555a1f06ff9dd2.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA55m9546J5qKB,size_14,color_FFFFFF,t_70,g_se,x_16)
![在这里插入图片描述](https://img-blog.csdnimg.cn/525099376bf44ac89b8b0858fab59fce.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA55m9546J5qKB,size_13,color_FFFFFF,t_70,g_se,x_16)

```sql
create table jwttest.permissions
(
    id          int auto_increment
        primary key,
    path        varchar(255)             null,
    role_ids    varchar(255) default '1' null,
    description varchar(255)             null
);

create table jwttest.role
(
    id          int          not null,
    name        varchar(255) null,
    description varchar(255) null,
    constraint role_id_uindex
        unique (id)
);

alter table jwttest.role
    add primary key (id);

create table jwttest.user
(
    id       bigint        not null comment '主键ID'
        primary key,
    username varchar(255)  null,
    password varchar(255)  null,
    nickname varchar(30)   null comment '姓名',
    age      int           null comment '年龄',
    email    varchar(50)   null comment '邮箱',
    status   int default 1 null,
    role_id  int default 1 null
);


```
