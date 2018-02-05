<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%  
    String name="";  
    String psw="";  
    String checked="";  
    Cookie[] cookies=request.getCookies();  
    if(cookies!=null&&cookies.length>0){   
        //遍历Cookie  
        for(int i=0;i<cookies.length;i++){  
            Cookie cookie=cookies[i];  
            //此处类似与Map有name和value两个字段,name相等才赋值,并处理编码问题   
            if("name".equals(cookie.getName())){  
                name=java.net.URLDecoder.decode(cookie.getValue(),"utf-8");  
                //将"记住我"设置为勾选   
                checked="checked";  
            }  
            if("psw".equals(cookie.getName())){  
                psw=cookie.getValue();  
            }  
        }  
    }  
 %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登陆界面</title>
		<link href="<%=request.getContextPath()%>/js/OA_Login_JS/css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="<%=request.getContextPath()%>/js/OA_Login_JS/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/OA_Login_JS/cloud.js" type="text/javascript"></script>
		
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
				    <span>欢迎登录后台管理界面平台</span>    
				    <ul>
				    <li><a href="#">回首页</a></li>
				    <li><a href="#">帮助</a></li>
				    <li><a href="#">关于</a></li>
				    </ul>    
			    </div>
		    
			    <div class="loginbody">
			    
			    <span class="systemlogo"></span> 
			       
			    <div class="loginbox">
		    
					    <ul>
					    <li><input name="userLoginName" type="text" class="loginuser" placeholder="用户名" value="<%=name %>" /></li>
					    <li><input name="userLoginPass" type="password" class="loginpwd" placeholder="密码" value="<%=psw %>"/></li>
					  <%--  
						  	<li>
						    		<div>
						    		<input id="code"  name="userVerificationCode" type="text" class="logincode" placeholder="请输入验证码"/>
							 			<img   title="点击更换" src="<%=request.getContextPath() %>/user/createCode.do" 
							 			    class="logincodeimg"  onclick="refreshCode()" > 
						          	</div>
						    </li>
					   --%>
					    <li><input  id="loginButton" type="button" class="loginbtn" value="登录"  onclick="javascript:loginIf()"  />
					    <label><input name="rememberMe" type="checkbox" id="rememberMe"   <%=checked %>/>记住密码</label>
					    <label><a href="<%=request.getContextPath() %>/register2.jsp">注册新的账号</a></label>
					    </li>
					    </ul>
			    
			    </div>
			    
			    </div>
		    
		   		 <div class="loginbm">版权所有  2017  明瑞教育--仅供学习交流，勿用于任何商业用途</div>
		</body>
	<script type="text/javascript">
		$(function(){  //点击enter键进行登陆的方法
			$("body").keydown(function(){
				if(event.keyCode=="13"){
					$("#loginButton").click();
				}
			})
		})
		//登陆的请求
		function loginIf(){
			$.ajax({
				url:"<%=request.getContextPath()%>/userController/login.do",
				data:{"userLoginName":$("[name='userLoginName']").val(),"userLoginPass":$("[name='userLoginPass']").val(),
					"rememberMe":$("[name='rememberMe']").val()
					},
				
				type:"post",
				success:function(data){
					alert(data.msg)
					if(data.success){
						location.href="<%=request.getContextPath()%>/userController/toShowIndexPage.do"						
					}
					
				},
				error:function(){
					alert("代码出现错误")
				},
			});
			
		}
	
	
		
		
	</script>
</html>