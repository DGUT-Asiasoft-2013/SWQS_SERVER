package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.User;

public interface IUserService {
	User create(User user);

	User findUserByAccount(String account);

	User findUserById(int id);

	User findUserByEmail(String email);

	int updatePwdByEmail(String passwordHash, String email);

	User findUserByPhone(String phone);

	User findUserByName(String name);

	int setSellerBalance(float curPrice,Integer id);

	int setBuyerBalance(float curPrice, Integer id);

	int setRootBalance(float curPrice);



}
