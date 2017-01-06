package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.Collection;
import com.swqs.schooltrade.entity.Collection.Key;
import com.swqs.schooltrade.entity.Goods;
import com.swqs.schooltrade.entity.User;
import com.swqs.schooltrade.repository.ICollectionRepository;

@Component
@Service
@Transactional
public class DefaultCollectionService implements ICollectionService {

	@Autowired
	ICollectionRepository collectionRepo;

	@Override
	public void addCollection(User user, Goods goods) {
		Collection.Key key = new Key();
		key.setUser(user);
		key.setGoods(goods);

		Collection collection = new Collection();
		collection.setId(key);
		collectionRepo.save(collection);
	}

	@Override
	public void removeCollection(User user, Goods goods) {
		Collection.Key key = new Key();
		key.setUser(user);
		key.setGoods(goods);

		collectionRepo.delete(key);
	}

	@Override
	public int countCollection(int goodsId) {
		return collectionRepo.collectionCountsOfGoods(goodsId);
	}

	@Override
	public boolean checkCollection(int userId, int articleId) {
		return collectionRepo.checkcollectionExsists(userId, articleId) > 0;
	}

	@Override
	public List<Collection> getMyCollection(Integer id) {
		// TODO Auto-generated method stub
		return collectionRepo.getMyCollection(id);
	}

}