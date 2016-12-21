package com.swqs.schooltrade.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Comment;

@Repository
public interface ICommentRepository extends PagingAndSortingRepository<Comment, Integer>{

}
