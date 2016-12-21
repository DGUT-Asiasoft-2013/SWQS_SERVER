package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.User;

public interface IUserService {
	User create(User user);

	User findUserByAccount(String account);

	User findUserById(int id);

	User findUserByEmail(String email);

	int updatePwd(String passwordHash, String account);

}
