/* MySql中所有View的select语句 */

-- v_cart
select ca.user_id as user_id, cu.id as cust_id, cu.cid as cid, cu.name, cu.msisdn, cu.payment_clothing
      ,g.gname as gname,g.cate_code as cate_code
      ,sku.sid as sid,sku.img as img,sku.model as model,sku.sprice as sprice
      ,sm.id as sku_more_id,sm.size as size,sm.count as scount,ca.count as count

from t_cart ca
inner join t_sku_more sm on sm.id = ca.sku_more_id
inner join t_sku sku on sku.id = sm.sku_id
inner join t_goods g on g.id = sku.goods_id
inner join t_customer cu on cu.id = ca.cust_id
where cu.state = 1 and g.state = 1 and sku.state = 1;




-- v_sku_more
select t1.id as skuId,t.id as skumoreId,t2.id as goodsId,t2.gname as gname, t2.cate_code as cateCode
      ,t1.sid as sid,t1.model as model,t1.ptime as ptime,t1.pprice as pprice,t1.sprice as sprice
      ,t1.img as img,t1.state as state,t.size as size,t.count as count,t.remark as remark
from t_sku_more t
inner join t_sku t1 on t1.id = t.sku_id
inner join t_goods t2 on t2.id = t1.goods_id
where t1.state <> 0
order by t2.id,t.id;