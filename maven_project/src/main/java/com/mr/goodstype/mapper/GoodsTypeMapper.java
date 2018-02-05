package com.mr.goodstype.mapper;

import java.util.List;

import com.mr.goodstype.entity.GoodsType;

public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer goodsTypeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer goodsTypeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
    List<GoodsType> queryGoodsTypeList();

}