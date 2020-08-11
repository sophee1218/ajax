package com.ajax.test.dao;

import java.util.List;
import java.util.Map;

public interface UserInfoDAO
{
	int insertUserInfo(Map<String,Object> ui);
	int updatetUserInfo(Map<String,Object> ui);
	int deleteUserInfo(Map<String,Object> ui);
	Map<String,Object> selectUserInfo(Map<String,Object> ui);
	List<Map<String,Object>> selectUserInfoList(Map<String,Object> ui);
}
