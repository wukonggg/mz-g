-- 0.6.3a

-- add：goods下架功能。sku的值为0时自动下架。退货时如果库存为0，需要自动上架。
ALTER TABLE `t_goods` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-有效' AFTER `utime`;
ALTER TABLE `t_sku` CHANGE COLUMN `state` `state` CHAR(2) NOT NULL COMMENT '0-已删除, 1-上架, 2-下架' AFTER `utime`;



-- 给customer增加字段
ALTER TABLE `t_customer` ADD COLUMN `ctime` DATETIME NOT NULL COMMENT '创建时间' AFTER `remark`;
ALTER TABLE `t_customer` ADD COLUMN `utime` DATETIME NOT NULL COMMENT '更新时间' AFTER `ctime`;
ALTER TABLE `t_customer` ADD COLUMN `uuid` INT NOT NULL COMMENT 'update user id(t_user)' AFTER `state`;

-- update customer基础数据
update t_customer set ctime=date('2015-11-15 00:00:00');
update t_customer set utime=date('2015-11-15 00:00:00');
update t_customer set uuid = 1;



--支付时不再计算customer的clothingPayment，去除customer表的clothingPayment。
ALTER TABLE `t_customer` DROP COLUMN `payment_clothing`;



-- 重建视图（需使用root用户）
ALTER DEFINER=`mz-gallery-root`@`localhost` VIEW `v_cart` AS select ca.id as id, ca.user_id as user_id, cu.id as cust_id, cu.cid as cid, cu.name, cu.msisdn
      ,g.gname as gname,g.cate_code as cate_code
      ,sku.sid as sid,sku.img as img,sku.model as model,sku.sprice as sprice
      ,sm.id as sku_more_id,sm.size as size,sm.count as scount,ca.count as count
from t_cart ca
inner join t_sku_more sm on sm.id = ca.sku_more_id
inner join t_sku sku on sku.id = sm.sku_id
inner join t_goods g on g.id = sku.goods_id
inner join t_customer cu on cu.id = ca.cust_id
where cu.state = 1 and g.state = 1 and sku.state = 1;

-- 重建v_cart视图（需使用root用户）
ALTER DEFINER=`mz-gallery-root`@`localhost` VIEW `v_sku_more` AS select t1.id as skuId,t.id as skumoreId,t2.id as goodsId,t2.gname as gname, t2.cate_code as cateCode
      ,t1.sid as sid,t1.model as model,t1.type as `type`,t1.ptime as ptime,t1.pprice as pprice,t1.sprice as sprice
      ,t1.img as img,t1.state as state,t.size as size,t.count as count,t.remark as remark
from t_sku_more t
inner join t_sku t1 on t1.id = t.sku_id
inner join t_goods t2 on t2.id = t1.goods_id
where t1.state <> 0
order by t2.id,t.id;
