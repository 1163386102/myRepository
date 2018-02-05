package com.mr.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	private static  Jedis jedis=new Jedis("localhost");
	/**
	 * @param key 需要的参数key
	 * @return 返回存放在redis里面的对象
	 */
	public static Object getObjectFromRedis(String key ){
		byte[] bs = jedis.get(key.getBytes());
		Object returnObject=null;
		if(bs!=null){
			//调用饭序列化工具
			 returnObject = SerializableUtil.deSerialize(bs);
		}
		return returnObject;
	}
	
	/**
	 * 保存对象到redis缓存
	 * @param object 要保存的对象
	 */
	public static void saveToRedis(String key ,Object object){
		jedis.set(key.getBytes(), SerializableUtil.serialize(object));
	}
	
	/** 设置对象的生命周期
	 * @param key 放到redis 里面的key
	 * @param time 想要设置的时间(分钟数)
	 */
	public static void setObjectLife(String key ,Integer time){
		jedis.expire(key, time*60);
	}
	
	 /** redis中删除一个对象的方法
	 * @param key
	 */
	public static void delObjectFromRedis(String key){
			jedis.del(key.getBytes());
	 }
}
