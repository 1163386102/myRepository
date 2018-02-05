package com.mr.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCookie {
	
	/**Cookie的实现    **/  
	public static void addCookie(String name, String password,HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {  
	    if(name!=null&&!"".equals(name)&&password!=null&&!"".equals(password)){  
	        //创建Cookie  
	        Cookie nameCookie=new Cookie("name",URLEncoder.encode(name,"utf-8"));  
	        Cookie pswCookie=new Cookie("psw",password);  
	          System.out.println(nameCookie+"   "+pswCookie);
	        //设置Cookie的父路径  
	        nameCookie.setPath(request.getContextPath()+"/");  
	        pswCookie.setPath(request.getContextPath()+"/");  
	          
	        //获取是否保存Cookie  
	        String rememberMe=request.getParameter("rememberMe");  
	        if(rememberMe==null){//不保存Cookie  
	            nameCookie.setMaxAge(0);  
	            pswCookie.setMaxAge(0);  
	        }else{//保存Cookie的时间长度，单位为秒  
	            nameCookie.setMaxAge(7*24*60*60);  
	            pswCookie.setMaxAge(7*24*60*60);  
	        }  
	        //加入Cookie到响应头  
	        response.addCookie(nameCookie);  
	        response.addCookie(pswCookie);  
	        
	    }  
	}  
}
