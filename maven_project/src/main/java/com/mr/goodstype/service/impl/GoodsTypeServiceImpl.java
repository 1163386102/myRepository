package com.mr.goodstype.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.goodstype.entity.GoodsType;
import com.mr.goodstype.mapper.GoodsTypeMapper;
import com.mr.goodstype.service.GoodsTypeService;
import com.mr.util.SerializableUtil;

import redis.clients.jedis.Jedis;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Override
	public List<GoodsType> queryGoodsTypeList() {
		//连接redis
		Jedis jedis=new Jedis("localhost");
		System.out.println("连接redis服务的连接状态："+jedis.ping());
		//先判断redis缓存中是否有想要查询的数据
		if(jedis.get("typeList".getBytes())==null){ //如果为空 则到数据库中进行查询并将查询的结果放到redis中
			
			//调用到数据库查询商品类型的方法
			List<GoodsType> typeList = goodsTypeMapper.queryGoodsTypeList();
			//查询完了之后将这个查询的结果放到redis服务器（缓存里面）
			jedis.set("typeList".getBytes(), SerializableUtil.serialize(typeList));//将typeList进行序列化之后放到redis数据库中
		}
		
		//如果不为空则直接反序列化之后 返回即可
		return (List<GoodsType>) SerializableUtil.deSerialize(jedis.get("typeList".getBytes()));//返回反序列化后的list
		
	}

}
