package com.swqs.schooltrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.User;
import com.swqs.schooltrade.repository.IUserRepository;

@Component
@Service
@Transactional
public class DefaultUserService implements IUserService {

	@Autowired
	IUserRepository userRepo;

	@Override
	public User create(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findUserByAccount(String account) {
		return userRepo.findUserByAccount(account);
	}

	@Override
	public User findUserById(int id) {
		return userRepo.findOne(id);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

	@Override
	public int updatePwdByEmail(String passwordHash, String email) {
		return userRepo.updatePwdByEmail(passwordHash, email);
	}

	@Override
	public User findUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return userRepo.findUserByPhone(phone);
	}

	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return userRepo.findUserByName(name);
	}

	@Override
	public int setSellerBalance(float curPrice,Integer id) {
		// TODO Auto-generated method stub
		return userRepo.setSellerBalance(curPrice,id);
	}

	@Override
	public int setBuyerBalance(float curPrice, Integer id) {
		// TODO Auto-generated method stub
		return userRepo.setBuyerBalance(curPrice,id);
	}

	@Override
	public int setRootBalance(float curPrice) {
		// TODO Auto-generated method stub
		return userRepo.setRootBalance(curPrice);
	}



}
