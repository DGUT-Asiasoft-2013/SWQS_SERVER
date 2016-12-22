package com.swqs.schooltrade.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Identify;

@Repository
public interface IIdentifyRepository extends PagingAndSortingRepository<Identify, Integer>{

	@Query("from Identify identify where identify.seller.id = ?1")
	Identify getGoodsIdBySellerId(Integer id);

	@Query("from Identify identify where identify.buyer.id = ?1")
	Identify getGoodsIdByBuyerId(Integer id);

	@Query("from Identify identify where identify.goods.id = ?1")
	Identify findIdentifyByGoodsId(Integer id);

}
