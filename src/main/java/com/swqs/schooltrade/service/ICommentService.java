package com.swqs.schooltrade.service;

import com.swqs.schooltrade.entity.Comment;

public interface ICommentService {

	Comment save(Comment comment);

	Comment findOne(int comment_id);

}
