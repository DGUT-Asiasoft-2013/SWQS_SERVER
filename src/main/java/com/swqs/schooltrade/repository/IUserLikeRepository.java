package com.swqs.schooltrade.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.UserLike;

@Repository
public interface IUserLikeRepository extends PagingAndSortingRepository<UserLike, Integer> {

	@Query("select count(*) from UserLike userlike where userlike.assessee.id = ?1 and userlike.isLike = 1")
	int countLike(int user_id);

	@Query("select count(*) from UserLike userlike where userlike.assessee.id = ?1 and userlike.isLike = 0")
	int countDisLike(int user_id);

}
