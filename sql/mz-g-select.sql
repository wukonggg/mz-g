-- 查询所有有效服装类商品的所有有效SKU
select t1.id as 'goods_id', t1.cate_code, t1.gname
     , t.id as 'sku_clothing_id', t.model, t.img, t.pprice
     , t2.size, t2.count
from t_sku_clothing t
inner join t_goods t1 on t1.id = t.goods_id
inner join t_sku_clothing_more t2 on t2.sku_clothing_id = t.id
where t.state = 1 and t1.state = 1
and t.id = 2;

-- 查询一个skumore的详细信息
select t.sku_id, t.id as sku_more_id,
       t2.gname, t2.cate_code,
       t1.sid, t1.model, t1.`type`, t1.pprice, t1.sprice, t1.state,
       t.size, t.count
from t_sku_more t
inner join t_sku t1 on t1.id = t.sku_id
inner join t_goods t2 on t2.id = t1.goods_id
where t.id = 113;

-- 查询sku_prop_type模板
select t.cate_code, t.item, t.name, t.title, t.evalue, t.cvalue, t.name_order, t.value_order
from t_sku_prop_type t
where t.cate_code='S-1' and t.item='SIZE_STANDARD' and t.name='SIZE'
order by t.name_order;