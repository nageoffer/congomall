CREATE TABLE `pay_info`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_sn`         varchar(64)    DEFAULT NULL COMMENT '订单号',
    `out_order_sn`     varchar(64)    DEFAULT NULL COMMENT '商户订单号',
    `channel`          varchar(64)    DEFAULT NULL COMMENT '支付渠道',
    `trade_type`       varchar(64)    DEFAULT NULL COMMENT '支付环境',
    `subject`          varchar(512)   DEFAULT NULL COMMENT '订单标题',
    `order_request_id` varchar(64)    DEFAULT NULL COMMENT '商户订单号',
    `total_amount`     decimal(10, 2) DEFAULT NULL COMMENT '交易总金额',
    `trade_no`         varchar(256)   DEFAULT NULL COMMENT '三方交易凭证号',
    `gmt_payment`      datetime       DEFAULT NULL COMMENT '付款时间',
    `pay_amount`       decimal(10, 2) DEFAULT NULL COMMENT '支付金额',
    `status`           varchar(32)    DEFAULT NULL COMMENT '支付状态',
    `create_time`      datetime       DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime       DEFAULT NULL COMMENT '修改时间',
    `del_flag`         tinyint(1) DEFAULT NULL COMMENT '删除标记 0：未删除 1：删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1620760783201124353 DEFAULT CHARSET=utf8mb4 COMMENT='支付表';
