
## 模块说明

商品服务，提供商品分类查询、商品详情查询、商品库存查询等能力。

├── congomall-product
│   ├── congomall-product-application  # 商品服务应用层
│   ├── congomall-product-domain  # 商品服务领域层
│   ├── congomall-product-infrastructure  # 商品服务基础层
│   ├── congomall-product-interface  # 商品服务用户接口层
│   ├── congomall-product-job  # 商品服务任务层

congomall-product-interface、congomall-product-job 都是可独立部署的应用。

接口文档：https://www.apifox.cn/apidoc/project-1038592/api-21806863

## 启动说明

### 项目依赖

无。

### 组件依赖

> 语雀相关文档查看知识星球置顶主题获取密码，避免盗版传播，会定期修改访问密码。

Sentinel 1.8.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/kus9sy

Nacos 2.x

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/mcn5ey

Seata 1.5.2

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/trwpfp57tuusw51b

SkyWalking 9.3.0 可选

- 部署文档：https://www.yuque.com/magestack/knowledge-planet/go4sgtgxgp33r27i
- 使用文档：https://www.yuque.com/magestack/knowledge-planet/qwle7f3hz4r5adcv

### 配置项修改

如果 XXL-Job 为 Docker 部署，需要修改 `congomall-product/congomall-product-job/src/main/resources/application.properties` 以下属性。

- xxl.job.executor.logpath：替换为本地目录。
- xxl.job.executor.ip：替换为本机 ID。

## 核心功能

千万数据通过并发编程高性能导入三方数据库，比如 ElasticSearch。

- 代码地址：`congomall-product/congomall-product-job/src/main/java/org/opengoofy/congomall/biz/product/job/handler/InitializeProductJobHandler.java`
- 文档地址：https://www.yuque.com/magestack/knowledge-planet/nya8aa
