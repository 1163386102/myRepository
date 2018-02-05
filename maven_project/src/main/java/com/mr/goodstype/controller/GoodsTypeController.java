package com.mr.goodstype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mr.goodstype.entity.GoodsType;
import com.mr.goodstype.service.GoodsTypeService;

@Controller
@RequestMapping(value="goodsType")
public class GoodsTypeController {
	@Autowired
	private GoodsTypeService goodsTypeService;
	//跳转到新增也面的方法
	@RequestMapping(value="toInsertPage")
	public String toInsertPage(ModelMap map){
		List<GoodsType>list=goodsTypeService.queryGoodsTypeList();
		map.put("typeList", list);
		return "goods/insert_page";
	}
	
}
