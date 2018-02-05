package com.mr.goods.mapper;

import java.util.List;

import com.mr.goods.entity.Goods;
import com.mr.util.Page;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

	List<Goods> queryGoodsList(Page pageUtil);

	int queryGoodsCount();
}