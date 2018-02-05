package com.mr.user.service;

import java.util.Map;

import com.mr.user.entity.User;

public interface UserService {

	Map<String, Object> loginIf(User user);

}
