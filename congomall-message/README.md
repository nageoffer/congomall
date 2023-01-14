
## 模块说明

消息服务，提供邮箱消息发送、短信消息发送、微信公众号以及微信订阅号发送等能力。

├── congomall-message
│   ├── congomall-message-application  # 消息服务应用层
│   ├── congomall-message-domain  # 消息服务领域层
│   ├── congomall-message-infrastructure  # 消息服务基础层
│   ├── congomall-message-interface  # 消息服务用户接口层

congomall-message-interface 为可独立部署的应用。

接口文档：https://www.yuque.com/magestack/knowledge-planet/kgvgcgc749grt928

## 启动说明

### 组件依赖

> 语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

Sentinel 1.8.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/kus9sy

Nacos 2.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/mcn5ey

RocketMQ 4.5.1

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/ox5gn3

SkyWalking 9.3.0 可选

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/go4sgtgxgp33r27i
- 使用文档：https://www.yuque.com/magestack/knowledge-planet/qwle7f3hz4r5adcv

### 配置项修改

邮箱账号发送默认在配置文件中指定，用户可自定义。

- spring.mail.username：发送邮箱账户
