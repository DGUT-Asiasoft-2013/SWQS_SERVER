package com.swqs.schooltrade.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.GoodsLike;

@Repository
public interface IGoodsLikeRepository extends PagingAndSortingRepository<GoodsLike, Integer>{

	@Query("select count(*) from GoodsLike goodslike where goodslike.goods.id = ?1 and goodslike.isLike = 1")
	int countLike(int goods_id);

	@Query("select count(*) from GoodsLike goodslike where goodslike.goods.id = ?1 and goodslike.isLike = 0")
	int countDisLike(int goods_id);

	@Query("from GoodsLike goodslike where goodslike.account.id=?1 and goodslike.goods.id = ?2")
	GoodsLike getGoodsLike(int buyerIds, int goodsId);

}
