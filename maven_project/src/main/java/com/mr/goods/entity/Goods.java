package com.mr.goods.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Goods implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5896720867562713600L;

	private Integer goodsId;

    private String goodsName;

    private Integer goodsTypeId;

    private Double goodsPrice;

    private Integer goodsCount;

   // @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date goodsDate;
    private String goodsDateStr;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
    }

	public String getGoodsDateStr() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return goodsDate==null?"":sdf.format(goodsDate);
	}

	public void setGoodsDateStr(String goodsDateStr) {
		this.goodsDateStr = goodsDateStr;
	}

	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsTypeId=" + goodsTypeId
				+ ", goodsPrice=" + goodsPrice + ", goodsCount=" + goodsCount + ", goodsDate=" + goodsDate
				+ ", goodsDateStr=" + goodsDateStr + "]";
	}
}