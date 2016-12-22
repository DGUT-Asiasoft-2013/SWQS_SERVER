package com.swqs.schooltrade.service;

import java.util.List;

import com.swqs.schooltrade.entity.Comment;

public interface ICommentService {

	Comment save(Comment comment);

	Comment findOne(int comment_id);

	List<Comment> getListCommentByGoodsId(int goods_id);

}
