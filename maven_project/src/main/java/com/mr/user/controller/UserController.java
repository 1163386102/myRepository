package com.mr.user.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mr.user.entity.User;
import com.mr.user.service.UserService;
import com.mr.util.AddCookie;
import com.mr.util.RedisUtil;

@Controller
@RequestMapping(value="userController")
public class UserController {
	@Autowired
	private UserService userService;

	
	@RequestMapping(value="login")
	@ResponseBody
	public Map<String,Object>login(User user,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object>map=userService.loginIf(user);
		User userFromMap=(User)map.get("object");
		if((Boolean)map.get("success")){ //登陆成功之后将登陆对象信息放到redis中 用sessionid 作为key值
			RedisUtil.saveToRedis(session.getId(),userFromMap );
			RedisUtil.setObjectLife(session.getId(), 20);
			try { //保存密码的（将登陆名和登陆密码保存到cookie中）
				AddCookie.addCookie(userFromMap.getUserLoginName(), userFromMap.getUserLoginPass(), response, request);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	
	
	
	@RequestMapping(value="toShowIndexPage")
	public String toShowIndexPage( ModelMap map,HttpSession session){
		User user=(User) RedisUtil.getObjectFromRedis(session.getId());
		map.put("user", user);
		return "goods/index";
	}
}
