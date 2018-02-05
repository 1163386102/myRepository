package com.mr.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mr.goods.entity.Goods;
import com.mr.goods.service.GoodsService;
import com.mr.goodstype.entity.GoodsType;
import com.mr.goodstype.service.GoodsTypeService;
import com.mr.user.entity.User;
import com.mr.util.Page;
import com.mr.util.RedisUtil;

@Controller
@RequestMapping(value="goods")
public class GoodsController {
@Autowired
private GoodsService goodsService;
@Autowired
private GoodsTypeService typeService;

@RequestMapping(value="queryGoodsList")
@ResponseBody
public Map queryGoodsList(int page,int rows,Page pageUtil){ //分页查询的方法
	pageUtil.setCurrentPage(page);
	pageUtil.setPageItem(rows);
	goodsService.queryGoodsList(pageUtil);
	
	Map map=new HashMap();
	map.put("total", pageUtil.getCountItem());
	map.put("rows", pageUtil.getList());
	return map;
}

//新增的方法
@ResponseBody
@RequestMapping(value="insertGoods")
public void insertGoods(Goods goods){
	if(goods.getGoodsTypeId()==-1){
		goods.setGoodsTypeId(null);
	}
	goodsService.insertGoods(goods);
}
//跳转到修改页面的方法
@RequestMapping(value="toUpdatePage")
public String toUpdatePage(ModelMap map,Goods goods){
	//根据id查询对象 返回到回显的页面
	Goods returnGoods=goodsService.queryGoodsById(goods.getGoodsId());
	map.put("goods", returnGoods);
		//从redis里面查询typeList
		List<GoodsType> typeList = typeService.queryGoodsTypeList();
	map.put("typeList", typeList);
	return "goods/update_page";
}
	
//删除的方法
@RequestMapping(value="deleteGoods")
@ResponseBody
public void deleteGoods(Integer goodsId){
	goodsService.deleteGoods(goodsId);
}

//修改的方法
@RequestMapping(value="updateGoods")
@ResponseBody
 public void updateGoods (Goods goods){
	goodsService.updateGoods(goods);
	 
 }
//跳转到商品详情页面的方法
@RequestMapping(value="toInfoPage")
public String toInfoPage( Integer goodsId,ModelMap map){
	//调用工具类从redis中取出相对应的商品的详细信息 
	Goods goods = (Goods) RedisUtil.getObjectFromRedis("goodsList_"+goodsId);
	map.put("goods", goods);
	//调用工具类从redis中取出商品类型的信息 展示到页面中
	List<GoodsType> typeList = (List<GoodsType>) RedisUtil.getObjectFromRedis("typeList");
	for (GoodsType goodsType : typeList) {
		if(goodsType.getGoodsTypeId().equals(goods.getGoodsTypeId())){
			map.put("goodsTypeName", goodsType.getGoodsTypeName());
		}
	}
	return "goods/info_page";
}

//加入购物车的方法
@RequestMapping(value="insertBuyCarSuccess")
public String insertBuyCarSuccess(Integer buyCount,Integer goodsId,ModelMap map,HttpSession session){
	//调用service层将商品信息以及购买数量放到redis缓存中
	goodsService.saveGoodsCarToRedis(buyCount,goodsId,session);
	Goods returnGoods = (Goods) RedisUtil.getObjectFromRedis("goodsList_"+goodsId);
	map.put("goods", returnGoods);
	map.put("count", buyCount);
	return "goods/success";
}

//查看购物车的方法
@RequestMapping(value="showBuyCar")
public String showBuyCar(ModelMap map,HttpSession session){
	User user = (User) RedisUtil.getObjectFromRedis(session.getId());
	List<Map<String, Object>> list = (List<Map<String, Object>>) RedisUtil.getObjectFromRedis("buyCarList_"+user.getUserId());
	map.put("list", list);
	return "goods/showBuyCar";
	
}

}
