package com.mr.goodstype.entity;

import java.io.Serializable;

public class GoodsType implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3413993720154357376L;

	private Integer goodsTypeId;

    private String goodsTypeName;

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName == null ? null : goodsTypeName.trim();
    }
}