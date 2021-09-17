# SpringBootJwt
SpringBoot+Security+Jwt+MybatisPlus

![在这里插入图片描述](https://img-blog.csdnimg.cn/4b96098b9a31495bbd555a1f06ff9dd2.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA55m9546J5qKB,size_14,color_FFFFFF,t_70,g_se,x_16)

**数据库：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/525099376bf44ac89b8b0858fab59fce.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA55m9546J5qKB,size_13,color_FFFFFF,t_70,g_se,x_16)
![在这里插入图片描述](https://img-blog.csdnimg.cn/411c08ab953442f9adcdbe2d2a5b7ed1.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA55m9546J5qKB,size_20,color_FFFFFF,t_70,g_se,x_16)
![在这里插入图片描述](https://img-blog.csdnimg.cn/5ee6a3b6ad9e472d95c395cb0ad12130.png)

**Sql：**

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

**请求示例（测试JWT）：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/18cfce886f4e404ba2e0ad3be52dc9a8.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/8278c27ac7924a8f86eb21bfdea3e549.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/436b76ca0c4540fa9651b381127ca4b3.png)

**请求示例（测试权限）：**

![在这里插入图片描述](https://img-blog.csdnimg.cn/0ea0467de7934db080d93d6ab3eaecdf.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/72ec7d52c7e3492a97010bb32a071966.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/139e97effd3d47f2840382ddd2d009d8.png)

