<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- easyui的使用必须在引用jquery  js的基础上 -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui/jquery.easyui.min.js"></script>
	<!-- 主题、皮肤 -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui/themes/bootstrap/easyui.css">
	<!-- 小图标的css文件 -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui/themes/icon.css">

</head>
<body >
	<table align="center">
			<tr>
				<td rowspan="8" >
						<img src="<%=request.getContextPath()%>/images/tu.jpg" alt="没有图片" width="500px" height="400">
				</td>
				<td>商品编号</td>
				<td>${goods.goodsId}</td>
			</tr>
			<tr>
				<td>商品名称</td>
				<td>${goods.goodsName}</td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td>${goods.goodsPrice}</td>
			</tr>
			<tr>
				<td>商品库存</td>
				<td>${goods.goodsCount}</td>
			</tr>
			<tr>
				<td>商品生产日期</td>
				<td>${goods.goodsDateStr}</td>
			</tr>
			<tr>
				<td>商品类型</td>
				<td>${goodsTypeName}</td>
			</tr>
			<tr>
				<td>购买数量</td>
				<td>
						<input id="ss" class="easyui-numberspinner" style="width:80px;"   
       				 required="required" data-options="min:1,max:${goods.goodsCount},editable:false">  
				</td>
			</tr>
			<tr align="center">
				<td >
					<div style="background-color:orange;color:white ;width:100px" >
							<a style="text-decoration:none " target="_blank" href="javascript:insertBuyCar()">加入购物车</a>
					</div>
				</td>
				<td >
					<div style="background-color:orange;color:white ;width:100px" >
							<a style='text-decoration:none ' href="#">立即购买</a>
					</div>
				</td>
				
			</tr>
	</table>
	
</body>
<script type="text/javascript">
 $(function(){
 $('#ss').numberspinner('setValue', 1);  
 })
 //加入商品到购物车
 function insertBuyCar(){
	 //只需要传递 购买数量 和商品的id到后台
	 var buyCount= $('#ss').numberspinner('getValue');     
	 var goodsId=${goods.goodsId};
	 //一般加入购物车之后显示加入状态  （一般都会新打开一个页面）
	window.open("${pageContext.request.contextPath}/goods/insertBuyCarSuccess.do?goodsId="+goodsId+"&&buyCount="+buyCount)
 }
 
</script>
</html>