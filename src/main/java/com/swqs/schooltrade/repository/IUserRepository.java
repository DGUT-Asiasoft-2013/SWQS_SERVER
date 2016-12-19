package com.swqs.schooltrade.repository;

import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer>{
	@Query("from User u where u.account = ?1")
	public User findUserByAccount(String account);
	
	@Query("from User u where u.email = ?1")
	public User findUserByEmail(String email);
	
	@Modifying
	@Query("update User u set u.passwordHash = ?1 where u.account = ?2")
	public int updatePwd(String password,String account);
}
