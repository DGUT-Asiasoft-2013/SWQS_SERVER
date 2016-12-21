package com.swqs.schooltrade.service;

import java.util.List;

import com.swqs.schooltrade.entity.Image;

public interface IImageService {

	List<Image> getListImageByGoodsId(int id);

	Image save(Image image);

}
