package com.swqs.schooltrade.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Identify;

@Repository
public interface IIdentifyRepository extends PagingAndSortingRepository<Identify, Integer>{

}
