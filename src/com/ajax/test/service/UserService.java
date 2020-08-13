package com.ajax.test.service;

import java.util.Map;

public interface UserService
{
	Map<String,Object> doLogin(Map<String,String> user);
	Map<String,Object> joinUserInfo(Map<String,Object> user);
	Map<String,String> checkId(String uiId);
}
