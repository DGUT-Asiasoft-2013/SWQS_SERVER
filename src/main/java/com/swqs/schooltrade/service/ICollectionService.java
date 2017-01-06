package com.swqs.schooltrade.service;

import java.util.List;

import com.swqs.schooltrade.entity.Collection;
import com.swqs.schooltrade.entity.Goods;
import com.swqs.schooltrade.entity.User;

public interface ICollectionService {

	void addCollection(User user, Goods goods);

	void removeCollection(User user, Goods goods);

	int countCollection(int goodsId);

	boolean checkCollection(int userId, int goodsId);

	List<Collection> getMyCollection(Integer id);
}