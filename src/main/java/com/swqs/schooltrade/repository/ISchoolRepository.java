package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.School;

@Repository
public interface ISchoolRepository extends PagingAndSortingRepository<School, Integer> {

	@Query("from School school")
	List<School> findSchoolName();

}
