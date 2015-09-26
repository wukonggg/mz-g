-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 mz-g 的数据库结构
DROP DATABASE IF EXISTS `mz-g`;
CREATE DATABASE IF NOT EXISTS `mz-g` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mz-g`;


-- 导出  表 mz-g.t_cart 结构
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE IF NOT EXISTS `t_cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL COMMENT '当前用户id',
  `cust_id` varchar(50) NOT NULL COMMENT '会员id',
  `sku_more_id` int(11) unsigned NOT NULL COMMENT 'sku_more_id',
  `count` int(11) unsigned NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1 COMMENT='购物车';

-- 正在导出表  mz-g.t_cart 的数据：~0 rows (大约)
DELETE FROM `t_cart`;
/*!40000 ALTER TABLE `t_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_cart` ENABLE KEYS */;


-- 导出  表 mz-g.t_cate 结构
DROP TABLE IF EXISTS `t_cate`;
CREATE TABLE IF NOT EXISTS `t_cate` (
  `title` varchar(50) NOT NULL COMMENT '标题。用于在用户交互时展示。',
  `code` varchar(20) NOT NULL COMMENT '品类编码',
  `pcode` varchar(20) NOT NULL COMMENT '父品类编码',
  `ord` tinyint(4) unsigned NOT NULL DEFAULT '255' COMMENT '同级排序',
  `seq` int(11) NOT NULL COMMENT 'sid序列值',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `state` char(2) NOT NULL COMMENT '0：已删除；1：有效；',
  UNIQUE KEY `UK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- 正在导出表  mz-g.t_cate 的数据：~12 rows (大约)
DELETE FROM `t_cate`;
/*!40000 ALTER TABLE `t_cate` DISABLE KEYS */;
INSERT INTO `t_cate` (`title`, `code`, `pcode`, `ord`, `seq`, `remark`, `state`) VALUES
	('SIMPLE', 'S', '0', 0, 0, 'SIMPLE CATEGORY', '1'),
	('A上衣套装', 'S-A', 'S', 1, 15, 'A上衣套装', '1'),
	('B裤子', 'S-B', 'S', 2, 3, 'B裤子', '1'),
	('C内衣', 'S-C', 'S', 3, 2, 'C内衣', '1'),
	('D鞋子', 'S-D', 'S', 4, 0, 'D鞋子', '1'),
	('E袜子/护袖/口水巾/罩衫', 'S-E', 'S', 5, 0, 'E袜子/护袖/口水巾/罩衫', '1'),
	('F零食', 'S-F', 'S', 6, 0, 'F零食', '1'),
	('G包包', 'S-G', 'S', 7, 0, 'G包包', '1'),
	('H帽子', 'S-H', 'S', 8, 0, 'H帽子', '1'),
	('I尿不湿', 'S-I', 'S', 9, 0, 'I尿不湿', '1'),
	('J玩具', 'S-J', 'S', 10, 2, 'J玩具', '1'),
	('K文具', 'S-K', 'S', 11, 0, 'K文具', '1');
/*!40000 ALTER TABLE `t_cate` ENABLE KEYS */;


-- 导出  表 mz-g.t_customer 结构
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE IF NOT EXISTS `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` varchar(20) NOT NULL COMMENT '会员卡号',
  `name` varchar(20) DEFAULT NULL COMMENT '会员姓名',
  `birth` date DEFAULT NULL COMMENT '宝宝生日',
  `msisdn` varchar(20) DEFAULT NULL COMMENT '手机号',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `payment_clothing` int(11) DEFAULT '0' COMMENT '服装类消费总额',
  `state` char(2) NOT NULL COMMENT '0：已删除；1：有效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='客户表';

-- 正在导出表  mz-g.t_customer 的数据：~8 rows (大约)
DELETE FROM `t_customer`;
/*!40000 ALTER TABLE `t_customer` DISABLE KEYS */;
INSERT INTO `t_customer` (`id`, `cid`, `name`, `birth`, `msisdn`, `address`, `remark`, `payment_clothing`, `state`) VALUES
	(1, '00000004', '非会员顾客', '2014-11-16', '0', '0', NULL, 0, '1'),
	(2, '99999999999', 'Abel', '2015-04-10', '18181798763', 'LHW', '11', 5000, '1'),
	(3, '99999999998', 'Black', '2015-04-10', '18982746372', 'LHW', NULL, 3000, '1'),
	(4, '99999999997', 'Candy', '2015-04-10', '18238374659', NULL, NULL, 1000, '1'),
	(5, '99999999996', 'Daniel', '2015-04-10', '18928374678', NULL, NULL, 0, '1'),
	(6, '99999999995', 'Ema', '2015-04-10', '18238479906', '123123sd阿斯达', NULL, 0, '0'),
	(7, '99999999995', 'Frank', '2015-05-01', '18998773495', 'White house', 'power', 0, '1'),
	(12, '99999999994', 'Gary', '2015-07-01', '13790785566', 'nanjing', 'nanjing', 0, '1');
/*!40000 ALTER TABLE `t_customer` ENABLE KEYS */;


-- 导出  表 mz-g.t_goods 结构
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE IF NOT EXISTS `t_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cate_code` varchar(20) NOT NULL COMMENT '品类',
  `gname` varchar(100) NOT NULL COMMENT '货名',
  `words` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `img` varchar(100) DEFAULT 'goods_default.png' COMMENT '商品图片文件名。如flower.png。',
  `ctime` datetime NOT NULL,
  `utime` datetime DEFAULT NULL,
  `state` char(2) NOT NULL COMMENT '0：已删除；1：有效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='商品表。 TODO -OPT 以后扩展：供应商，厂家，品牌等';

-- 正在导出表  mz-g.t_goods 的数据：~0 rows (大约)
DELETE FROM `t_goods`;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;


-- 导出  表 mz-g.t_item 结构
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE IF NOT EXISTS `t_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL DEFAULT '0' COMMENT 'sku表pk',
  `sku_more_id` int(11) NOT NULL DEFAULT '0' COMMENT 'skumore表pk',
  `cate_code_snapshot` varchar(20) NOT NULL DEFAULT '0' COMMENT '当前品类快照',
  `sprice_snapshot` int(11) NOT NULL DEFAULT '0' COMMENT '当前零售价快照',
  `dprice` int(11) NOT NULL DEFAULT '0' COMMENT '成交单价  当状态为"退货"时，字段为负',
  `dcount` int(11) NOT NULL DEFAULT '0' COMMENT '成交数量  当状态为"退货"时，字段为负',
  `payment` int(11) NOT NULL DEFAULT '0' COMMENT '付款金额',
  `return_time` datetime DEFAULT NULL COMMENT '退货时间',
  `return_user_id` int(11) DEFAULT '0' COMMENT '退货的操作员',
  `return_reason` char(2) DEFAULT '0' COMMENT '退货原因   1：质量问题；2尺码问题',
  `return_desc` varchar(100) DEFAULT '0' COMMENT '退货描述',
  `state` char(2) NOT NULL DEFAULT '0' COMMENT '0：已删除；1：有效；2：退货',
  `order_id` int(11) NOT NULL DEFAULT '0' COMMENT 'order表pk',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_item 的数据：~0 rows (大约)
DELETE FROM `t_item`;
/*!40000 ALTER TABLE `t_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_item` ENABLE KEYS */;


-- 导出  表 mz-g.t_order 结构
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE IF NOT EXISTS `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '营业员id',
  `cust_id` int(11) NOT NULL COMMENT '顾客id',
  `dtime` datetime NOT NULL COMMENT '成交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_order 的数据：~0 rows (大约)
DELETE FROM `t_order`;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;


-- 导出  表 mz-g.t_permission 结构
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE IF NOT EXISTS `t_permission` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `al` varchar(50) DEFAULT NULL,
  `dt` varchar(500) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_permission 的数据：~0 rows (大约)
DELETE FROM `t_permission`;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;


-- 导出  表 mz-g.t_role 结构
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `al` varchar(50) DEFAULT NULL,
  `dt` varchar(500) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_role 的数据：~0 rows (大约)
DELETE FROM `t_role`;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;


-- 导出  表 mz-g.t_role_permission 结构
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE IF NOT EXISTS `t_role_permission` (
  `role_id` bigint(64) DEFAULT NULL,
  `permission_id` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_role_permission 的数据：~0 rows (大约)
DELETE FROM `t_role_permission`;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;


-- 导出  表 mz-g.t_sku 结构
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE IF NOT EXISTS `t_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` varchar(50) NOT NULL COMMENT 'sku_id。sku_xxx的唯一id，因为id可能在不同的sku_xxx表中重复',
  `model` varchar(50) NOT NULL COMMENT '(一级分类)款型/颜色',
  `type` varchar(50) NOT NULL COMMENT '(二级分类)分类。如尺码标准，对应t_sku_prop_type表',
  `ptime` datetime NOT NULL COMMENT '入库日期',
  `pprice` int(11) NOT NULL COMMENT '入库价格',
  `sprice` int(11) NOT NULL COMMENT '零售价格',
  `img` varchar(50) DEFAULT NULL COMMENT '单品图片',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '更新时间',
  `state` char(2) NOT NULL COMMENT '0：已删除；1：上架；2：下架',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='商品SKU主表';

-- 正在导出表  mz-g.t_sku 的数据：~0 rows (大约)
DELETE FROM `t_sku`;
/*!40000 ALTER TABLE `t_sku` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sku` ENABLE KEYS */;


-- 导出  表 mz-g.t_sku_more 结构
DROP TABLE IF EXISTS `t_sku_more`;
CREATE TABLE IF NOT EXISTS `t_sku_more` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL COMMENT 't_sku_clothing表的id',
  `size` varchar(50) DEFAULT NULL COMMENT '取值为t_sku_dict表的SIZE_STANDARD相关item',
  `count` int(11) unsigned NOT NULL COMMENT '数量',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8 COMMENT='商品SKU更多表';

-- 正在导出表  mz-g.t_sku_more 的数据：~0 rows (大约)
DELETE FROM `t_sku_more`;
/*!40000 ALTER TABLE `t_sku_more` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sku_more` ENABLE KEYS */;


-- 导出  表 mz-g.t_sku_prop_type 结构
DROP TABLE IF EXISTS `t_sku_prop_type`;
CREATE TABLE IF NOT EXISTS `t_sku_prop_type` (
  `cate_code` varchar(20) NOT NULL COMMENT 'cate_code&item&name联合保持唯一性',
  `item` varchar(50) DEFAULT NULL COMMENT '项目',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `title` varchar(50) DEFAULT NULL,
  `evalue` varchar(50) DEFAULT NULL COMMENT '值',
  `cvalue` varchar(50) DEFAULT NULL COMMENT '描述',
  `name_order` tinyint(4) DEFAULT NULL COMMENT 'name排序',
  `value_order` tinyint(4) DEFAULT NULL COMMENT 'value排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU扩展属性-type属性表';

-- 正在导出表  mz-g.t_sku_prop_type 的数据：~121 rows (大约)
DELETE FROM `t_sku_prop_type`;
/*!40000 ALTER TABLE `t_sku_prop_type` DISABLE KEYS */;
INSERT INTO `t_sku_prop_type` (`cate_code`, `item`, `name`, `title`, `evalue`, `cvalue`, `name_order`, `value_order`) VALUES
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '50', '50cm', 1, 1),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '55', '55cm', 1, 2),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '60', '60cm', 1, 3),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '65', '65cm', 1, 4),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '70', '70cm', 1, 5),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '75', '75cm', 1, 6),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '80', '80cm', 1, 7),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '85', '85cm', 1, 8),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '90', '90cm', 1, 9),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '95', '95cm', 1, 10),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '100', '100cm', 1, 11),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '110', '110cm', 1, 12),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '120', '120cm', 1, 13),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '130', '130cm', 1, 14),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '140', '140cm', 1, 15),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '150', '150cm', 1, 16),
	('S-A', 'SIZE_STANDARD', 'HEIGHT', '身高', '160', '160cm', 1, 17),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '1-2', '1-2岁', 2, 1),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '2-3', '2-3岁', 2, 2),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '3-4', '3-4岁', 2, 3),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '4-5', '4-5岁', 2, 4),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '5-6', '5-6岁', 2, 5),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '6-7', '6-7岁', 2, 6),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '7-8', '7-8岁', 2, 7),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '8-9', '8-9岁', 2, 8),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '9-10', '9-10岁', 2, 9),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '10-11', '10-11岁', 2, 10),
	('S-A', 'SIZE_STANDARD', 'AGE', '年龄', '11-12', '11-12岁', 2, 11),
	('S-A', 'SIZE_STANDARD', 'SIZE', '尺码', 'S', '小号', 3, 1),
	('S-A', 'SIZE_STANDARD', 'SIZE', '尺码', 'M', '中号', 3, 2),
	('S-A', 'SIZE_STANDARD', 'SIZE', '尺码', 'L', '大号', 3, 3),
	('S-A', 'SIZE_STANDARD', 'SIZE', '尺码', 'XL', '特大号', 3, 4),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '50', '50cm', 1, 1),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '55', '55cm', 1, 2),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '60', '60cm', 1, 3),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '65', '65cm', 1, 4),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '70', '70cm', 1, 5),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '75', '75cm', 1, 6),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '80', '80cm', 1, 7),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '85', '85cm', 1, 8),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '90', '90cm', 1, 9),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '95', '95cm', 1, 10),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '100', '100cm', 1, 11),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '110', '110cm', 1, 12),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '120', '120cm', 1, 13),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '130', '130cm', 1, 14),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '140', '140cm', 1, 15),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '150', '150cm', 1, 16),
	('S-B', 'SIZE_STANDARD', 'HEIGHT', '身高', '160', '160cm', 1, 17),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '1-2', '1-2岁', 2, 1),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '2-3', '2-3岁', 2, 2),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '3-4', '3-4岁', 2, 3),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '4-5', '4-5岁', 2, 4),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '5-6', '5-6岁', 2, 5),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '6-7', '6-7岁', 2, 6),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '7-8', '7-8岁', 2, 7),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '8-9', '8-9岁', 2, 8),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '9-10', '9-10岁', 2, 9),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '10-11', '10-11岁', 2, 10),
	('S-B', 'SIZE_STANDARD', 'AGE', '年龄', '11-12', '11-12岁', 2, 11),
	('S-B', 'SIZE_STANDARD', 'SIZE', '尺码', 'S', '小号', 3, 1),
	('S-B', 'SIZE_STANDARD', 'SIZE', '尺码', 'M', '中号', 3, 2),
	('S-B', 'SIZE_STANDARD', 'SIZE', '尺码', 'L', '大号', 3, 3),
	('S-B', 'SIZE_STANDARD', 'SIZE', '尺码', 'XL', '特大号', 3, 4),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '50', '50cm', 1, 1),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '55', '55cm', 1, 2),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '60', '60cm', 1, 3),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '65', '65cm', 1, 4),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '70', '70cm', 1, 5),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '75', '75cm', 1, 6),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '80', '80cm', 1, 7),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '85', '85cm', 1, 8),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '90', '90cm', 1, 9),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '95', '95cm', 1, 10),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '100', '100cm', 1, 11),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '110', '110cm', 1, 12),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '120', '120cm', 1, 13),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '130', '130cm', 1, 14),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '140', '140cm', 1, 15),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '150', '150cm', 1, 16),
	('S-C', 'SIZE_STANDARD', 'HEIGHT', '身高', '160', '160cm', 1, 17),
	('S-C', 'SIZE_STANDARD', 'SIZE', '尺码', 'S', '小号', 2, 1),
	('S-C', 'SIZE_STANDARD', 'SIZE', '尺码', 'M', '中号', 2, 2),
	('S-C', 'SIZE_STANDARD', 'SIZE', '尺码', 'L', '大号', 2, 3),
	('S-C', 'SIZE_STANDARD', 'SIZE', '尺码', 'XL', '特大号', 2, 4),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '12', '12cm', 1, 1),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '13', '13cm', 1, 2),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '14', '14cm', 1, 3),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '15', '15cm', 1, 4),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '16', '16cm', 1, 5),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '17', '17cm', 1, 6),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '18', '18cm', 1, 7),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '19', '19cm', 1, 8),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '20', '20cm', 1, 9),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '21', '21cm', 1, 10),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '22', '22cm', 1, 11),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '23', '23cm', 1, 12),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '24', '24cm', 1, 13),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '25', '25cm', 1, 14),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '26', '26cm', 1, 15),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '27', '27cm', 1, 16),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '28', '28cm', 1, 17),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '29', '29cm', 1, 18),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '30', '30cm', 1, 19),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '31', '31cm', 1, 20),
	('S-D', 'SIZE_STANDARD', 'LENGTH', '长度', '32', '32cm', 1, 21),
	('S-E', 'SIZE_STANDARD', 'AGE', '年龄', '0-3', '0-3岁', 1, 1),
	('S-E', 'SIZE_STANDARD', 'AGE', '年龄', '3-6', '3-6岁', 1, 2),
	('S-E', 'SIZE_STANDARD', 'AGE', '年龄', '6-12', '6-12岁', 1, 3),
	('S-E', 'SIZE_STANDARD', 'AGE', '年龄', '12+', '12岁以上', 1, 4),
	('S-F', 'SIZE_STANDARD', 'DEFAULT', '默认分类', 'blabla', 'blabla', 1, 1),
	('S-G', 'SIZE_STANDARD', 'DEFAULT', '默认分类', 'blabla', 'blabla', 1, 1),
	('S-H', 'SIZE_STANDARD', 'DEFAULT', '默认分类', 'blabla', 'blabla', 1, 1),
	('S-I', 'SIZE_STANDARD', 'DEFAULT', '默认分类', 'blabla', 'blabla', 1, 1),
	('S-J', 'SIZE_STANDARD', 'SIZE', '尺码', 'NB', 'NB', 1, 1),
	('S-J', 'SIZE_STANDARD', 'SIZE', '尺码', 'S', 'S', 1, 2),
	('S-J', 'SIZE_STANDARD', 'SIZE', '尺码', 'M', 'M', 1, 3),
	('S-J', 'SIZE_STANDARD', 'SIZE', '尺码', 'L', 'L', 1, 4),
	('S-J', 'SIZE_STANDARD', 'SIZE', '尺码', 'XL', 'XL', 1, 5),
	('S-J', 'SIZE_STANDARD', 'SIZE', '尺码', 'XXL', 'XXL', 1, 6),
	('S-K', 'SIZE_STANDARD', 'DEFAULT', '默认分类', 'blabla', 'blabla', 1, 1);
