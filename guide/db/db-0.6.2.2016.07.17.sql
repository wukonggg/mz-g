-- MySQL dump 10.13  Distrib 5.6.26, for Win64 (x86_64)
--
-- Host: localhost    Database: mz-g
-- ------------------------------------------------------
-- Server version	5.6.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_app_info`
--

DROP TABLE IF EXISTS `t_app_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_info` (
  `k` varchar(20) NOT NULL COMMENT 'key',
  `v` varchar(20) NOT NULL COMMENT 'value'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用程序信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_info`
--

LOCK TABLES `t_app_info` WRITE;
/*!40000 ALTER TABLE `t_app_info` DISABLE KEYS */;
INSERT INTO `t_app_info` VALUES ('app_name','mz-gallery'),('app_version','0.6.2'),('db_version','0.6.2'),('user_test','test/000000');
/*!40000 ALTER TABLE `t_app_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cart`
--

DROP TABLE IF EXISTS `t_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL COMMENT '当前用户id',
  `cust_id` varchar(50) NOT NULL COMMENT '会员id',
  `sku_more_id` int(11) unsigned NOT NULL COMMENT 'sku_more_id',
  `count` int(11) unsigned NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1 COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cart`
--

LOCK TABLES `t_cart` WRITE;
/*!40000 ALTER TABLE `t_cart` DISABLE KEYS */;
INSERT INTO `t_cart` VALUES (1,1,'1',1,1);
/*!40000 ALTER TABLE `t_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cate`
--

DROP TABLE IF EXISTS `t_cate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cate` (
  `title` varchar(50) NOT NULL COMMENT '标题。用于在用户交互时展示。',
  `code` varchar(20) NOT NULL COMMENT '品类编码',
  `pcode` varchar(20) NOT NULL COMMENT '父品类编码',
  `ord` tinyint(4) unsigned NOT NULL DEFAULT '255' COMMENT '同级排序',
  `seq` int(11) NOT NULL COMMENT 'sid序列值',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `state` char(2) NOT NULL COMMENT '0：已删除；1：有效；',
  UNIQUE KEY `UK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cate`
--

LOCK TABLES `t_cate` WRITE;
/*!40000 ALTER TABLE `t_cate` DISABLE KEYS */;
INSERT INTO `t_cate` VALUES ('SIMPLE','S','0',0,0,'SIMPLE CATEGORY','1'),('A上衣&套装','S-A','S',1,0,'A上衣&套装','1'),('B裤子','S-B','S',2,0,'B裤子','1'),('C内衣','S-C','S',3,0,'C内衣','1'),('D鞋子','S-D','S',4,1,'D鞋子','1'),('E日化','S-E','S',5,0,'E日化','1'),('F零食&保健品','S-F','S',6,0,'F零食&保健品','1'),('G奶粉','S-G','S',7,0,'G奶粉','1'),('H玩具','S-H','S',8,0,'H玩具','1'),('I尿不湿','S-I','S',9,0,'I尿不湿','1'),('J文具','S-J','S',10,0,'J文具','1');
/*!40000 ALTER TABLE `t_cate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_customer`
--

DROP TABLE IF EXISTS `t_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` varchar(20) NOT NULL COMMENT '会员卡号',
  `name` varchar(20) DEFAULT NULL COMMENT '会员姓名',
  `birth` date DEFAULT NULL COMMENT '宝宝生日',
  `msisdn` varchar(20) DEFAULT NULL COMMENT '手机号',
  `weixin` varchar(20) DEFAULT NULL COMMENT '微信',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `payment_clothing` int(11) DEFAULT '0' COMMENT '服装类消费总额',
  `state` char(2) NOT NULL COMMENT '0：已删除；1：有效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='客户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_customer`
--

LOCK TABLES `t_customer` WRITE;
/*!40000 ALTER TABLE `t_customer` DISABLE KEYS */;
INSERT INTO `t_customer` VALUES (1,'00000004','非会员顾客','2014-11-16','','00000004','','非会员顾客购买时使用',0,'1');
/*!40000 ALTER TABLE `t_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cate_code` varchar(20) NOT NULL COMMENT '品类',
  `gname` varchar(100) NOT NULL COMMENT '货名',
  `words` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `img` varchar(100) DEFAULT 'goods_default.png' COMMENT '商品图片文件名。如flower.png。',
  `ctime` datetime NOT NULL,
  `utime` datetime DEFAULT NULL,
  `state` char(2) NOT NULL COMMENT '0：已删除；1：有效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品表。 TODO -OPT 以后扩展：供应商，厂家，品牌等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
INSERT INTO `t_goods` VALUES (1,'S-A','11','11','84af2271-ae1e-471b-9cda-a72b7fbac05a.jpg','2016-07-16 23:08:10',NULL,'1');
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_item`
--

DROP TABLE IF EXISTS `t_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_item` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='购物车-商品项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_item`
--

LOCK TABLES `t_item` WRITE;
/*!40000 ALTER TABLE `t_item` DISABLE KEYS */;
INSERT INTO `t_item` VALUES (1,1,2,'S-A',15,15,1,15,NULL,0,NULL,NULL,'1',1);
/*!40000 ALTER TABLE `t_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '营业员id',
  `cust_id` int(11) NOT NULL COMMENT '顾客id',
  `dtime` datetime NOT NULL COMMENT '成交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (1,1,8,'2016-07-16 23:09:57');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `al` varchar(50) DEFAULT NULL,
  `dt` varchar(500) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `al` varchar(50) DEFAULT NULL,
  `dt` varchar(500) DEFAULT NULL,
  `ct` datetime DEFAULT NULL,
  `ut` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(64) DEFAULT NULL,
  `permission_id` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sequence`
--

DROP TABLE IF EXISTS `t_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sequence表（模拟sequence）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sequence`
--

LOCK TABLES `t_sequence` WRITE;
/*!40000 ALTER TABLE `t_sequence` DISABLE KEYS */;
INSERT INTO `t_sequence` VALUES ('S-A',100000,1,'SID-S-A'),('S-B',100000,1,'SID-S-B'),('S-C',100000,1,'SID-S-C'),('S-D',100000,1,'SID-S-D'),('S-E',100000,1,'SID-S-E'),('S-F',100000,1,'SID-S-F'),('S-G',100000,1,'SID-S-G'),('S-H',100000,1,'SID-S-H'),('S-I',100000,1,'SID-S-I'),('S-J',100000,1,'SID-S-J');
/*!40000 ALTER TABLE `t_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sku`
--

DROP TABLE IF EXISTS `t_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sku` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品SKU主表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sku`
--

LOCK TABLES `t_sku` WRITE;
/*!40000 ALTER TABLE `t_sku` DISABLE KEYS */;
INSERT INTO `t_sku` VALUES (1,'A100001','123','SIZE','2016-07-16 00:00:00',12,15,'A100001.jpg','2016-07-16 23:08:41',NULL,'1',1);
/*!40000 ALTER TABLE `t_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sku_more`
--

DROP TABLE IF EXISTS `t_sku_more`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sku_more` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL COMMENT 't_sku_clothing表的id',
  `size` varchar(50) DEFAULT NULL COMMENT '取值为t_sku_dict表的SIZE_STANDARD相关item',
  `count` int(11) unsigned NOT NULL COMMENT '数量',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品SKU更多表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sku_more`
--

LOCK TABLES `t_sku_more` WRITE;
/*!40000 ALTER TABLE `t_sku_more` DISABLE KEYS */;
INSERT INTO `t_sku_more` VALUES (1,1,'S',10,'备注备注备注'),(2,1,'M',9,'备注备注备注'),(3,1,'L',10,'备注备注备注'),(4,1,'XL',10,'备注备注备注');
/*!40000 ALTER TABLE `t_sku_more` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sku_prop_type`
--

DROP TABLE IF EXISTS `t_sku_prop_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sku_prop_type` (
  `cate_code` varchar(20) NOT NULL COMMENT 'cate_code&item&name联合保持唯一性',
  `item` varchar(50) DEFAULT NULL COMMENT '项目',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `title` varchar(50) DEFAULT NULL,
  `evalue` varchar(50) DEFAULT NULL COMMENT '值',
  `cvalue` varchar(50) DEFAULT NULL COMMENT '描述',
  `name_order` tinyint(4) DEFAULT NULL COMMENT 'name排序',
  `value_order` tinyint(4) DEFAULT NULL COMMENT 'value排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU扩展属性-type属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sku_prop_type`
--

LOCK TABLES `t_sku_prop_type` WRITE;
/*!40000 ALTER TABLE `t_sku_prop_type` DISABLE KEYS */;
INSERT INTO `t_sku_prop_type` VALUES ('S-A','SIZE_STANDARD','HEIGHT','身高','50','50cm',1,1),('S-A','SIZE_STANDARD','HEIGHT','身高','55','55cm',1,2),('S-A','SIZE_STANDARD','HEIGHT','身高','60','60cm',1,3),('S-A','SIZE_STANDARD','HEIGHT','身高','65','65cm',1,4),('S-A','SIZE_STANDARD','HEIGHT','身高','70','70cm',1,5),('S-A','SIZE_STANDARD','HEIGHT','身高','75','75cm',1,6),('S-A','SIZE_STANDARD','HEIGHT','身高','80','80cm',1,7),('S-A','SIZE_STANDARD','HEIGHT','身高','85','85cm',1,8),('S-A','SIZE_STANDARD','HEIGHT','身高','90','90cm',1,9),('S-A','SIZE_STANDARD','HEIGHT','身高','95','95cm',1,10),('S-A','SIZE_STANDARD','HEIGHT','身高','100','100cm',1,11),('S-A','SIZE_STANDARD','HEIGHT','身高','110','110cm',1,12),('S-A','SIZE_STANDARD','HEIGHT','身高','120','120cm',1,13),('S-A','SIZE_STANDARD','HEIGHT','身高','130','130cm',1,14),('S-A','SIZE_STANDARD','HEIGHT','身高','140','140cm',1,15),('S-A','SIZE_STANDARD','HEIGHT','身高','150','150cm',1,16),('S-A','SIZE_STANDARD','HEIGHT','身高','160','160cm',1,17),('S-A','SIZE_STANDARD','AGE','年龄','1-2','1-2岁',2,1),('S-A','SIZE_STANDARD','AGE','年龄','2-3','2-3岁',2,2),('S-A','SIZE_STANDARD','AGE','年龄','3-4','3-4岁',2,3),('S-A','SIZE_STANDARD','AGE','年龄','4-5','4-5岁',2,4),('S-A','SIZE_STANDARD','AGE','年龄','5-6','5-6岁',2,5),('S-A','SIZE_STANDARD','AGE','年龄','6-7','6-7岁',2,6),('S-A','SIZE_STANDARD','AGE','年龄','7-8','7-8岁',2,7),('S-A','SIZE_STANDARD','AGE','年龄','8-9','8-9岁',2,8),('S-A','SIZE_STANDARD','AGE','年龄','9-10','9-10岁',2,9),('S-A','SIZE_STANDARD','AGE','年龄','10-11','10-11岁',2,10),('S-A','SIZE_STANDARD','AGE','年龄','11-12','11-12岁',2,11),('S-A','SIZE_STANDARD','SIZE','尺码','S','小号',3,1),('S-A','SIZE_STANDARD','SIZE','尺码','M','中号',3,2),('S-A','SIZE_STANDARD','SIZE','尺码','L','大号',3,3),('S-A','SIZE_STANDARD','SIZE','尺码','XL','特大号',3,4),('S-B','SIZE_STANDARD','HEIGHT','身高','50','50cm',1,1),('S-B','SIZE_STANDARD','HEIGHT','身高','55','55cm',1,2),('S-B','SIZE_STANDARD','HEIGHT','身高','60','60cm',1,3),('S-B','SIZE_STANDARD','HEIGHT','身高','65','65cm',1,4),('S-B','SIZE_STANDARD','HEIGHT','身高','70','70cm',1,5),('S-B','SIZE_STANDARD','HEIGHT','身高','75','75cm',1,6),('S-B','SIZE_STANDARD','HEIGHT','身高','80','80cm',1,7),('S-B','SIZE_STANDARD','HEIGHT','身高','85','85cm',1,8),('S-B','SIZE_STANDARD','HEIGHT','身高','90','90cm',1,9),('S-B','SIZE_STANDARD','HEIGHT','身高','95','95cm',1,10),('S-B','SIZE_STANDARD','HEIGHT','身高','100','100cm',1,11),('S-B','SIZE_STANDARD','HEIGHT','身高','110','110cm',1,12),('S-B','SIZE_STANDARD','HEIGHT','身高','120','120cm',1,13),('S-B','SIZE_STANDARD','HEIGHT','身高','130','130cm',1,14),('S-B','SIZE_STANDARD','HEIGHT','身高','140','140cm',1,15),('S-B','SIZE_STANDARD','HEIGHT','身高','150','150cm',1,16),('S-B','SIZE_STANDARD','HEIGHT','身高','160','160cm',1,17),('S-B','SIZE_STANDARD','AGE','年龄','1-2','1-2岁',2,1),('S-B','SIZE_STANDARD','AGE','年龄','2-3','2-3岁',2,2),('S-B','SIZE_STANDARD','AGE','年龄','3-4','3-4岁',2,3),('S-B','SIZE_STANDARD','AGE','年龄','4-5','4-5岁',2,4),('S-B','SIZE_STANDARD','AGE','年龄','5-6','5-6岁',2,5),('S-B','SIZE_STANDARD','AGE','年龄','6-7','6-7岁',2,6),('S-B','SIZE_STANDARD','AGE','年龄','7-8','7-8岁',2,7),('S-B','SIZE_STANDARD','AGE','年龄','8-9','8-9岁',2,8),('S-B','SIZE_STANDARD','AGE','年龄','9-10','9-10岁',2,9),('S-B','SIZE_STANDARD','AGE','年龄','10-11','10-11岁',2,10),('S-B','SIZE_STANDARD','AGE','年龄','11-12','11-12岁',2,11),('S-B','SIZE_STANDARD','SIZE','尺码','S','小号',3,1),('S-B','SIZE_STANDARD','SIZE','尺码','M','中号',3,2),('S-B','SIZE_STANDARD','SIZE','尺码','L','大号',3,3),('S-B','SIZE_STANDARD','SIZE','尺码','XL','特大号',3,4),('S-C','SIZE_STANDARD','HEIGHT','身高','50','50cm',1,1),('S-C','SIZE_STANDARD','HEIGHT','身高','55','55cm',1,2),('S-C','SIZE_STANDARD','HEIGHT','身高','60','60cm',1,3),('S-C','SIZE_STANDARD','HEIGHT','身高','65','65cm',1,4),('S-C','SIZE_STANDARD','HEIGHT','身高','70','70cm',1,5),('S-C','SIZE_STANDARD','HEIGHT','身高','75','75cm',1,6),('S-C','SIZE_STANDARD','HEIGHT','身高','80','80cm',1,7),('S-C','SIZE_STANDARD','HEIGHT','身高','85','85cm',1,8),('S-C','SIZE_STANDARD','HEIGHT','身高','90','90cm',1,9),('S-C','SIZE_STANDARD','HEIGHT','身高','95','95cm',1,10),('S-C','SIZE_STANDARD','HEIGHT','身高','100','100cm',1,11),('S-C','SIZE_STANDARD','HEIGHT','身高','110','110cm',1,12),('S-C','SIZE_STANDARD','HEIGHT','身高','120','120cm',1,13),('S-C','SIZE_STANDARD','HEIGHT','身高','130','130cm',1,14),('S-C','SIZE_STANDARD','HEIGHT','身高','140','140cm',1,15),('S-C','SIZE_STANDARD','HEIGHT','身高','150','150cm',1,16),('S-C','SIZE_STANDARD','HEIGHT','身高','160','160cm',1,17),('S-C','SIZE_STANDARD','SIZE','尺码','S','小号',2,1),('S-C','SIZE_STANDARD','SIZE','尺码','M','中号',2,2),('S-C','SIZE_STANDARD','SIZE','尺码','L','大号',2,3),('S-C','SIZE_STANDARD','SIZE','尺码','XL','特大号',2,4),('S-D','SIZE_STANDARD','LENGTH','长度','12','12cm',1,1),('S-D','SIZE_STANDARD','LENGTH','长度','13','13cm',1,2),('S-D','SIZE_STANDARD','LENGTH','长度','14','14cm',1,3),('S-D','SIZE_STANDARD','LENGTH','长度','15','15cm',1,4),('S-D','SIZE_STANDARD','LENGTH','长度','16','16cm',1,5),('S-D','SIZE_STANDARD','LENGTH','长度','17','17cm',1,6),('S-D','SIZE_STANDARD','LENGTH','长度','18','18cm',1,7),('S-D','SIZE_STANDARD','LENGTH','长度','19','19cm',1,8),('S-D','SIZE_STANDARD','LENGTH','长度','20','20cm',1,9),('S-D','SIZE_STANDARD','LENGTH','长度','21','21cm',1,10),('S-D','SIZE_STANDARD','LENGTH','长度','22','22cm',1,11),('S-D','SIZE_STANDARD','LENGTH','长度','23','23cm',1,12),('S-D','SIZE_STANDARD','LENGTH','长度','24','24cm',1,13),('S-D','SIZE_STANDARD','LENGTH','长度','25','25cm',1,14),('S-D','SIZE_STANDARD','LENGTH','长度','26','26cm',1,15),('S-D','SIZE_STANDARD','LENGTH','长度','27','27cm',1,16),('S-D','SIZE_STANDARD','LENGTH','长度','28','28cm',1,17),('S-D','SIZE_STANDARD','LENGTH','长度','29','29cm',1,18),('S-D','SIZE_STANDARD','LENGTH','长度','30','30cm',1,19),('S-D','SIZE_STANDARD','LENGTH','长度','31','31cm',1,20),('S-D','SIZE_STANDARD','LENGTH','长度','32','32cm',1,21),('S-E','SIZE_STANDARD','AGE','年龄','0-3','0-3岁',1,1),('S-E','SIZE_STANDARD','AGE','年龄','3-6','3-6岁',1,2),('S-E','SIZE_STANDARD','AGE','年龄','6-12','6-12岁',1,3),('S-E','SIZE_STANDARD','AGE','年龄','12+','12岁以上',1,4),('S-F','SIZE_STANDARD','DEFAULT','默认分类','blabla','blabla',1,1),('S-G','SIZE_STANDARD','DEFAULT','默认分类','blabla','blabla',1,1),('S-H','SIZE_STANDARD','DEFAULT','默认分类','blabla','blabla',1,1),('S-I','SIZE_STANDARD','DEFAULT','默认分类','blabla','blabla',1,1),('S-J','SIZE_STANDARD','SIZE','尺码','NB','NB',1,1),('S-J','SIZE_STANDARD','SIZE','尺码','S','S',1,2),('S-J','SIZE_STANDARD','SIZE','尺码','M','M',1,3),('S-J','SIZE_STANDARD','SIZE','尺码','L','L',1,4),('S-J','SIZE_STANDARD','SIZE','尺码','XL','XL',1,5),('S-J','SIZE_STANDARD','SIZE','尺码','XXL','XXL',1,6),('S-K','SIZE_STANDARD','DEFAULT','默认分类','blabla','blabla',1,1);
/*!40000 ALTER TABLE `t_sku_prop_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'test','91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203',0,'1','2015-09-12 17:28:48','2015-09-12 17:28:48');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_permission`
--

DROP TABLE IF EXISTS `t_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_permission` (
  `u_id` bigint(64) DEFAULT NULL,
  `permission_id` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_permission`
--

LOCK TABLES `t_user_permission` WRITE;
/*!40000 ALTER TABLE `t_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `u_id` bigint(64) DEFAULT NULL,
  `role_id` bigint(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_cart`
--

DROP TABLE IF EXISTS `v_cart`;
/*!50001 DROP VIEW IF EXISTS `v_cart`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_cart` AS SELECT 
 1 AS `id`,
 1 AS `user_id`,
 1 AS `cust_id`,
 1 AS `cid`,
 1 AS `name`,
 1 AS `msisdn`,
 1 AS `payment_clothing`,
 1 AS `gname`,
 1 AS `cate_code`,
 1 AS `sid`,
 1 AS `img`,
 1 AS `model`,
 1 AS `sprice`,
 1 AS `sku_more_id`,
 1 AS `size`,
 1 AS `scount`,
 1 AS `count`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_sku_more`
--

DROP TABLE IF EXISTS `v_sku_more`;
/*!50001 DROP VIEW IF EXISTS `v_sku_more`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_sku_more` AS SELECT 
 1 AS `skuId`,
 1 AS `skumoreId`,
 1 AS `goodsId`,
 1 AS `gname`,
 1 AS `cateCode`,
 1 AS `sid`,
 1 AS `model`,
 1 AS `ptime`,
 1 AS `pprice`,
 1 AS `sprice`,
 1 AS `img`,
 1 AS `state`,
 1 AS `size`,
 1 AS `count`,
 1 AS `remark`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'mz-g'
--
/*!50003 DROP FUNCTION IF EXISTS `currval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
         DECLARE value INTEGER;
         SET value = 0;
         SELECT current_value INTO value
                   FROM t_sequence
                   WHERE name = seq_name;
         RETURN value;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
         UPDATE t_sequence
                   SET current_value = current_value + increment
                   WHERE name = seq_name;
         RETURN currval(seq_name);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `setval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `setval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
   UPDATE t_sequence
   SET current_value = value
   WHERE name = seq_name;
   RETURN currval(seq_name);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `v_cart`
--

/*!50001 DROP VIEW IF EXISTS `v_cart`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_cart` AS select `ca`.`id` AS `id`,`ca`.`user_id` AS `user_id`,`cu`.`id` AS `cust_id`,`cu`.`cid` AS `cid`,`cu`.`name` AS `name`,`cu`.`msisdn` AS `msisdn`,`cu`.`payment_clothing` AS `payment_clothing`,`g`.`gname` AS `gname`,`g`.`cate_code` AS `cate_code`,`sku`.`sid` AS `sid`,`sku`.`img` AS `img`,`sku`.`model` AS `model`,`sku`.`sprice` AS `sprice`,`sm`.`id` AS `sku_more_id`,`sm`.`size` AS `size`,`sm`.`count` AS `scount`,`ca`.`count` AS `count` from ((((`t_cart` `ca` join `t_sku_more` `sm` on((`sm`.`id` = `ca`.`sku_more_id`))) join `t_sku` `sku` on((`sku`.`id` = `sm`.`sku_id`))) join `t_goods` `g` on((`g`.`id` = `sku`.`goods_id`))) join `t_customer` `cu` on((`cu`.`id` = `ca`.`cust_id`))) where ((`cu`.`state` = 1) and (`g`.`state` = 1) and (`sku`.`state` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_sku_more`
--

/*!50001 DROP VIEW IF EXISTS `v_sku_more`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_sku_more` AS select `t1`.`id` AS `skuId`,`t`.`id` AS `skumoreId`,`t2`.`id` AS `goodsId`,`t2`.`gname` AS `gname`,`t2`.`cate_code` AS `cateCode`,`t1`.`sid` AS `sid`,`t1`.`model` AS `model`,`t1`.`ptime` AS `ptime`,`t1`.`pprice` AS `pprice`,`t1`.`sprice` AS `sprice`,`t1`.`img` AS `img`,`t1`.`state` AS `state`,`t`.`size` AS `size`,`t`.`count` AS `count`,`t`.`remark` AS `remark` from ((`t_sku_more` `t` join `t_sku` `t1` on((`t1`.`id` = `t`.`sku_id`))) join `t_goods` `t2` on((`t2`.`id` = `t1`.`goods_id`))) where (`t1`.`state` <> 0) order by `t2`.`id`,`t`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-17 22:09:41
