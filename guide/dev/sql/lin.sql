-- 0.6.3a

-- add：goods下架功能。sku的值为0时自动下架。退货时如果库存为0，需要自动上架。
ALTER TABLE `t_goods` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-有效' AFTER `utime`;
ALTER TABLE `t_sku` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-上架, 2-下架' AFTER `utime`;

--支付时不再计算customer的clothingPayment，去除customer表的clothingPayment。
ALTER TABLE `t_customer` DROP COLUMN `payment_clothing`;

-- 给customer增加字段
ALTER TABLE `t_customer` ADD COLUMN `ctime` DATETIME NOT NULL COMMENT '创建时间' AFTER `remark`;
ALTER TABLE `t_customer` ADD COLUMN `utime` DATETIME NOT NULL COMMENT '更新时间' AFTER `ctime`;
ALTER TABLE `t_customer` ADD COLUMN `uuid` INT NOT NULL COMMENT 'update user id(t_user)' AFTER `state`;

-- update customer基础数据
update t_customer set ctime=date('2015-11-15 00:00:00');
update t_customer set utime=date('2015-11-15 00:00:00');
update t_customer set uuid = 1;