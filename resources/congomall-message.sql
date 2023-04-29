CREATE TABLE `mail_template`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `template_name`  varchar(128) DEFAULT NULL COMMENT '模板名称',
    `template_id`    varchar(128) DEFAULT NULL COMMENT '模板 ID',
    `template_param` varchar(512) DEFAULT NULL COMMENT '模板参数',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime     DEFAULT NULL COMMENT '修改时间',
    `del_flag`       tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='邮箱模板';

CREATE TABLE `send_record_2023_m1`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m10`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m11`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m12`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m2`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1628006778296438785 DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m3`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1638209055761678337 DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m4`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m5`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m6`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m7`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m8`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2023_m9`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m1`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m10`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m11`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m12`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m2`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m3`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m4`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m5`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m6`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m7`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m8`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2024_m9`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m1`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m10`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m11`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m12`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m2`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m3`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m4`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m5`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m6`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m7`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m8`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2025_m9`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m1`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m10`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m11`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m12`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m2`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m3`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m4`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m5`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m6`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m7`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m8`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_2026_m9`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`        varchar(64)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_batch_id`  varchar(64)   DEFAULT NULL COMMENT '消息批量发送ID',
    `template_id`   varchar(64)   DEFAULT NULL COMMENT '模板ID',
    `msg_type`      int(3) DEFAULT NULL COMMENT '模板类型 0：短信-验证码 1：短信-通知 2：短信-营销 3：微信模板消息 4：邮箱 5...',
    `source_id`     varchar(64)   DEFAULT NULL COMMENT '渠道，用来统计发送来源方',
    `sender`        varchar(64)   DEFAULT NULL COMMENT '发送者',
    `receiver`      varchar(64)   DEFAULT NULL COMMENT '接收者',
    `cc`            varchar(64)   DEFAULT NULL COMMENT '抄送者，邮箱独有',
    `billing_count` int(3) DEFAULT NULL COMMENT '短信计费条数',
    `status`        tinyint(1) DEFAULT NULL COMMENT '消息状态: 0：发送成功 1：发送失败 2：发送中 3：提交失败',
    `fail_info`     varchar(2048) DEFAULT NULL COMMENT '失败详情',
    `send_time`     datetime      DEFAULT NULL COMMENT '发送时间',
    `receipt_time`  datetime      DEFAULT NULL COMMENT '接收时间，短信独有',
    `create_time`   datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`      tinyint(1) DEFAULT NULL COMMENT '删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息发送记录';

CREATE TABLE `send_record_extend_2023_m1`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m10`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m11`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m12`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m2`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1628006783530930177 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m3`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1638209056474710017 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m4`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m5`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m6`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m7`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m8`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2023_m9`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m1`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m10`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m11`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m12`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m2`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m3`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m4`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m5`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m6`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m7`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m8`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2024_m9`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m1`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m10`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m11`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m12`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m2`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m3`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m4`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m5`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m6`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m7`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m8`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2025_m9`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m1`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m10`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m11`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m12`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m2`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m3`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m4`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m5`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m6`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m7`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m8`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `send_record_extend_2026_m9`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `msg_id`      varchar(84)   DEFAULT NULL COMMENT '消息发送ID',
    `msg_param`   varchar(2048) DEFAULT NULL COMMENT '发送参数',
    `msg_content` varchar(2048) DEFAULT NULL COMMENT '发送内容',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '修改时间',
    `del_flag`    tinyint(1) DEFAULT '0' COMMENT '是否删除 0：否 1：是',
    PRIMARY KEY (`id`),
    KEY           `idx_msg_id` (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `mail_template` (`id`, `template_name`, `template_id`, `template_param`, `create_time`, `update_time`,
                             `del_flag`)
VALUES ('1', '用户注册验证码', 'userRegisterVerification', 'validCode', '2022-07-14 07:10:48', '2022-07-14 07:10:48',
        '0');
