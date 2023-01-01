
## 模块说明

订单服务，提供商品下单、订单查询、订单确认等能力。

├── congomall-order
│   ├── congomall-order-application  # 订单服务应用层
│   ├── congomall-order-domain  # 订单服务领域层
│   ├── congomall-order-infrastructure  # 订单服务基础层
│   ├── congomall-order-interface  # 订单服务用户接口层

congomall-order-interface 为可独立部署的应用。

接口文档：https://www.apifox.cn/apidoc/project-1038592/api-21806863

## 启动说明

### 组件依赖

> 语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

Sentinel 1.8.x

- 部署文档：https://www.yuque.com/magestack/programmer_progress/kus9sy

Nacos 2.x

- 部署文档：https://www.yuque.com/magestack/programmer_progress/mcn5ey

RocketMQ 4.5.1

- 部署文档：https://www.yuque.com/magestack/programmer_progress/ox5gn3

### 配置项修改

无

## 核心功能

1）商品下单引入分布式事务。商品服务 -> 购物车服务 -> 商品服务。

2）商品下单后，通过 RocketMQ 延迟消息关闭订单。

- 代码地址：`org.opengoofy.congomall.biz.order.infrastructure.mq.provide.DelayCloseOrderProvide`
