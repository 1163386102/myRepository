//验证用户名是否已经存在的方法
function panDuanUserName(){
	//获取用户名框里的内容
	var userName=$("#userName").val();
	//写正则
	var reg=/^[\u4e00-\u9fa5 a-z A-Z _-]{3,20}$/; //3位（汉字字母数字下划线）
	var bolen=reg.test(userName);
	if(bolen){
		//符合正则之后判断是否存在该姓名；
		//根据可以使用的正则到数据库查询判断名字是否可用
		var queryResult=queryByUserName(userName)
		if(queryResult){
			//提示帐户名可用
			$("#nameMsg").html("<font color='green' >账户名可以使用</font>")
			return true;
		}else{
			//提示账户名已经被使用，请换个账号
			$("#nameMsg").html("<font color='red' >账户名已被使用</font>")
			return false;
		}
	}else{ //不符合正则的时候在输入框后面进行提示不符合正则
		$("#nameMsg").html("<font color='red' >请输入至少3位汉字 字母 下划线</font>")
		return false;
	}
}
//判断userPass是否符合正则；
function panDuanUserPass(){
	var userPass=$("#userPass").val();
	var reg=/^\w{6,10}$/   
	if(reg.test(userPass)){
		$("#passMsg").html("<font color='green' >密码可用</font>")
		return true;
	}else{
		$("#passMsg").html("<font color='red' >请输入6-10位的密码</font>")
		return false;
	}
}
//判断确认的userPass 是否和userPass一致
function panDuanReUserPass(){
	var rePass=$("#reUserPass").val();
	var pass=$("#userPass").val();
	if(pass!=null&&pass!=""){
		if(rePass==pass){
			$("#rePassMsg").html("<font color='green' >密码一致</font>")
			return true;
		}else{
			$("#rePassMsg").html("<font color='red' >两次密码不一致</font>")
			return false;
		}
	}
}
//判断手机号码的输入是否符合
function panDuanPhone(){
	var phone=$("#userPhone").val();
	var reg=/^(13|15|18|17|19)\d{9}$/;
	if(reg.test(phone)){
		$("#phoneMsg").html("<font color='green' >手机号格式正确</font>")
		return true;
	}else{
		$("#phoneMsg").html("<font color='red' >手机号格式错误</font>")
		return false;
	}
}
//判断验证码是否输入
function panDuanUserMessage(){
	var message=$("#userMessageCode").val();
	if(message!=null&&message!=''){
		return true;
	}else{
		$("#messageMsg").html("<font color='red' >请输入验证码</font>")
		return false;
	}
}
//最后的进行注册按钮时的事件
function lastRegisterFunction(){
	var resultOfName=panDuanUserName();
	var resultOfPass=panDuanUserPass();
	var resultOfRePass=panDuanReUserPass();
	var resultOfPhone=panDuanPhone();
	var resultOfMessage=panDuanUserMessage();
	var bool=(resultOfName&&resultOfPass&&resultOfRePass&&resultOfPhone&&resultOfMessage);
	if(bool){
		//alert("keyi ")
		insertUserFunction();
	}
}







//到数据库进行查询，判断名字是否已经有人使用了
function queryByUserName(name){
	var queryResult;
	$.ajax({
		url:"http://localhost:8080/computer_maven_easyui_ssm_poi_full/user/panDuanQueryByUserName.do?name="+name,
		type:"post",
		//data:$("#registerFormId").serialize(),
		//dataType:"text",
		async:false,  //注意要获得成功回调函数的值  要使用同步 操作，否则 如果异步，还没有返回结果 就执行了
		success:function(data){
			
				queryResult=data.success; //将查询结果返回
		},
		error:function(){
			alert("出现错误");
		}
	});
	return queryResult;
}





function registerFunction(){
		//先判断账号，密码，短信验证码 是否填写  （正则之类）
		var name=$("#userName").val()
		var pass=$("#userPass").val();
		var rePass=$("#reUserPass").val();
		var phone=$("#userPhone").val();
		var code=$("#userMessageCode").val();
		if(name==null||name==''){
			alert("请填写账号")
			return;
		}if(pass==null||pass==''){
			alert("请填写密码");
			return;
		}if(rePass==null||rePass==''){
			alert("请确认密码");
			return;
		}if(rePass!=pass){
			alert("密码不一致！");
			return;
		}
		if(phone==null||phone==''){
			alert("请填写手机号码");
			return;
		}if(code==null||code==''){
			alert("请填写短信验证码");
			return;
		}else{
			//调用新增的操作
		
			insertUserFunction();
		}
	}



//调用ajax进行新增的操作
function insertUserFunction(){  
	$.ajax({
		url:"http://localhost:8080/computer_maven_easyui_ssm_poi_full/user/insertUser.do",
		type:"post",
		data:$("#registerFormId").serialize(),
		//dataType:"text",
		async:false,
		success:function(data){
			alert(data.msg)
			if(data.flag){
				location.href="http://localhost:8080/computer_maven_easyui_ssm_poi_full/login.jsp"
			}else{
				location.reload();
			}
		},
		error:function(){
			alert("出现错误");
		}
	});
	
}