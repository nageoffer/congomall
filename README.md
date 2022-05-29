
## 基础组件

| 模块 | 功能 | 进度 | 备注 |
| --- | --- | --- | --- |
| mall4j-cache-spring-boot-starter | 封装分布式缓存 | 进行中 | 解决缓存穿透、击穿等问题 |
|  | 多级缓存：本地缓存 + 分布式缓存 | 未开始 | 提高缓存性能，减少分布式缓存压力 |
| mall4j-convention-spring-boot-starter | 分页、全局返回对象、抽象异常码等 | ✅ 已完成 |  |
|  | 异常抽象，划分：客户端异常、服务端异常以及远程调用异常 | ✅ 已完成 |  |
| mall4j-ddd-framework-core | DDD 核心组件 | 进行中 |  |
| mall4j-distributedid-spring-boot-starter | 分布式 ID 组件 | 未开始 | 解决雪花算法在极端情况下生成重复问题 |
| mall4j-flow-monitor-agent | Java Agent 流量监控组件 | 未开始 | 统计中台接口的调用系统，以及各业务系统调用中台接口的流量统计 |
| mall4j-log-spring-boot-starter | 日志打印 | 未开始 | 打印入参、方法、返回参数以及方法执行时间 |
| mall4j-mybatisplus-spring-boot-starter | 持久层组件 | 进行中 |  |
|  | 深分页优化 | 未开始 |  |
|  | 添加自定义查询缓存 | 未开始 |  |
| mall4j-openfeign-spring-boot-starter | 远程调用组件 | 进行中 |  |
| mall4j-sensitive-spring-boot-starter | 前端敏感信息脱敏 | 未开始 |  |
| mall4j-web-spring-boot-starter | 全局异常拦截 | ✅ 已完成 |  |
|  | 全局返回对象工具类 | ✅ 已完成 |  |
|  | 请求 ID 链路传递 | 未开始 |  |

## 模块分类

|  | 模块名称 | 服务名称 | 访问地址 |
| --- | --- | --- | --- |
| 1 | mall4j-message | 消息发送 eg：邮件、公众号、短信等 | http://localhost:8001 |
| 2 | mall4j-customer-user | C 端用户 | http://localhost:8002 |
