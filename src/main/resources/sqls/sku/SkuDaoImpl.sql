/* sku.list */
select t1.id as 'g.id', t1.cate_code as 'g.cate_code', t1.gname as 'g.gname'
     , t.id as 'sc.id', t.sid as 'sc.sid', t.model as 'sc.model', t.img as 'sc.img', t.pprice as 'sc.pprice', t1.id as 'sc.goods_id'
     , sum(t2.count) as 'sc.count'
from t_sku t
inner join t_goods t1 on t1.id = t.goods_id
inner join t_sku_more t2 on t2.sku_id = t.id
where t.state = @t_state and t1.state = @t1_state
and t1.cate_code like @cate_code
and (t.sid like @qcond or t1.gname like @qcond)
group by t.id
