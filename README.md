
<div align="center">
   <img alt="logo" width="100" height="100" src="https://foruda.gitee.com/images/1733417239320800627/3c5290fe_716974.png">
   <h2>youlai-boot</h2>
   <img alt="有来技术" src="https://img.shields.io/badge/Java -17-brightgreen.svg"/>
   <img alt="有来技术" src="https://img.shields.io/badge/SpringBoot-3.3.6-green.svg"/>
   <a href="https://gitee.com/youlaiorg/youlai-boot" target="_blank">
     <img alt="有来技术" src="https://gitee.com/youlaiorg/youlai-boot/badge/star.svg"/>
   </a>     
   <a href="https://github.com/haoxianrui" target="_blank">
     <img alt="有来技术" src="https://img.shields.io/github/stars/haoxianrui/youlai-boot.svg?style=social&label=Stars"/>
   </a>
   <br/>
   <img alt="有来技术" src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg"/>
   <a href="https://gitee.com/youlaiorg" target="_blank">
     <img alt="有来技术" src="https://img.shields.io/badge/Author-有来开源组织-orange.svg"/>
   </a>
</div>

![](https://raw.gitmirror.com/youlaitech/image/main/docs/rainbow.png)

<div align="center">
  <a target="_blank" href="https://vue.youlai.tech/">🖥️ 在线预览</a> |  <a target="_blank" href="https://youlai.blog.csdn.net/article/details/145178880">📑 阅读文档</a> |  <a target="_blank" href="https://www.youlai.tech/">🌐 官网</a>
</div>

## 📢 项目简介

基于 JDK 17、Spring Boot 3、Spring Security 6、JWT、Redis、Mybatis-Plus、Knife4j、Vue 3、Element-Plus 构建的前后端分离单体权限管理系统。

- **🚀 开发框架**: 使用 Spring Boot 3 和 Vue 3，以及 Element-Plus 等主流技术栈，实时更新。

- **🔐 安全认证**: 基于 Spring Security 6 原生架构，集成 JWT 令牌自动续期（无状态）和 Redis 会话多端互斥管理（实时强制离线）双重认证机制，构建企业级身份安全中枢。

- **🔑 权限管理**: 基于 RBAC 模型，实现细粒度的权限控制，涵盖接口方法和按钮级别。

- **🛠️ 功能模块**: 包括用户管理、角色管理、菜单管理、部门管理、字典管理等多个功能。


## 🌈 项目资源

- **🌍 在线演示**: [https://vue.youlai.tech](https://vue.youlai.tech)
- **📦 源码集合**  
  `后端`・[youlai-boot](https://gitee.com/youlaiorg/youlai-boot)  
  `前端`・[vue3-element-admin](https://gitee.com/youlaiorg/vue3-element-admin)  
  `移动端`・[vue-uniapp-template](https://gitee.com/youlaiorg/vue-uniapp-template)
- **📄 接口文档**  
  [Apifox 在线文档](https://www.apifox.cn/apidoc/shared-195e783f-4d85-4235-a038-eec696de4ea5)
- **📚 系统文档**  
  [全功能手册](https://youlai.blog.csdn.net/article/details/145178880)  
  [从0到1搭建指南](https://youlai.blog.csdn.net/article/details/145177011)

## 🚀 项目启动

1. **克隆项目**

   ```bash
   git clone https://gitee.com/youlaiorg/youlai-boot.git
   ```

2. **数据库初始化**

   执行 [youlai_boot.sql](sql/mysql/youlai_boot.sql) 脚本完成数据库创建、表结构和基础数据的初始化。

3. **修改配置**

   [application-dev.yml](src/main/resources/application-dev.yml) 修改MySQL、Redis连接配置；

4. **启动项目**

   执行 [YoulaiBootApplication.java](src/main/java/com/youlai/boot/YoulaiBootApplication.java) 的 main 方法完成后端项目启动；

   访问接口文档地址 [http://localhost:8989/doc.html](http://localhost:8989/doc.html) 验证项目启动是否成功。


## 📁 项目目录
```
youlai-boot
├── docker                              # Docker 目录
│   ├── docker-compose.yml              # docker-compose 脚本
├── sql                                 # SQL脚本
│   ├── mysql                           # MySQL 脚本
├── src                                 # 源码目录
│   ├── common                          # 公共模块
│   │   ├── annotation                  # 注解定义
│   │   ├── base                        # 基础类
│   │   ├── constant                    # 常量
│   │   ├── enums                       # 枚举类型
│   │   ├── exception                   # 异常处理
│   │   ├── model                       # 数据模型
│   │   ├── result                      # 结果封装
│   │   └── util                        # 工具类
│   ├── config                          # 自动装配配置
│   │   └── property                    # 配置属性目录
│   ├── core                            # 核心功能
│   │   ├── aspect                      # 切面(日志、防重提交)
│   │   ├── filter                      # 过滤器(请求日志、限流)
│   │   ├── handler                     # 处理器(数据权限、数据填充)
│   │   └── security                    # Spring Security 安全模块
│   ├── modules                         # 业务模块
│   │   ├── member                      # 会员模块【业务模块演示】
│   │   ├── order                       # 订单模块【业务模块演示】
│   │   ├── product                     # 商品模块【业务模块演示】
│   ├── shared                          # 共享模块
│   │   ├── auth                        # 认证模块
│   │   ├── file                        # 文件模块
│   │   ├── codegen                     # 代码生成模块
│   │   ├── mail                        # 邮件模块
│   │   ├── sms                         # 短信模块
│   │   └── websocket                   # WebSocket 模块
│   ├── system                          # 系统模块
│   │   ├── controller                  # 控制层
│   │   ├── converter                   # MapStruct 转换器
│   │   ├── event                       # 事件处理
│   │   ├── handler                     # 处理器
│   │   ├── listener                    # 监听器
│   │   ├── model                       # 模型层
│   │   │   ├── bo                      # 业务对象
│   │   │   ├── dto                     # 数据传输对象
│   │   │   ├── entity                  # 实体对象
│   │   │   ├── form                    # 表单对象
│   │   │   ├── query                   # 查询参数对象
│   │   │   └── vo                      # 视图对象
│   │   ├── mapper                      # 数据库访问层
│   │   └── service                     # 业务逻辑层
│   └── YouLaiBootApplication           # 启动类
└── end                             
```

## ✅ 项目统计

![Alt](https://repobeats.axiom.co/api/embed/544c5c0b5b3611a6c4d5ef0faa243a9066b89659.svg "Repobeats analytics image")

Thanks to all the contributors!

[![contributors](https://contrib.rocks/image?repo=haoxianrui/youlai-boot)](https://github.com/haoxianrui/youlai-boot/graphs/contributors)


## 💖 加交流群

> **关注「有来技术」公众号，点击菜单“交流群”获取加群二维码。**
>
> 如果二维码过期，请加微信(haoxianrui)备注「前端」、「后端」或「全栈」拉你进群。
>
> 交流群仅限技术交流，为过滤广告营销暂设此门槛，感谢理解与配合

![有来技术公众号二维码](https://foruda.gitee.com/images/1737108820762592766/3390ed0d_716974.png)