/*!40000 ALTER TABLE `t_sku_prop_type` ENABLE KEYS */;


-- 导出  表 mz-g.t_user 结构
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `passwd` varchar(128) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_user 的数据：~1 rows (大约)
DELETE FROM `t_user`;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `name`, `passwd`, `locked`, `state`, `ct`, `ut`) VALUES
	(1, 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 0, '1', '2015-09-12 17:28:48', '2015-09-12 17:28:48');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


-- 导出  表 mz-g.t_user_permission 结构
DROP TABLE IF EXISTS `t_user_permission`;
CREATE TABLE IF NOT EXISTS `t_user_permission` (
  `u_id` bigint(64) DEFAULT NULL,
  `permission_id` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_user_permission 的数据：~0 rows (大约)
DELETE FROM `t_user_permission`;
/*!40000 ALTER TABLE `t_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_permission` ENABLE KEYS */;


-- 导出  表 mz-g.t_user_role 结构
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `u_id` bigint(64) DEFAULT NULL,
  `role_id` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mz-g.t_user_role 的数据：~0 rows (大约)
DELETE FROM `t_user_role`;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;


-- 导出  视图 mz-g.v_cart 结构
DROP VIEW IF EXISTS `v_cart`;
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `v_cart` (
	`id` INT(11) UNSIGNED NOT NULL,
	`user_id` INT(11) UNSIGNED NOT NULL COMMENT '当前用户id',
	`cust_id` INT(11) NOT NULL,
	`cid` VARCHAR(20) NOT NULL COMMENT '会员卡号' COLLATE 'utf8_general_ci',
	`name` VARCHAR(20) NULL COMMENT '会员姓名' COLLATE 'utf8_general_ci',
	`msisdn` VARCHAR(20) NULL COMMENT '手机号' COLLATE 'utf8_general_ci',
	`payment_clothing` INT(11) NULL COMMENT '服装类消费总额',
	`gname` VARCHAR(100) NOT NULL COMMENT '货名' COLLATE 'utf8_general_ci',
	`cate_code` VARCHAR(20) NOT NULL COMMENT '品类' COLLATE 'utf8_general_ci',
	`sid` VARCHAR(50) NOT NULL COMMENT 'sku_id。sku_xxx的唯一id，因为id可能在不同的sku_xxx表中重复' COLLATE 'utf8_general_ci',
	`img` VARCHAR(50) NULL COMMENT '单品图片' COLLATE 'utf8_general_ci',
	`model` VARCHAR(50) NOT NULL COMMENT '(一级分类)款型/颜色' COLLATE 'utf8_general_ci',
	`sprice` INT(11) NOT NULL COMMENT '零售价格',
	`sku_more_id` INT(11) NOT NULL,
	`size` VARCHAR(50) NULL COMMENT '取值为t_sku_dict表的SIZE_STANDARD相关item' COLLATE 'utf8_general_ci',
	`scount` INT(11) UNSIGNED NOT NULL COMMENT '数量',
	`count` INT(11) UNSIGNED NOT NULL COMMENT '数量'
) ENGINE=MyISAM;


-- 导出  视图 mz-g.v_sku_more 结构
DROP VIEW IF EXISTS `v_sku_more`;
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `v_sku_more` (
	`skuId` INT(11) NOT NULL,
	`skumoreId` INT(11) NOT NULL,
	`goodsId` INT(11) UNSIGNED NOT NULL,
	`gname` VARCHAR(100) NOT NULL COMMENT '货名' COLLATE 'utf8_general_ci',
	`cateCode` VARCHAR(20) NOT NULL COMMENT '品类' COLLATE 'utf8_general_ci',
	`sid` VARCHAR(50) NOT NULL COMMENT 'sku_id。sku_xxx的唯一id，因为id可能在不同的sku_xxx表中重复' COLLATE 'utf8_general_ci',
	`model` VARCHAR(50) NOT NULL COMMENT '(一级分类)款型/颜色' COLLATE 'utf8_general_ci',
	`ptime` DATETIME NOT NULL COMMENT '入库日期',
	`pprice` INT(11) NOT NULL COMMENT '入库价格',
	`sprice` INT(11) NOT NULL COMMENT '零售价格',
	`img` VARCHAR(50) NULL COMMENT '单品图片' COLLATE 'utf8_general_ci',
	`state` CHAR(2) NOT NULL COMMENT '0：已删除；1：上架；2：下架' COLLATE 'utf8_general_ci',
	`size` VARCHAR(50) NULL COMMENT '取值为t_sku_dict表的SIZE_STANDARD相关item' COLLATE 'utf8_general_ci',
	`count` INT(11) UNSIGNED NOT NULL COMMENT '数量',
	`remark` VARCHAR(50) NULL COMMENT '备注' COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;


-- 导出  视图 mz-g.v_cart 结构
DROP VIEW IF EXISTS `v_cart`;
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `v_cart`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_cart` AS select `ca`.`id` AS `id`,`ca`.`user_id` AS `user_id`,`cu`.`id` AS `cust_id`,`cu`.`cid` AS `cid`,`cu`.`name` AS `name`,`cu`.`msisdn` AS `msisdn`,`cu`.`payment_clothing` AS `payment_clothing`,`g`.`gname` AS `gname`,`g`.`cate_code` AS `cate_code`,`sku`.`sid` AS `sid`,`sku`.`img` AS `img`,`sku`.`model` AS `model`,`sku`.`sprice` AS `sprice`,`sm`.`id` AS `sku_more_id`,`sm`.`size` AS `size`,`sm`.`count` AS `scount`,`ca`.`count` AS `count` from ((((`t_cart` `ca` join `t_sku_more` `sm` on((`sm`.`id` = `ca`.`sku_more_id`))) join `t_sku` `sku` on((`sku`.`id` = `sm`.`sku_id`))) join `t_goods` `g` on((`g`.`id` = `sku`.`goods_id`))) join `t_customer` `cu` on((`cu`.`id` = `ca`.`cust_id`))) where ((`cu`.`state` = 1) and (`g`.`state` = 1) and (`sku`.`state` = 1));


-- 导出  视图 mz-g.v_sku_more 结构
DROP VIEW IF EXISTS `v_sku_more`;
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `v_sku_more`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_sku_more` AS select `t1`.`id` AS `skuId`,`t`.`id` AS `skumoreId`,`t2`.`id` AS `goodsId`,`t2`.`gname` AS `gname`,`t2`.`cate_code` AS `cateCode`,`t1`.`sid` AS `sid`,`t1`.`model` AS `model`,`t1`.`ptime` AS `ptime`,`t1`.`pprice` AS `pprice`,`t1`.`sprice` AS `sprice`,`t1`.`img` AS `img`,`t1`.`state` AS `state`,`t`.`size` AS `size`,`t`.`count` AS `count`,`t`.`remark` AS `remark` from ((`t_sku_more` `t` join `t_sku` `t1` on((`t1`.`id` = `t`.`sku_id`))) join `t_goods` `t2` on((`t2`.`id` = `t1`.`goods_id`))) where (`t1`.`state` <> 0) order by `t2`.`id`,`t`.`id`;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
