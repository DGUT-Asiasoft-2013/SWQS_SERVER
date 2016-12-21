package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.School;
import com.swqs.schooltrade.repository.ISchoolRepository;

@Component
@Service
@Transactional
public class DefaultSchoolService implements ISchoolService{

	@Autowired
	ISchoolRepository schoolRepo;

	@Override
	public School findSchoolById(int id) {
		return schoolRepo.findOne(id);
	}

	@Override
	public List<School> findSchoolName() {
		// TODO Auto-generated method stub
		return schoolRepo.findSchoolName();
	}
	
}
