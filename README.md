## 系统架构

https://www.processon.com/view/link/62984d1b0e3e74603c573306

## 功能完成列表

### 核心技术
mall4j-message 消息发送
- 使用 sharding sphere 自动时间段分片算法，对 mail_send_record 表按年进行分片

mall4j-customer-user C端用户
- 使用 sharding sphere 哈希取模分片算法，对 customer_user 表进行哈希分片
- 使用 sharding sphere 完成 AES 加密算法，对 customer_user phone、mail 字段进行加密

### 基础架构
mall4j-base-spring-boot-starter
- 应用上下文初始化后置处理器封装
- 应用上下文封装，方便在非 spring 类中获取 bean

mall4j-cache-spring-boot-starter
- redis 分布式缓存封装
- redis 分布式缓存自定义 key 前缀
- 缓存穿透问题解决方案
- 缓存击穿问题解决方案

mall4j-convention-spring-boot-starter
- 定义异常抽象，封装客户端、服务端、远程调用异常
- 基础异常码定义
- 分页请求、返回对象定义
- 全局返回对象定义

mall4j-ddd-framework-core
- ddd 核心代码定义

mall4j-design-pattern-spring-boot-starter
- 抽象策略模式容器

mall4j-distributedid-spring-boot-starter
- 解决雪花算法在集群环境生成重复问题

mall4j-log-spring-boot-starter
- 定义日志注解，打印入参、出参、方法名和执行时间

mall4j-mybatisplus-spring-boot-starter
- 引入 mysql 连接
- 引入 spring jdbc 引用
- 引入 mybatis-plus starter
- 定义基础 DO 对象
- 自定义 id 生成器
- mybatis-plus 元数据填充器
- mybatis-plus 分页对象定义

mall4j-swagger-spring-boot-starter
- 引入 swagger + knife4j
- 自定义默认 docket
- 项目启动后打印 knife4j docs api 地址

mall4j-web-spring-boot-starter
- 全局返回对象构建器
- 全局异常拦截器定义

### 业务模块
mall4j-message 消息发送
- 邮箱消息发送

mall4j-customer-user C端用户
- 用户验证
- 用户注册
- 用户登录

mall4j-gateway 外部网关

## 模块分类

|  | 模块名称 | 服务名称 | 访问地址 |
| --- | --- | --- | --- |
| 1 | mall4j-message | 消息发送 eg：邮件、公众号、短信等 | http://localhost:8001 |
| 2 | mall4j-customer-user | C 端用户 | http://localhost:8002 |
| 3 | mall4j-gateway | 外部网关 | http://localhost:8003 |
