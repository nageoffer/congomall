CREATE TABLE `cart_item_0`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1635198236895604737 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_1`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_10`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_11`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_12`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_13`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_14`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_15`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_2`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_3`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_4`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_5`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_6`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_7`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_8`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `cart_item_9`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `product_id`        bigint(20) DEFAULT NULL COMMENT '商品SPU ID',
    `product_sku_id`    bigint(20) DEFAULT NULL COMMENT '商品SKU ID',
    `customer_user_id`  bigint(20) DEFAULT NULL COMMENT '用户ID',
    `product_pic`       varchar(256)   DEFAULT NULL COMMENT '商品图',
    `product_name`      varchar(256)   DEFAULT NULL COMMENT '商品名称',
    `product_brand`     varchar(256)   DEFAULT NULL COMMENT '商品品牌',
    `product_price`     decimal(10, 2) DEFAULT NULL COMMENT '商品价格',
    `product_quantity`  int(11) DEFAULT NULL COMMENT '加购物车数量',
    `product_attribute` varchar(1024)  DEFAULT NULL COMMENT '商品规格，JSON 格式',
    `select_flag`       tinyint(1) DEFAULT NULL COMMENT '选中标志 0：未选中 1：选中',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`       datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`          tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1634809410957627393 DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

CREATE TABLE `undo_log`
(
    `branch_id`     bigint(20) NOT NULL COMMENT 'branch transaction id',
    `xid`           varchar(128) NOT NULL COMMENT 'global transaction id',
    `context`       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` longblob     NOT NULL COMMENT 'rollback info',
    `log_status`    int(11) NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   datetime(6) NOT NULL COMMENT 'create datetime',
    `log_modified`  datetime(6) NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AT transaction mode undo table';
