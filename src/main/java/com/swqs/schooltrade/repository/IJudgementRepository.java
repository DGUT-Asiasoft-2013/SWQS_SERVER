package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Judgement;

@Repository
public interface IJudgementRepository extends PagingAndSortingRepository<Judgement, Integer> {

	@Query("from Judgement judgement where judgement.goods.id = ?1")
	Judgement getJudgementByGoodsId(int goods_id);

}
