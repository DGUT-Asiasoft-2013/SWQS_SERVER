package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Identify;

@Repository
public interface IIdentifyRepository extends PagingAndSortingRepository<Identify, Integer>{

	@Query("from Identify identify where identify.seller.id = ?1 order by createDate DESC")
	List<Identify> getGoodsIdBySellerId(Integer id);

	@Query("from Identify identify where identify.buyer.id = ?1 order by createDate DESC")
	List<Identify> getGoodsIdByBuyerId(Integer id);

	@Query("from Identify identify where identify.goods.id = ?1")
	Identify findIdentifyByGoodsId(Integer id);

	@Query("from Identify identify where identify.id =?1")
	Identify findIdentifyById(int identifyId);

	@Modifying
	@Query("update Identify identify set identify.tradeState = ?1 where identify.id=?2")
	int setTradeStateById(short tradestate, Integer id);

}
