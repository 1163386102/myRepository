package com.mr.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.goods.entity.Goods;
import com.mr.goods.mapper.GoodsMapper;
import com.mr.goods.service.GoodsService;
import com.mr.user.entity.User;
import com.mr.util.Page;
import com.mr.util.RedisUtil;
import com.mr.util.SerializableUtil;

import redis.clients.jedis.Jedis;
@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	//连接redis服务
	Jedis jedis=new Jedis("localhost");
	public void  queryGoodsList(Page pageUtil) {
		//查询总条数
		int count=goodsMapper.queryGoodsCount();
		pageUtil.setCountItem(count);
		//分页查询集合
		List<Goods>list=goodsMapper.queryGoodsList(pageUtil); //使用redis缓存的时候 只查询 数据库中记录的id的 集合
		for (int i = 0; i < list.size(); i++) { //遍历从数据库中获得的id 集合 然后根据id 到redis缓存中进行查询
			byte[] bs = jedis.get(("goodsList_"+list.get(i).getGoodsId()).getBytes());
			Goods goods = (Goods) SerializableUtil.deSerialize(bs);
			list.set(i, goods); //将从redis中获取到的对象重新放到list 中
			System.out.println("世界你好");
		}
		pageUtil.setList(list);
		
		
//		//判断redis里面是否有需要的数据
//		if(jedis.get("goodsList".getBytes())==null){
//			jedis.set("goodsList".getBytes(), SerializableUtil.serialize(list));
//		}
//		
//		//获得reids里面的list
//		List<Goods> returnList = (List<Goods>) SerializableUtil.deSerialize(jedis.get("goodsList".getBytes()));
		
	}

	@Override
	public void insertGoods(Goods goods) { //新增商品的方法
		goodsMapper.insert(goods);
		jedis.set(("goodsList_"+goods.getGoodsId()).getBytes(), SerializableUtil.serialize(goods));
		byte[] bs = jedis.get(("goodsList_"+goods.getGoodsId()).getBytes());
		Goods goodsFromRedis =(Goods) SerializableUtil.deSerialize(bs);
		System.out.println(goodsFromRedis);
		
	}

	@Override
	public Goods queryGoodsById(Integer goodsId) {
		//先判断redis里面是否有这条数据   如果有就从这里取 如果没有再到数据库中取；
		byte[] bs = jedis.get(("goodsList_"+goodsId).getBytes());
		Goods goods=null;
		if(bs!=null){
			
			goods =(Goods) SerializableUtil.deSerialize(bs);
		}else{
			goods=goodsMapper.selectByPrimaryKey(goodsId);
		}
		
		return goods;
		
	}

	@Override
	public void deleteGoods(Integer goodsId) {
		//先从数据库中删除这条记录
		goodsMapper.deleteByPrimaryKey(goodsId);
		//将本条记录从redis中移除
		//先判断redis里面是否有这条数据   如果有就从这里取 如果没有再到数据库中取；
				byte[] bs = jedis.get(("goodsList_"+goodsId).getBytes());
				
				if(bs!=null){
					//如果不等于空则将这条数据从redis中移除
					jedis.del(("goodsList_"+goodsId).getBytes());
				}
		
	}

	@Override
	public void updateGoods(Goods goods) {
		//先到数据库修改本条数据
		goodsMapper.updateByPrimaryKey(goods);
		//将修该的这条对象重新放到redis里面（如果有直接覆盖了之前放置的）
		jedis.set(("goodsList_"+goods.getGoodsId()).getBytes(), SerializableUtil.serialize(goods));
		
		
		
	}

	@Override
	public List<Map<String, Object>> saveGoodsCarToRedis(Integer buyCount, Integer goodsId,HttpSession session) {
		/**
		 * 将每个人加入到购物车的商品信息放到 一个list中 这个list就相当于一个人的购物车
			将个人的购物车 放到redis缓存中 ，使用个人的唯一标示 作为购物车list的key 放到缓存中 
		 */
		//到redis数据库中查询指定的购物车信息（一般把个人的唯一标示 如 用户的编号当作key）
		User loginUser = (User) RedisUtil.getObjectFromRedis(session.getId());
		Goods returnGoods = (Goods) RedisUtil.getObjectFromRedis("goodsList_"+goodsId);
		List<Map<String, Object>> returnBuyCarList = (List<Map<String, Object>>) RedisUtil.getObjectFromRedis("buyCarList_"+loginUser.getUserId());
		//判断returnList 是否为空（如果为空也就说名用户还没有王购物车中放入想要购买的商品的信息）
		if(returnBuyCarList==null){
			//如果为空则需要new 一个list 用来存放 商品的信息
			List<Map<String, Object>> buyCarList=new ArrayList<Map<String, Object>>();
			//new 一个map 用来存放单条 商品的信息
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("goods", returnGoods);
			map.put("count", buyCount);
			buyCarList.add(map);
			RedisUtil.saveToRedis("buyCarList_"+loginUser.getUserId(), buyCarList);
			return buyCarList;
		}else{
			//如果 已经存在购物车 则需要 将页面传递过来的想要加入购物车的商品放到购物车list中再重新保存到redis中
			//如果有购物车了，那么就要判断本次添加的商品是否 已经在购物车中存在，如果存在则累加购买数量
			int flag=0;
			for (Map<String, Object> map : returnBuyCarList) {
				Goods mapGoods = (Goods) map.get("goods");
				if(mapGoods.getGoodsId().equals(goodsId)){
					//通过商品的id进行判断 购物车中是否已经有了相同的商品，如果有则累加更新
					Integer mapCount = (Integer) map.get("count");
					mapCount=mapCount+buyCount;
					map.put("count", mapCount);  //使用foreach 要注意
				}else{
					flag=flag+1;
				}
			}
			if(flag==returnBuyCarList.size()){ //如果不一样的次数等于购物车的长度 说明没有一个一样的
				//如果购物车中没有这种商品，则需要new 一个新的map 将商品增加到购物车list中 
				Map<String,Object> map2=new HashMap<String,Object>();
				map2.put("goods", returnGoods);
				map2.put("count", buyCount);
				returnBuyCarList.add(map2);
				
			}
			RedisUtil.saveToRedis("buyCarList_"+loginUser.getUserId(), returnBuyCarList);
			return returnBuyCarList;
		}
	}
}
