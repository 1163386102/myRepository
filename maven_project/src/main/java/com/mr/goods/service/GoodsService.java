package com.mr.goods.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.mr.goods.entity.Goods;
import com.mr.util.Page;

public interface GoodsService {

	void  queryGoodsList(Page pageUtil);

	void insertGoods(Goods goods);

	Goods queryGoodsById(Integer goodsId);

	void deleteGoods(Integer goodsId);

	void updateGoods(Goods goods);

	List<Map<String, Object>> saveGoodsCarToRedis(Integer buyCount, Integer goodsId, HttpSession session);

}
