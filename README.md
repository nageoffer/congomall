### 🎉 刚果商城，不一样的C端商城系统

---

官网地址：[magestack.cn](https://magestack.cn)

体验地址：[cmall.magestack.cn](http://cmall.magestack.cn)

刚果商城是个从零到一的C端商城项目，包含商城核心业务和基础架构两大模块。

参照商城系统原型，推出用户、消息、商品、订单、优惠券、支付、网关、购物车等业务模块，通过商城系统中复杂场景，给出对应解决方案。使用
DDD 模型开发系统功能，帮助对 DDD 一知半解的开发者树立正确地开发思路。

**如果对动态线程池感兴趣的同学，可以关注作者另一个项目 [Hippo4j](https://github.com/opengoofy/hippo4j) - 33 家企业生产使用，5.2k Star。**

![](https://images-machen.oss-cn-beijing.aliyuncs.com/1673165270664-5d0c4381-96ef-427b-a58d-9b21140eabe0-20230306173625527.png)

### 📚 能学到什么

---

刚果商城系统是我从事开发以来，在实际工作中遇到各种场景问题的“疑难杂症”汇总。

这些问题有些是自己遇到的，有些是其他人遇到帮忙解决的，最终把解决方案和代码实战放在刚果商城这个系统里。

这个系统没有很完整的商城业务，但是提供了偏架构层面有用的工具和参考，能帮助大家在实际项目中更好地解决问题。

如果你能够认真且坚持把项目看完，我相信你会收获包括不限于下面这些知识点：

- 基于 DDD 领域驱动模型设计实现的商品、购物车、订单、用户、消息以及支付服务。

- 掌握分布式锁、分布式事务、分布式搜索、分布式缓存、分布式限流以及分库分表等核心技术。
- 完成基础组件抽象，规约、缓存、幂等、分布式 ID、数据持久层、脱敏以及日志等底层组件库。
- 基于 Agent 开发字节码流量监控，监控项目接口 QPS、响应时间和异常请求等核心指标。
- 掌握常用设计模式实战场景，策略、责任链、装饰器、观察者以及适配器等设计模式。

### 🤩 联系我

---

开源不易，右上角点个 Star 鼓励一下吧！

如果大家想要实时关注 CongoMall 更新的文章以及分享的干货的话，可以关注我的公众号。

使用过程中有任何问题，或者对项目有什么建议，关注公众号回复：加群，和 `1000+` 志同道合的朋友交流讨论。

<img width="586" alt="image" src="https://user-images.githubusercontent.com/77398366/225888779-367f42a6-8401-4867-8e80-44214e1d17c1.png">

### 🔨 如何开始

---

刚果商城核心有两块，分别是商城业务和基础架构，通过认真学习分别可以收获以下两种能力提升。

- 商城业务：通过学习刚果商城中复杂业务处理场景，增加自己的复杂业务处理能力。

- 基础架构：尝试跟着基础架构部分自己把轮子都造一遍，以此提高自己方案设计和公共代码开发能力。

1）商城核心业务

目前 [前端页面](http://cmall.magestack.cn) 正在开发中，暂时没有开源出来，所以大家需要通过接口请求访问。接口如何访问详细看下文。

1. [初始化数据库](https://magestack.cn/preparation/init-datasource.html)，比如商品库、订单库、用户库、支付库、购物车库等；
2. [通过 Docker 安装项目中依赖的中间件](https://magestack.cn/preparation/environment/mysql_5.7_install.html)，比如 Nacos2、MySQL、Seata、RocketMQ 等；
3. 学习接口调用流程，项目接口目前全量放入 Apifox 软件中，可通过在线访问；
4. [查看不同微服务之间的依赖关系](https://magestack.cn/fast-start/user.html)，并根据文档中的描述进行修改指定参数；
5. 找到自己感兴趣的模块功能 Debug 源代码，参考代码设计。

<img width="817" alt="image" src="https://user-images.githubusercontent.com/77398366/229265338-dc16abdb-665a-49a7-a42a-653fbe3e5060.png">

2）基础架构

基础架构相关的代码都在 `congomall-framework-all` 模块中，可以通过官网查看如何实现，或者通过 [视频教学](https://magestack.cn/preparation/video.html) 来学习如何开发基础架构代码。

<img width="1090" alt="image" src="https://user-images.githubusercontent.com/77398366/229265310-7fa8b406-b621-4334-91d6-911c0b95dce3.png">

### 📝 文档

---

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

        - [揭秘 Java8 ParallelStream 并行流极端情况串行化](https://magestack.cn/scene/thread/parallel-stream.html)

        - [参考 Dubbo 线程池模型实现快速消费线程池](https://magestack.cn/scene/thread/dubbo-fast-consumer.html)
        - [线程池如何监控，才能帮助开发者快速定位错误](https://magestack.cn/scene/thread/thread-pool-monitor.html)
        - [线程池这样用，架构师看了都说好](https://magestack.cn/scene/thread/hippo4j.html)

    - 分布式架构

        - [如何保证雪花算法集群环境下不重复](https://magestack.cn/scene/distributed/snowflake.html)

        - [彻底掌握分布式事务 2PC、3PC 模型](https://magestack.cn/scene/distributed/23pc.html)
        - [从根上理解 Redis 分布式锁演进架构](https://magestack.cn/scene/distributed/lock.html)
        - [从 Redisson 源码到红锁 RedLock 算法思想](https://magestack.cn/scene/distributed/redisson.html)

    - 应用安全

        - [如何防止数据库敏感信息泄漏](https://magestack.cn/scene/data-safety/database.html)

        - [如何防止配置文件敏感信息泄漏](https://magestack.cn/scene/data-safety/config-file.html)
        - [如何实现前端返回数据脱敏](https://magestack.cn/scene/data-safety/front.html)

    - 生产问题

        - [为什么线上异常信息为空，而本地可以正常打印](https://magestack.cn/scene/prod-issue/jvm-fast-throw.html)

        - [面试官问生产环境 OOM 怎么解决，别再说不会了](https://magestack.cn/scene/prod-issue/interview-oom.html)
        - [应用出现 OOM 异常，程序员如何第一时间知道](https://magestack.cn/scene/prod-issue/jvm-oom.html)
        - [核心接口请求出错，结果忘记打印相关日志](https://magestack.cn/scene/prod-issue/log.html)

    - 数据库

        - [MySQL 单表千万数据量如何深分页优化](https://magestack.cn/scene/database/deep-page.html)

        - [MyBatis 千万数据量查询不发生内存溢出](https://magestack.cn/scene/database/mybatis-query.html)

    - 分库分表

        - [分库分表如何实现平滑上线 & 回滚](https://magestack.cn/scene/sharding/smooth-sharding.html)

        - [按照时间分库分表，如何按照 ID 查询记录](https://magestack.cn/scene/sharding/complex-sharding.html)
        - [按照用户 ID 分库分表，如何按照订单 ID 高效查询](https://magestack.cn/scene/sharding/gene-sharding.html)

    - 本地&分布式缓存

        - [如何解决缓存穿透&击穿&雪崩](https://magestack.cn/scene/cache/issue.html)

        - [说说布隆过滤器查询误判和数据不能删除解决方案](https://magestack.cn/scene/cache/bloom-filter.html)
        - [如何通过 Caffeine & Redis 实现多级缓存](https://magestack.cn/scene/cache/multistage-cache.html)
        - [缓存和数据库一致性问题如何解决](https://magestack.cn/scene/cache/consistency.html)

    - 源码解析

        - [MyBatis 整体架构设计分享](https://magestack.cn/scene/source-code/mybatis.html)

        - [花一个周末，掌握 OpenFeign 核心原理](https://magestack.cn/scene/source-code/openfeign.html)
        - [花一个周末，掌握 SpringCloud Ribbon 核心原理](https://magestack.cn/scene/source-code/ribbon.html)
        - [1.1w字，10图，轻松掌握 BlockingQueue](https://magestack.cn/scene/source-code/blocking-queue.html)
        - [聊一聊 ReentrantLock 和 AQS 那点事，看完不会你找我](https://magestack.cn/scene/source-code/reentrant-lock-aqs.html)

- 设计模式实战

    - [项目中使用设计模式的场景说明](https://magestack.cn/design-pattern/use.html)

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

- 开源之路

    - [提名 Apache ShardingSphere Committer，说说方法](https://magestack.cn/open-source/apache-committer.html)

- 开发规约

    - [Git 使用操作规约](https://magestack.cn/convention/git.html)

    - [框架版本号定义](https://magestack.cn/convention/frame-version.html)
    - [写一手好文档必备的规约](https://magestack.cn/convention/docs.html)
    - [阿里 P7 也在用的编码规约](https://magestack.cn/convention/coding.html)
    - [代码整洁为什么如此重要](https://magestack.cn/convention/code-format.html)
    - [如何提升代码质量](https://magestack.cn/convention/check-style.html)

### 👍 服务列表

---

刚果商城项目是马哥从零到一写出来的，当前已开发模块如下所述，没有完成的请耐心等待。

|    | 模块名称                    | 服务名称               | 访问地址                                          |
|----|-------------------------|--------------------|-----------------------------------------------|
| 1  | congomall-message       | 消息发送 eg：邮件、公众号、短信等 | [http://localhost:8001](http://localhost:8001) |
| 2  | congomall-customer-user | 用户服务               | [http://localhost:8002](http://localhost:8002) |
| 3  | congomall-gateway       | 外部网关               | [http://localhost:8003](http://localhost:8003) |
| 4  | congomall-product       | 商品服务               | [http://localhost:8004](http://localhost:8004) |
| 5  | congomall-product-job   | 商品 Job 服务          | [http://localhost:9001](http://localhost:9001) |
| 6  | congomall-cart          | 购物车服务              | [http://localhost:8005](http://localhost:8005) |
| 7  | congomall-order         | 订单服务               | [http://localhost:8006](http://localhost:8006) |
| 8  | congomall-pay           | 支付服务               | [http://localhost:8007](http://localhost:8007) |
| 9  | congomall-basic-data    | 基础数据服务             | [http://localhost:8008](http://localhost:8008) |
| 10 | congomall-bff           | BFF 商城聚合层          | [http://localhost:8009](http://localhost:8009) |

### 🚅 接口请求

---

目前刚果商城已开发的接口已汇总至下述接口文档中，本地启动对应项目，通过接口文档访问查看效果。

[https://www.apifox.cn/web/project/1038592/apis/api-50106328-run](https://www.apifox.cn/web/project/1038592/apis/api-50106328-run)

如果需要通过 Apifox 直接调用，需要安装对应浏览器内网插件，这里把插件安装包装上，跟着教程安装即可使用。

安装文档：[https://www.apifox.cn/help/app/web/browser-extension](https://www.apifox.cn/help/app/web/browser-extension)

![](https://images-machen.oss-cn-beijing.aliyuncs.com/1673619450701-2c7d5fe3-bf39-4c79-84e8-2ae4f0211a79.png)

### 🛡 模块介绍

---

刚果商城后端系统模块介绍如下所示。

```txt
├── congomall-basic-data  || -- # 基础数据服务
│   ├── congomall-basic-data-application
│   ├── congomall-basic-data-domain
│   ├── congomall-basic-data-infrastructure
│   ├── congomall-basic-data-interface
├── congomall-bff  || -- # 商城 BFF 聚合层
│   ├── congomall-bff-biz
│   ├── congomall-bff-remote
│   ├── congomall-bff-web
├── congomall-cart  || -- # 购物车服务
│   ├── congomall-cart-application
│   ├── congomall-cart-domain
│   ├── congomall-cart-infrastructure
│   ├── congomall-cart-interface
├── congomall-coupon  || -- # 优惠券服务
├── congomall-customer-user  || -- # C端用户服务
│   ├── congomall-customer-user-application
│   ├── congomall-customer-user-domain
│   ├── congomall-customer-user-infrastructure
│   ├── congomall-customer-user-interface
│   ├── congomall-customer-user-mock
├── congomall-framework-all  || -- # 基础组件
│   ├── congomall-base-spring-boot-starter  || -- # 顶层抽象基础组件
│   ├── congomall-cache-spring-boot-starter  || -- # 缓存组件
│   ├── congomall-common-spring-boot-starter  || -- # 公共工具包组件
│   ├── congomall-convention-spring-boot-starter  || -- # 项目规约组件
│   ├── congomall-database-spring-boot-starter  || -- # 数据库持久层组件
│   ├── congomall-ddd-framework-core  || -- # DDD抽象接口组件
│   ├── congomall-designpattern-spring-boot-starter  || -- # 设计模式抽象组件
│   ├── congomall-distributedid-spring-boot-starter  || -- # 分布式ID组件
│   ├── congomall-flow-monitor-agent  || -- # 微服务流量监控组件
│   ├── congomall-httputil-spring-boot-starter  || -- # Http网络调用组件
│   ├── congomall-idempotent-spring-boot-starter  || -- # 分布式幂等组件
│   ├── congomall-log-spring-boot-starter  || -- # 日志打印组件
│   ├── congomall-minio-spring-boot-starter  || -- # 文件存储组件
│   ├── congomall-openfeign-spring-boot-starter  || -- # 微服务调用组件
│   ├── congomall-rocketmq-spring-boot-starter  || -- # 分布式消息队列组件
│   ├── congomall-sensitive-spring-boot-starter  || -- # 前端返回数据脱敏组件
│   ├── congomall-swagger-spring-boot-starter  || -- # 文档API组件
│   ├── congomall-web-spring-boot-starter  || -- # Web组件
│   ├── congomall-xxljob-spring-boot-starter  || -- # 定时任务组件
├── congomall-gateway  || -- # 网关服务
├── congomall-message  || -- # 消息服务
│   ├── congomall-message-application
│   ├── congomall-message-domain
│   ├── congomall-message-infrastructure
│   ├── congomall-message-interface
├── congomall-order  || -- # 订单服务
│   ├── congomall-order-application
│   ├── congomall-order-domain
│   ├── congomall-order-infrastructure
│   ├── congomall-order-interface
├── congomall-pay  || -- # 支付服务
│   ├── congomall-pay-application
│   ├── congomall-pay-domain
│   ├── congomall-pay-infrastructure
│   ├── congomall-pay-interface
├── congomall-product  || -- # 商品服务
│   ├── congomall-product-application
│   ├── congomall-product-domain
│   ├── congomall-product-infrastructure
│   ├── congomall-product-interface
│   ├── congomall-product-job
├── congomall-test-all  || -- # 测试用例
│   ├── congomall-flow-monitor-agent-test
│   ├── congomall-h2-test
│   ├── congomall-oom-test
│   ├── congomall-smooth-sharding-test
│   ├── congomall-yaml-test
├── dev-support  || -- # 开发工具包
```

### 📈 技术选型

---

当前暂时先梳理后端技术，前端开发后再行梳理。

|    | 技术                  | 名称                 | 官网                                                                                                 |
|----|---------------------|--------------------|----------------------------------------------------------------------------------------------------|
| 1  | Spring Boot         | 基础框架               | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)                   |
| 2  | MyBatis-Plus        | 持久层框架              | [https://baomidou.com](https://baomidou.com)                                                       |
| 3  | HikariCP            | 数据库连接池             | [https://github.com/brettwooldridge/HikariCP](https://github.com/brettwooldridge/HikariCP)         |
| 4  | Redis               | 分布式缓存数据库           | [https://redis.io](https://redis.io)                                                               |
| 5  | RocketMQ            | 消息队列               | [https://rocketmq.apache.org](https://rocketmq.apache.org)                                         |
| 6  | ShardingSphere      | 数据库生态系统            | [https://shardingsphere.apache.org](https://shardingsphere.apache.org)                             |
| 7  | SpringCloud Alibaba | 分布式框架              | [https://github.com/alibaba/spring-cloud-alibaba](https://github.com/alibaba/spring-cloud-alibaba) |
| 8  | SpringCloud Gateway | 网关框架               | [https://spring.io/projects/spring-cloud-gateway](https://spring.io/projects/spring-cloud-gateway) |
| 9  | Seata               | 分布式事务框架            | [http://seata.io/zh-cn/index.html](http://seata.io/zh-cn/index.html)                               |
| 10 | Canal               | MySQL 订阅 BinLog 组件 | [https://github.com/alibaba/canal](https://github.com/alibaba/canal)                               |
| 11 | MinIO               | 文件存储框架             | [https://min.io](https://min.io)                                                                   |
| 12 | Swagger3            | 项目 API 文档框架        | [http://swagger.io](http://swagger.io)                                                             |
| 13 | Knife4j             | Swagger 增强框架       | [https://doc.xiaominfo.com](https://doc.xiaominfo.com/)                                            |
| 14 | Maven               | 项目构建管理             | [http://maven.apache.org](http://maven.apache.org)                                                 |
| 15 | Redisson            | Redis Java 客户端     | [https://redisson.org](https://redisson.org/)                                                      |
| 16 | Sentinel            | 流控防护框架             | [https://github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel)                         |
| 17 | Hippo4j             | 动态线程池框架            | [https://hippo4j.cn](https://hippo4j.cn)                                                           |
| 18 | XXL-Job             | 分布式定时任务框架          | [http://www.xuxueli.com/xxl-job](http://www.xuxueli.com/xxl-job)                                   |
| 19 | SkyWalking          | 分布式链路追踪框架          | [https://skywalking.apache.org](https://skywalking.apache.org/)                                    |
| 20 | JetCache            | Java 缓存框架          | [https://github.com/alibaba/jetcache](https://github.com/alibaba/jetcache)                                    |
