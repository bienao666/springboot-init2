
# springboot-init

## 前言

[Spring Security](https://spring.io/projects/spring-security ) 是 Spring 全家桶中非常强大的一个用来做身份验证以及权限控制的框架，我们可以轻松地扩展它来满足我们当前系统安全性这方面的需求。

## 介绍

**项目用到的一些框架/服务：**

- **数据库**： sqlite，无需手动安装。
- **权限框架** ：Spring Security

**你能从这个项目中学习到什么？**

1. Spring Security +JWT 实现登入登出以及权限校验

## 示例

### 1.注册一个账号

**URL:**

`POST http://localhost:8899/users/signup`

**RequestBody:**

```json
{
  "msg": "注册成功",
  "code": "0000"
}
```

### 2.登录

**URL:**

`POST http://localhost:8899/login`

**RequestBody:**

```json
{
  "msg": "success",
  "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0LVtST0xFX0FETUlOLCBBVVRIX1dSSVRFXSIsImlhdCI6MTY5OTExMzIyNywiZXhwIjoxNjk5MTEzNTI3fQ.qBkOnpFTc7VXtCB8g5-__W5YU1p6mI_eoKoZmwKMujsie2JZwVKE_bc7p6eTybqEa9TB9hk5k4kpdza9w2iXaw",
  "code": "200"
}
```

### 3.使用正确 Token 访问需要进行身份验证的资源

**URL:**

`POST http://localhost:8899/users/loadByUserName`

**RequestBody:**

```json
{
  "msg": "success",
  "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0LVtST0xFX0FETUlOLCBBVVRIX1dSSVRFXSIsImlhdCI6MTY5OTExMzIyNywiZXhwIjoxNjk5MTEzNTI3fQ.qBkOnpFTc7VXtCB8g5-__W5YU1p6mI_eoKoZmwKMujsie2JZwVKE_bc7p6eTybqEa9TB9hk5k4kpdza9w2iXaw",
  "code": "200"
}
```
### 4.不带 Token 或者使用无效 Token 访问

**URL:**

`POST http://localhost:8899/users/loadByUserName`

**RequestBody:**

```json
{
  "msg": "Token过期",
  "code": 1000
}
```

