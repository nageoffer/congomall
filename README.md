
刚果商城是个从零到一的项目，包含商城业务和基础架构两个大模块，预计 2023.03 月份完成基础业务功能。

## 如何学习

语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

https://www.yuque.com/magestack/knowledge-planet/kgvgcgc749grt928

## 功能清单

https://www.yuque.com/magestack/public/knowledge-planet

## 模块分类

| | 模块名称 | 服务名称 | 访问地址 |
| -- | --- | --- | --- |
| 1 | congomall-message | 消息发送 eg：邮件、公众号、短信等 | http://localhost:8001 |
| 2 | congomall-customer-user | C 端用户 | http://localhost:8002 |
| 3 | congomall-gateway | 外部网关 | http://localhost:8003 |
| 4 | congomall-product | 商品服务 | http://localhost:8004 |
| 5 | congomall-product-job | 商品 Job 服务 | http://localhost:9001 |
| 6 | congomall-cart | 购物车服务 | http://localhost:8005 |
| 7 | congomall-order | 订单服务 | http://localhost:8006 |
| 8 | congomall-pay | 支付服务 | http://localhost:8007 |

## 文档目录

注：文章里 TODO 的是还没写（耐心等待更新吧）

- 入门准备

    - [加入交流群](https://magestack.cn/preparation/group.html)

    - [初始化数据库](https://magestack.cn/preparation/init-datasource.html)

    - 环境搭建

        - [MySQL 5.7.x 简易安装部署](https://magestack.cn/preparation/environment/mysql_5.7_install.html)

        - [Redis 简易安装部署](https://magestack.cn/preparation/environment/redis_latest_install.html)
        - [RocketMQ 4.5.1 安装部署](https://magestack.cn/preparation/environment/rocketmq_4.5.1_install.html)
        - [Nacos 2.1.1 安装部署](https://magestack.cn/preparation/environment/nacos_2.1.1_install.html)
        - [Sentinel 1.8.4 安装部署](https://magestack.cn/preparation/environment/sentinel_1.8.4_install.html)
        - [MinIO 安装部署](https://magestack.cn/preparation/environment/minio_latest_install.html)
        - [Prometheus 安装部署](https://magestack.cn/preparation/environment/prometheus_latest_install.html)
        - [Grafana 安装部署](https://magestack.cn/preparation/environment/grafana_latest_install.html)
        - [Seata 1.5.2 安装部署](https://magestack.cn/preparation/environment/seata_1.5.2_install.html)
        - [Canal 1.1.5 安装部署](https://magestack.cn/preparation/environment/canal_1.1.5_install.html)
        - [SkyWalking 9.3.0 安装部署](https://magestack.cn/preparation/environment/sky-walking_9.3.0_install.html)
        - [XXL-Job 2.3.1 安装部署](https://magestack.cn/preparation/environment/xxl-job_2.3.1_install.html)

    - 项目说明

        - [接口文档说明](https://magestack.cn/preparation/explain/interface-docs.html)

        - [技术架构选型](https://magestack.cn/preparation/explain/interface-docs.html)
        - [项目结构说明](https://magestack.cn/preparation/explain/item-structured.html)
        - [商品 SPU、SKU 之间的区别](https://magestack.cn/preparation/explain/product-sku-spu.html)

- 快速开始

    - [用户服务](https://magestack.cn/fast-start/user.html)

    - [购物车服务](https://magestack.cn/fast-start/cart.html)
    - [商品服务](https://magestack.cn/fast-start/product.html)
    - [消息服务](https://magestack.cn/fast-start/message.html)
    - [订单服务](https://magestack.cn/fast-start/order.html)
    - [支付服务](https://magestack.cn/fast-start/pay.html)

- 系统设计

    - [数据库设计](https://magestack.cn/system-design/database.html)

    - 通用系统设计

        - [SpringBoot 封装 Web 请求通用返回](https://magestack.cn/system-design/common/result.html)

        - [SpringBoot 封装项目统一异常处理](https://magestack.cn/system-design/common/exception.html)

    - 用户系统设计

        - [电商平台亿级用户如何分库分表](https://magestack.cn/system-design/user/sharding.html)

    - 商品系统设计

        - [如何解决商品秒杀库存超卖问题](https://magestack.cn/system-design/product/inventory-oversold.html)

        - [亿级商品数据如何快速同步三方数据库](https://magestack.cn/system-design/product/sync-other-database.html)

    - 订单系统设计

        - [订单&明细表数据如何进行分库分表](https://magestack.cn/system-design/order/sharding.html)

        - [如何实现订单 15 分钟未支付自动取消](https://magestack.cn/system-design/order/timing-canal.html)
        - [服务端如何解决用户下单重复](https://magestack.cn/system-design/order/user-order-repetition.html)
        - [如何保证消息队列不被重复消费](https://magestack.cn/system-design/order/mq-repetition.html)

    - 支付系统设计

    - 消息系统设计

        - [架构师设计的百万数据量安全导入](https://magestack.cn/system-design/message/import.html)

        - [架构师设计的百万数据量安全导出](https://magestack.cn/system-design/message/export.html)

- 场景实战

    - 并发编程

        - [参考 Dubbo 线程池模型实现快速消费线程池](https://magestack.cn/scene/thread/dubbo-fast-consumer.html)

        - [线程池如何监控，才能帮助开发者快速定位错误](https://magestack.cn/scene/thread/thread-pool-monitor.html)
        - [揭秘 Java8 ParallelStream 并行流极端情况串行化](https://magestack.cn/scene/thread/parallel-stream.html)

    - 分布式架构

        - [如何保证雪花算法集群环境下不重复](https://magestack.cn/scene/distributed/snowflake.html)

    - 应用安全

        - [如何防止数据库敏感信息泄漏](https://magestack.cn/scene/data-safety/database.html)

        - [如何防止配置文件敏感信息泄漏](https://magestack.cn/scene/data-safety/config-file.html)
        - [如何实现前端返回数据脱敏](https://magestack.cn/scene/data-safety/front.html)

    - 生产问题

        - [为什么线上异常信息为空，而本地可以正常打印](https://magestack.cn/scene/prod-issue/jvm-fast-throw.html)

        - [应用出现 OOM 异常，程序员如何第一时间知道](https://magestack.cn/scene/prod-issue/jvm-oom.html)
        - [核心接口请求出错，结果忘记打印相关日志](https://magestack.cn/scene/prod-issue/log.html)

    - 数据库

        - [MySQL 单表千万数据量如何深分页优化](https://magestack.cn/scene/database/deep-page.html)

        - [MyBatis 千万数据量查询不发生内存溢出](https://magestack.cn/scene/database/mybatis-query.html)

    - 分库分表

        - [分库分表如何实现平滑上线 & 回滚](https://magestack.cn/scene/sharding/smooth-sharding.html)

        - [按照时间分库分表，如何按照 ID 查询记录](https://magestack.cn/scene/sharding/complex-sharding.html)
        - [按照用户分库分表，如何按照订单 ID 查询记录](https://magestack.cn/scene/sharding/gene-sharding.html)

    - 本地&分布式缓存

        - [如何解决缓存穿透&击穿&雪崩](https://magestack.cn/scene/cache/issue.html)

        - [说说布隆过滤器查询误判和数据不能删除解决方案](https://magestack.cn/scene/cache/bloom-filter.html)
        - [如何通过咖啡因 & Redis 实现多级缓存](https://magestack.cn/scene/cache/multistage-cache.html)
        - [缓存和数据库一致性问题如何解决](https://magestack.cn/scene/cache/consistency.html)

- 设计模式实战

    - 设计模式介绍

        - [摊牌了！策略模式在项目设计中用得最多](https://magestack.cn/design-pattern/explain/strategy.html)

        - [春节期间，我用责任链模式重构了业务代码](https://magestack.cn/design-pattern/explain/chain.html)
        - [火遍全网的 Hutool，如何使用 Builder 模式创建线程池](https://magestack.cn/design-pattern/explain/builder.html)
        - [放弃 EventBus，选择更优雅的 ApplicationEvent](https://magestack.cn/design-pattern/explain/observer.html)
        - [如何通俗易懂理解什么是 SPI 模式](https://magestack.cn/design-pattern/explain/spi.html)
        - [学习 Mybatis 动态代理扩展拒绝策略](https://magestack.cn/design-pattern/explain/mybatis-proxy.html)

    - 设计模式抽象

        - [死磕设计模式之如何抽象策略模式](https://magestack.cn/design-pattern/abstract/strategy.html)

        - [死磕设计模式之如何抽象责任链模式](https://magestack.cn/design-pattern/abstract/chain.html)

- 开发规约

    - [Git 使用操作规约](https://magestack.cn/convention/git.html)

    - [框架版本号定义](https://magestack.cn/convention/frame-version.html)
    - [写一手好文档必备的规约](https://magestack.cn/convention/docs.html)
    - [阿里 P7 都在用的编码规约](https://magestack.cn/convention/coding.html)
    - [代码整洁为什么如此重要](https://magestack.cn/convention/code-format.html)
    - [如何提升代码质量](https://magestack.cn/convention/check-style.html)

