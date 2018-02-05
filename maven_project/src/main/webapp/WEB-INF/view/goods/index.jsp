<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
	<h2>欢迎${user.userRealName}进入购物网站</h2>
<br>
<a target="_blank" href="${pageContext.request.contextPath }/goods/showBuyCar.do">查看我的购物车</a>
<table id="dg"></table>  
<div id="dd"></div>  


</body>
<script type="text/javascript">
		$('#dg').datagrid({    
		    url:'<%=request.getContextPath()%>/goods/queryGoodsList.do',    
		    columns:[[    
		        {field:'goodsId',title:'商品编号',width:100,align:'center'},    
		        {field:'goodsName',title:'商品名称',width:100, align:'center',
			        	formatter: function(value,row,index){
			        		//console.info("<a href='${pageContext.request.contextPath}/goods/infoPage.do?goodsId="+row.goodsId+"'>"+value+"</a>")
							return "<a target='_blank' style='text-decoration:none ' href='${pageContext.request.contextPath}/goods/toInfoPage.do?goodsId="+row.goodsId+"'>"+value+"</a>"
						}

		        },  
		        
		        {field:'goodsPrice',title:'商品价格',width:100,align:'center'} ,   
		        {field:'goodsCount',title:'商品库存',width:100,align:'center'},    
		        {field:'goodsDateStr',title:'生产时间',width:100,align:'center'}    
		    ]],
		    pagination:true,
		    singleSelect:true,
		    toolbar: [{
				iconCls: 'icon-add',
				handler: function(){
					$('#dd').dialog({    
					    title: '新增',    
					    width: 500,    
					    height: 300,    
					    closed: false,    
					    cache: false,    
					    href: '<%=request.getContextPath()%>/goodsType/toInsertPage.do',    
					    modal: true ,
					    buttons:[{
							text:'保存',
							handler:function(){
									//调用保存的方法
									insertGoods();
							}
						},{
							text:'关闭',
							handler:function(){
								$('#dd').dialog("close");
								$('#dg').datagrid("reload")
							}
						}]

					});    
				}
			},'-',{
				iconCls: 'icon-edit',
				handler: function(){
					//先获取被选择的对象的id
					var goodsId=$('#dg').datagrid("getSelected").goodsId;
					
					$('#dd').dialog({    
					    title: '修改',    
					    width: 500,    
					    height: 300,    
					    closed: false,    
					    cache: false,    
					    href: '<%=request.getContextPath()%>/goods/toUpdatePage.do?goodsId='+goodsId,    
					    modal: true ,
					    buttons:[{
							text:'修改',
							handler:function(){
									//调用修改的方法
									updateGoods(goodsId);
							}
						},{
							text:'取消',
							handler:function(){
								$('#dd').dialog("close");
								$('#dg').datagrid("reload")
							}
						}]

					});    
				}
			},'-',{
				iconCls: 'icon-remove',
				handler: function(){
					//获得选中的记录的id
					var goodsId=$('#dg').datagrid("getSelected").goodsId;
					//弹出确认的信息，确认之后进行删除的 操作
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){    //确定之后进行调用删除的方法
					          deleteGoods(goodsId);
					    }else{
					    	$.messager.alert('警告','警告不要乱点');    
					    	$('#dg').datagrid("reload")
					    }
					    
					});  
					
					
				}
			},'-',{
				iconCls: 'icon-help',
				handler: function(){alert('帮助按钮')}
			}]

		});  

function insertGoods(){ //新增的方法
		$.ajax({
	        url: "<%=request.getContextPath()%>/goods/insertGoods.do",
	        type:"post",
	        data:$("#add_form_id").serialize(),//表单序列化提交
	        dataType:"text",//规定返回值格式
	      	 async:false,//同步上传
	        success:function (data){//成功回调函数
	       	 $.messager.alert('提示','新增成功！'); 
	       	 $('#dd').dialog("close");
				$('#dg').datagrid("reload")
	        },
	        error :function(){//错误回调函数
	            alert("系统错误,请联系管理员")
	        }
		});
}

function deleteGoods(goodsId){
	$.ajax({
        url: "<%=request.getContextPath()%>/goods/deleteGoods.do?goodsId="+goodsId,
        type:"post",
       	// data:$("#add_form_id").serialize(),//表单序列化提交
        dataType:"text",//规定返回值格式
      	 async:false,//同步上传
        success:function (data){//成功回调函数
       	 $.messager.alert('提示','删除成功！'); 
			$('#dg').datagrid("reload")
        },
        error :function(){//错误回调函数
            alert("系统错误,请联系管理员")
        }
	});
}
	//修改的方法
	function updateGoods(goodsId){
		$.ajax({
	        url: "<%=request.getContextPath()%>/goods/updateGoods.do",
	        type:"post",
	        data:$("#update_form_id").serialize(),//表单序列化提交
	        dataType:"text",//规定返回值格式
	      	 async:false,//同步上传
	        success:function (data){//成功回调函数
	       	 	$.messager.alert('提示','修改成功！'); 
	       	 	$('#dd').dialog("close");
				$('#dg').datagrid("reload")
	        },
	        error :function(){//错误回调函数
	            alert("系统错误,请联系管理员")
	        }
		});
	}


	



	
</script>
</html>