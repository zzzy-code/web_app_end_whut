# 📦 web_app_end_whut 后端项目

## 📚 项目名称  
whut_ Web 应用开发课程实验项目 —— 后端系统

## 📖 项目简介  

本项目是基于 **SpringBoot 框架**开发的后端 Web 系统，配合前端 **Vue + ElementUI** 实现用户注册、登录、修改密码、用户信息列表展示等功能，采用**前后端分离架构设计**，实现前后端通过 RESTful API 接口数据交互。

系统包含：
- 登录、注册、找回密码功能
- 用户信息分页查询
- 后端数据存储、管理与调用
- 响应式布局支持，适配电脑端与移动端
- 前端 Vue + ElementUI 页面联动
- 完整的跨域配置、接口测试支持

前端项目仓库：[web_app_front_whut](https://github.com/zzzy-code/web_app_front_whut)
---

## 📂 项目结构  

```
web_app_end_whut/
├── src/main/java/com/whut/webappend
│   ├── controller        // 控制器层，处理前端请求
│   ├── pojo              // 实体类，映射数据库表
│   ├── service           // 业务逻辑层
│   ├── exception         // 异常处理层
│   ├── config            // 配置类（跨域、Swagger、拦截器）
│   └── WebAppEndApplication.java // SpringBoot 启动类
│
├── src/main/resources
│   ├── application.yml   // 项目配置文件
│   └── mybatis/          // MyBatis 配置与映射文件
│
└── pom.xml               // Maven 项目依赖管理
```

---

## 📦 环境依赖  

| 工具/框架         | 版本       |
|:----------------|:------------|
| JDK              | 17 (推荐 11+)|
| Maven            | 3.8+        |
| Spring Boot      | 3.x         |
| MySQL            | 8.x         |
| MyBatis          | 最新稳定版  |
| Postman          | 最新版      |
| Vue + ElementUI  | 3.x + 2.x/3.x|
| Git              | 最新版      |

---

## 🛠️ 启动与部署  

### 📌 克隆项目  

```bash
git clone https://github.com/zzzy-code/web_app_end_whut.git
```

### 📌 配置数据库  

在 `src/main/resources/application.yml` 中配置你的数据库信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database_name
    username: your_username
    password: your_password
```

### 📌 导入数据库表  

可在 `sql/` 目录下找到建表 SQL 文件，导入至 MySQL：
- `user` 表：存放注册登录用户信息
- `author` 表：存放普通用户信息（用于列表展示）

### 📌 启动项目  

在 IDEA 中直接运行 `WebAppEndApplication.java`，或命令行：

```bash
mvn spring-boot:run
```

---

✅ 至此，项目就可以正常运行、前后端联调了。

---
