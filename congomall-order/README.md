
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

### 项目依赖

启动前需启动 `congomall-cart`、`congomall-product` 模块。

### 组件依赖

> 语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

Sentinel 1.8.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/kus9sy

Nacos 2.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/mcn5ey

RocketMQ 4.5.1

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/ox5gn3

Seata 1.5.2

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/trwpfp57tuusw51b

SkyWalking 9.3.0 可选

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/go4sgtgxgp33r27i
- 使用文档：https://www.yuque.com/magestack/knowledge-planet/qwle7f3hz4r5adcv

### 配置项修改

无

## 核心功能

1）商品下单引入分布式事务。商品服务 -> 购物车服务 -> 商品服务。

2）订单创建使用责任链模式验证参数必填、参数正确性、商品库存是否正确等逻辑。

- 代码地址：`org.opengoofy.congomall.biz.order.application.service.impl.OrderServiceImpl.createOrder`

3）订单创建逻辑使用观察者模式解耦合，基于 Spring ApplicationEvent 实现。

- 代码地址：`org.opengoofy.congomall.biz.order.application.service.impl.OrderServiceImpl.createOrder`

4）商品下单后，通过 RocketMQ 延迟消息关闭订单。

- 代码地址：`org.opengoofy.congomall.biz.order.infrastructure.mq.provide.DelayCloseOrderProvide`

