package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Goods;

@Repository
public interface IGoodsRepository extends PagingAndSortingRepository<Goods, Integer> {

	@Query("from Goods goods order by createDate DESC")
	List<Goods> getListGoods();

	@Query("from Goods goods where goods.content like %?1%")
	Page<Goods> searchGoodsWithKeyword(String keyword, Pageable page);

	@Query("from Goods goods where goods.id = ?1")
	List<Goods> findMyBuyGoodslist(Integer id);

	@Query("from Goods goods where goods.id = ?1")
	List<Goods> findMySellGoodslist(Integer id);


}
