
## 模块说明

用户服务，提供验证码发送、用户注册、用户登录以及收获地址管理等能力。

├── congomall-customer-user
│   ├── congomall-customer-user-application  # 用户服务应用层
│   ├── congomall-customer-user-domain  # 用户服务领域层
│   ├── congomall-customer-user-infrastructure  # 用户服务基础层
│   ├── congomall-customer-user-interface  # 用户服务用户接口层
│   ├── congomall-customer-user-mock  # 用户服务数据模拟层

congomall-customer-user-interface 为可独立部署的应用。

接口文档：https://www.apifox.cn/apidoc/project-1038592/api-21806863

## 启动说明

### 项目依赖

启动前需启动 `congomall-message` 模块。

### 组件依赖

> 语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

Sentinel 1.8.x

- 部署文档：https://www.yuque.com/magestack/programmer_progress/kus9sy

Nacos 2.x

- 部署文档：https://www.yuque.com/magestack/programmer_progress/mcn5ey

Redis

- 部署文档：https://www.yuque.com/magestack/programmer_progress/cytzsu

RocketMQ 4.5.1

- 部署文档：https://www.yuque.com/magestack/programmer_progress/ox5gn3

### 配置项修改

验证码发送默认在配置文件中指定，用户可自定义。

- customer.user.register.verify.sender：验证码发送邮箱账户

## 核心功能

1）用户数据使用 ShardingSphere 框架 `HASH_MOD` 分片算法对用户 ID 进行分片。

2）用户相关敏感信息采用 ShardingSphere 框架 AES 加密算法对手机号、邮箱字段进行加密存储。

3）用户验证码发送使用策略模式、模板方法模式抽象。

- 代码地址：`org.opengoofy.congomall.biz.customer.user.web.controller.CustomerUserController.verifyCodeSend`

4）用户变更日志通过 RocketMQ 异步存储。

- 代码地址：`org.opengoofy.congomall.biz.customer.user.infrastructure.mq.produce.CustomerUserOperationLogProduce.recordCustomerUserOperationLog`
