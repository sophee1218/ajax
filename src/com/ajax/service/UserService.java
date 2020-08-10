package com.ajax.service;

import java.util.Map;

public interface UserService
{
	Map<String,String> doLogin(Map<String,String> user);
}
