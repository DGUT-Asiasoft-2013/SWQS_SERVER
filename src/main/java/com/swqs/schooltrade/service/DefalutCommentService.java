package com.swqs.schooltrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.Comment;
import com.swqs.schooltrade.repository.ICommentRepository;

@Component
@Service
@Transactional
public class DefalutCommentService implements ICommentService {

	@Autowired
	ICommentRepository commentRepo;

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepo.save(comment);
	}

	@Override
	public Comment findOne(int comment_id) {
		// TODO Auto-generated method stub
		return commentRepo.findOne(comment_id);
	}

	
}
