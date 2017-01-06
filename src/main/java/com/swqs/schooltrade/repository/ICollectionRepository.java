package com.swqs.schooltrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swqs.schooltrade.entity.Collection;

@Repository
public interface ICollectionRepository extends PagingAndSortingRepository<Collection, Collection.Key> {

	@Query("select count(*) from Collection collection where collection.id.goods.id = ?1")
	int collectionCountsOfGoods(int articleId);

	@Query("select count(*) from Collection collection where collection.id.user.id = ?1 and collection.id.goods.id = ?2")
	int checkcollectionExsists(int authorId, int articleId);

	@Query("from Collection collection where collection.id.user.id = ?1 order by createDate DESC")
	List<Collection> getMyCollection(Integer id);

}