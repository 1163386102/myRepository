<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这是新增页面</title>
<!-- easyui的使用必须在引用jquery  js的基础上 -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-easyui/jquery.easyui.min.js"></script>
	<!-- 主题、皮肤 -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui/themes/bootstrap/easyui.css">
	<!-- 小图标的css文件 -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-easyui/themes/icon.css">

</head>
<body>
<form id="add_form_id">
<center>
	商品名称：<input class="easyui-textbox"  style="width:200px" name="goodsName"> <br>
	商品价格：<input type="text" class="easyui-numberbox" data-options="min:0,precision:2"  name="goodsPrice"> <br>
	商品类型：
				<select id="cc" class="easyui-combobox" name="goodsTypeId" style="width:200px;">   
			    <option value="-1">--请选择--</option>   
			    <c:forEach items="${typeList }" var="type">
			    	<option value="${type.goodsTypeId }">${type.goodsTypeName }</option>  
			    </c:forEach>
				</select>   <br>

	商品库存：<input class="easyui-textbox"  style="width:200px" name="goodsCount"> <br>
	生产时间：<input  id="dd"  type= "text" class= "easyui-datebox"  name="goodsDate"> <br> 
	
				
					
				</center>

	

	
</form>
</body>
</html>