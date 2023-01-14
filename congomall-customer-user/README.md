
## 模块说明

用户服务，提供验证码发送、用户注册、用户登录以及收获地址管理等能力。

├── congomall-customer-user
│   ├── congomall-customer-user-application  # 用户服务应用层
│   ├── congomall-customer-user-domain  # 用户服务领域层
│   ├── congomall-customer-user-infrastructure  # 用户服务基础层
│   ├── congomall-customer-user-interface  # 用户服务用户接口层
│   ├── congomall-customer-user-mock  # 用户服务数据模拟层

congomall-customer-user-interface 为可独立部署的应用。

接口文档：https://www.yuque.com/magestack/knowledge-planet/kgvgcgc749grt928

## 启动说明

### 项目依赖

启动前需启动 `congomall-message` 模块。

### 组件依赖

> 语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

Sentinel 1.8.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/kus9sy

Nacos 2.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/mcn5ey

Redis

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/cytzsu

RocketMQ 4.5.1

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/ox5gn3

SkyWalking 9.3.0 可选

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/go4sgtgxgp33r27i
- 使用文档：https://www.yuque.com/magestack/knowledge-planet/qwle7f3hz4r5adcv

### 配置项修改

验证码发送默认在配置文件中指定，用户可自定义。

- customer.user.register.verify.sender：验证码发送邮箱账户
