<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册界面</title>
		<link href="<%=request.getContextPath()%>/js/OA_Login_JS/css/style2.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="<%=request.getContextPath()%>/js/OA_Login_JS/jquery.js"></script>
<%-- 	<script src="<%=request.getContextPath()%>/js/OA_Login_JS/cloud.js" type="text/javascript"></script>  --%>
		
	<script language="javascript">
			$(function(){
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
			$(window).resize(function(){  
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		    })  
		});  
	</script> 
</head>
		<body style="background-color:#1c77ac; background-image:url(<%=request.getContextPath()%>/js/OA_Login_JS/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

			    <div id="mainBody">
			      <div id="cloud1" class="cloud"></div>
			      <div id="cloud2" class="cloud"></div>
			    </div>  
		
		
				<div class="logintop">    
				    <span>欢迎注册后台管理系统</span>    
				    <ul>
				    <li><a href="#">回首页</a></li>
				    <li><a href="#">帮助</a></li>
				    <li><a href="#">关于</a></li>
				    </ul>    
			    </div>
		    
			    <div class="loginbody">
			    
			    <div class="systemlogo">欢迎注册！</div> 
			       
			    <div class="loginbox">
		    		<form id="registerFormId">
					    <div class="ul">
						    <div>
							    	<div style="float:left ;line-height: 42px;font-weight:bold ;width: 68px">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号： </div>
							    	<div style="float:left">
							    	<input id="userName" name="userName" type="text" class="loginuser" onblur="panDuanUserName()" onkeyup="this.value=value.replace(/\s/g,'')" placeholder="请输用户名"  />
							    	</div>
							    	<div style="float:left ;line-height: 42px ;width:200px"id="nameMsg">&nbsp;</div>
						    </div>
						    <div>
							    	<div style="float:left ;line-height: 42px;font-weight:bold;width: 68px">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码： </div>
							    	<div style="float:left ">
							    		<input id="userPass" name="userPass" type="password" class="loginpwd" placeholder="请输密码" onblur="panDuanUserPass()"/>
							    	</div>
							    	<div style="float:left ;line-height: 42px ;width:200px"id="passMsg">&nbsp;</div>
						    </div>
						    <div>
						    		<div style="float:left ;line-height: 42px;font-weight:bold;width: 68px">密码确认： </div>
						    		<div style="float:left ">
						    			<input id="reUserPass" name="reUserPass" type="password" class="loginpwd" placeholder="请确认密码" onblur="panDuanReUserPass()"/>
						    		</div>
						    		<div style="float:left ;line-height: 42px ;width:200px" id="rePassMsg">&nbsp;</div>
						    </div>
						    		
						    <div>
						    		<div style="float:left ;line-height: 42px;font-weight:bold;width: 68px">手机号码： </div>
						    		<div style="float:left ">
						    			<input id="userPhone" name="userPhone" type="password" class="loginpwd" placeholder="请输手机号" onblur="panDuanPhone()"/>
						    		</div>
						    		</div>
						    		<div style="float:left ;line-height: 42px ;width:200px"id="phoneMsg">&nbsp;</div>
						    <div>
							   		 <div style="float:left ;line-height: 42px;font-weight:bold;width: 68px">验&nbsp;证&nbsp;码&nbsp;&nbsp;：   </div>
							    	<div style="float: left">
							    			<input id="userMessageCode" name="userMessageCode" type="password" class="logincode" placeholder="短信验证码" 
							    					   onkeyup="this.value=value.replace(/\s/g,'')" onblur="panDuanUserMessage()"/>
							    	</div>
							    	
							    	<div style="float: left ;margin-left: 15px">
							    			<input type="button"  id="getCodeButton" class="register-but"  value="获取验证码"/>
							    	</div>
							    	<div style="float:left ;line-height: 42px ;width:200px" id="messageMsg">&nbsp;</div>
						    </div>
						    <div>
						    		<div onclick="lastRegisterFunction()" style=" clear:both;width: 302px;height:42px;background-color: #f60;text-align: center;
						    		font-size:18px; font-weight:bold; color:#fff;cursor:pointer; line-height:35px;margin-left: 69px">
						    				点击注册
									</div>	    
						    </div>
					    </div>
			    	</form>
			    </div>
			    </div>
		    
		   		 <div class="loginbm">版权所有  2017  明瑞教育--仅供学习交流，勿用于任何商业用途</div>
		</body>
	<script type="text/javascript">
	   var test = {
		       node:null,
		       count:60,
		       start:function(){
		          if(this.count > 0){
		             this.node.value = this.count--+"秒后重新获取";
		             var _this = this;
		             setTimeout(function(){
		                 _this.start();
		             },1000);
		          }else{
		             this.node.removeAttribute("disabled");
		             this.node.value = "获取验证码";
		//60秒读完，变回开始背景颜色，在这里添加。
				$("#getCodeButton").attr("class","register-but")
		             this.count = 60;
		          }
		       },
		       //初始化
		       init:function(node){
		          this.node = node;
		          this.node.setAttribute("disabled",true);
		          this.start();
		       }
		    };
		    var btn = document.getElementById("getCodeButton");
		    btn.onclick = function(){
		     //  alert("发送成功...");
		       test.init(btn);
		//点击改变背景颜色，在这里添加
		$("#getCodeButton").attr("class","register-butNot")
		    };
</script>
	<script type="text/javascript">
	//绑定获取验证码按钮的点击事件 注意如果使用 读秒的时候 
	$("#getCodeButton").click(function(){
			var phone=$("#userPhone").val();
	
		$.ajax({
			url:"<%=request.getContextPath()%>/user/getMessageCode.do?userPhone="+phone,
			type:"post",
			dataType:"text",
			async:false,
			success:function(data){
				var returnData=eval("("+data+")");
				alert(returnData.xinxi)
			},
			error:function(){
				alert("出现错误");
			}
		});
	})
	
	
	</script>
	<!-- 引入表单验证的js文件 -->
	<script type="text/javascript"  src="<%=request.getContextPath()%>/js/formYanZheng/javaJs.js"></script>
</html>