package com.swqs.schooltrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swqs.schooltrade.entity.Image;
import com.swqs.schooltrade.repository.IImageRepository;

@Component
@Service
@Transactional
public class DefalutImageService implements IImageService{

	@Autowired
	IImageRepository imageRepo;

	@Override
	public List<Image> getListImageByGoodsId(int id) {
		// TODO Auto-generated method stub
		return imageRepo.getListImageByGoodsId(id);
	}

	@Override
	public Image save(Image image) {
		// TODO Auto-generated method stub
		return imageRepo.save(image);
	}

}
