package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Comment;

@Repository
public interface ICommentRepository extends PagingAndSortingRepository<Comment, Integer> {

	@Query("from Comment comment where comment.goods.id = ?1 order by createDate DESC")
	List<Comment> getListCommentByGoodsId(int goods_id);

}
