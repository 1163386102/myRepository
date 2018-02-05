package com.mr.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.user.entity.User;
import com.mr.user.mapper.UserMapper;
import com.mr.user.service.UserService;
@Service

public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public Map<String, Object> loginIf(User user) {
		Map<String,Object>map=new HashMap<String,Object>();
		//根据前台的username去查询用户是否存在
		User returnUser=userMapper.selectByUserLoginName(user.getUserLoginName());
		if(returnUser!=null){//说明账号存在
			if(returnUser.getUserLoginPass().equals(user.getUserLoginPass())&&!"".equals(user.getUserLoginPass())){
				//说明密码正确
				map.put("msg", "登陆成功");
				map.put("object", returnUser);
				map.put("success", true);
			}else{
				map.put("msg", "密码不正确");
				map.put("success", false);
			}
		}else{
			map.put("msg", "账号不存在");
			map.put("success", false);
		}
		return map;
	}

}
