-- 0.6.3a

ALTER TABLE `t_goods` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-上架, 2-下架' AFTER `utime`;