package com.swqs.schooltrade.service;

import java.util.List;

import com.swqs.schooltrade.entity.School;

public interface ISchoolService {

	School findSchoolById(int id);

	List<School> findSchoolName();
}
