package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.Goods;
import com.swqs.schooltrade.repository.IGoodsRepository;

@Component
@Service
@Transactional
public class DefalutGoodsService implements IGoodsService {

	@Autowired
	IGoodsRepository goodsRepo;

	@Override
	public Goods save(Goods goods) {
		// TODO Auto-generated method stub
		return goodsRepo.save(goods);
	}

	@Override
	public List<Goods> getListGoods() {
		return goodsRepo.getListGoods();
	}

	@Override
	public Page<Goods> searchGoodsWithKeyword(String keyword, int page) {
		// TODO Auto-generated method stub
		int size = 10;
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest request = new PageRequest(page, size, sort);
		return goodsRepo.searchGoodsWithKeyword(keyword, request);
	}

	@Override
	public Goods findOne(int goods_id) {
		// TODO Auto-generated method stub
		return goodsRepo.findOne(goods_id);
	}

	@Override
	public Goods getMySellGoodslist(Integer id) {
		// TODO Auto-generated method stub
		return goodsRepo.findMySellGoodslist(id);
	}

	@Override
	public Goods getMyBuyGoodslist(Integer id) {
		// TODO Auto-generated method stub
		return goodsRepo.findMyBuyGoodslist(id);
	}

}
