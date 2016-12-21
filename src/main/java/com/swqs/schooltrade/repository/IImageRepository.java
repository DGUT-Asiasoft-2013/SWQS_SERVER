package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Image;

@Repository
public interface IImageRepository extends PagingAndSortingRepository<Image, Integer>{

	@Query("from Image image where image.goods.id=?1")
	List<Image> getListImageByGoodsId(int id);

}
