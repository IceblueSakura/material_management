# 物料管理系统

----
## 项目简介
### 概述
物料管理平台是一款专注于优化和简化企业物料流程的软件解决方案。该平台致力于提升物料管理的效率，确保企业能够有效地追踪、采购、入库、出库和消耗各类物料。

项目采用了Spring Boot 和 Spring Cloud 框架，主要使用 Kotlin 语言以协程与 WebFlux 相结合的方式进行微服务式系统的开发。这一技术栈的选择旨在充分发挥 Spring 框架的优势，同时结合 Kotlin 语言的简洁性和协程的异步处理特性，以及 WebFlux 的响应式编程风格，从而提高系统的性能和响应能力。

它以单点登录(SSO,Single sign-on)和基于角色的访问控制(RBAC,Role-base Access Control)的方式实现用户的权限划分与管理，相比较传统的权限管理模式，更加灵活、安全。通过集成单点登录(SSO)机制，用户能够以便捷的方式访问多个微服务，而基于角色的访问控制(RBAC)则确保了对系统资源的精准控制。相较传统权限管理，这种设计模式不仅提高了系统的整体安全性，还为用户提供了更加个性化和高效的权限管理体验。

### 开发环境
- JetBrains IDEA
- Docker Desktop on Windows
- Kubernetes(Docker) or MiniKube(Debian) 

## 安装与配置
### 依赖项
- Gradle 8.5
- Kotlin 1.9.20(Java 21)
- Spring Boot 3.2
- Spring Cloud 2023.0.0
- Kubernetes 1.28.2
- Istio 1.20

### 安装说明
1. 克隆仓库
   ```bash
       git clone https://github.com/icebluesakura/material-managment.git
   ```
2. 进入项目目录
   ```bash
       cd material-managment
   ```
3. 

### 使用框架组件及介绍

1. Spring Boot

2. Spring Cloud

3. Spring Data R2DBC

4. Spring Cloud

### 部分概念介绍
1. 微服务

2. 非阻塞

3. protobuf


## 程序结构
### 组件功能划分：
1. 网关服务：gateway
    - 提供用户登录的跳转、验证。
    - 提供权限判断、路由到各个组件功能。
2. 用户管理：user
   - 处理用户注册、登录、权限管理等功能。
   - 管理用户信息，如个人资料、认证信息等。
3. 物料管理：material
   - 管理产品和物料信息。
   - 处理物料的采购、入库、出库等操作。
4. 库存服务：inventory
   - 管理物料库存，处理库存变动。
   - 与订单服务和物料管理服务集成，确保库存的准确性。
5. 日志和监控服务：log
   - 处理系统日志记录和性能监控。
   - 提供集中式日志存储和监控功能。
6. 认证与授权服务：authorization
   - 管理用户身份认证和访问授权。
   - 与其他服务集成，确保安全性。
7. 订单服务：order
   - 处理订单创建、支付、取消等订单管理功能。
   - 包括订单状态跟踪和库存更新。


**以下为不确定是否扩展组件**
8. 通知服务：message
   - 处理系统通知、邮件通知等。
   - 与其他服务集成，触发通知事件
9. 搜索服务：search
    - 处理全文搜索、过滤和排序等功能。
    - 与产品、订单等服务集成，支持快速检索。

### 项目内目录介绍(以 组件为例)

### 数据库结构
以下为数据库中**主要**Table(没写关系表等)，序号与微服务对应，重复的Table只描述一次(一个表可能被多个项目使用)
1. user
2. role
3. permission


## 项目说明


# 一些杂记
记得配置k8s yml时配置service account,权限[get watch list]+[pods services]
使用istio配置负载均衡，而不是k8s/gateway
