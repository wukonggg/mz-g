-- 查询所有有效服装类商品的所有有效SKU
select t1.id as 'goods_id', t1.cate_code, t1.gname
     , t.id as 'sku_clothing_id', t.model, t.img, t.pprice
     , t2.size, t2.count
from t_sku_clothing t
inner join t_goods t1 on t1.id = t.goods_id
inner join t_sku_clothing_more t2 on t2.sku_clothing_id = t.id
where t.state = 1 and t1.state = 1
and t.id = 2;


SELECT t.cate_code, t.item, t.name, t.title, t.evalue, t.cvalue, t.name_order, t.value_order
FROM t_sku_dict t
WHERE t.cate_code='91000' AND t.item='SIZE_STANDARD' AND t.name='DEFAULT'
ORDER BY t.name_order;