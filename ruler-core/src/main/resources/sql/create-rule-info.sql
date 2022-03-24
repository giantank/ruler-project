CREATE TABLE IF NOT EXISTS 'tableName'
(
    `rule_code`       varchar(24) PRIMARY KEY COMMENT '规则编号',
    `business_type`   varchar(8) COMMENT '业务类型',
    `grade`           varchar(8) COMMENT '规则校验结果等级',
    `desc`            varchar(255) COMMENT '规则描述',
    `seq`             int(11) COMMENT '规则执行的顺序号',
    `required`        bit(1) COMMENT '是否强制使用',
    `enable`          bit(1) COMMENT '是否启用',
    `rule_class_name` varchar(128) COMMENT '规则实现类全限定类名'
) COMMENT '规则信息配置表'