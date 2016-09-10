-- 0.6.3a

-- add：goods下架功能。sku的值为0时自动下架。退货时如果库存为0，需要自动上架。
ALTER TABLE `t_goods` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-有效' AFTER `utime`;
ALTER TABLE `t_sku` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-上架, 2-下架' AFTER `utime`;

-- [now]调整打折策略(db-0.6.3)。支付时不再计算customer的clothingPayment，去除customer表的clothingPayment。
ALTER TABLE `t_customer` DROP COLUMN `payment_clothing`;