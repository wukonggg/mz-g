-- 0.6.3a

-- add：goods下架功能。sku的值为0时自动下架。退货时如果库存为0，需要自动上架。
ALTER TABLE `t_goods` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-有效' AFTER `utime`;
ALTER TABLE `t_sku` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-上架, 2-下架' AFTER `utime`;