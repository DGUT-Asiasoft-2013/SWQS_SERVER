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

//
//	@Override
//	public User getCurrentUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean changePassword(String newPasswordHash) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void logout() {
//		// TODO Auto-generated method stub
//
//	}

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
	public int updatePwd(String account, String password) {
		return userRepo.updatePwd(password, account);
	}

}
