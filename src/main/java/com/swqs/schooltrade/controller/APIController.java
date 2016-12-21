package com.swqs.schooltrade.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.swqs.schooltrade.entity.Goods;
import com.swqs.schooltrade.entity.Image;
import com.swqs.schooltrade.entity.School;
import com.swqs.schooltrade.entity.User;
import com.swqs.schooltrade.service.IGoodsService;
import com.swqs.schooltrade.service.IImageService;
import com.swqs.schooltrade.service.ISchoolService;
import com.swqs.schooltrade.service.IUserService;

@RestController
@RequestMapping("/api")
public class APIController {

	@Autowired
	IUserService userService;
	@Autowired
	ISchoolService schoolService;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	IImageService imageService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody String hello() {
		return "HELLO WORLD";
	}

	// 注册接口
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody User register(@RequestParam(name = "account") String account,
			@RequestParam(name = "password") String passwordHash, @RequestParam(name = "email") String email,
			@RequestParam(name = "name") String name, @RequestParam(name = "birthday") long birthday,
			@RequestParam(name = "phone") String phone, @RequestParam(name = "sex") short sex,
			@RequestParam(name = "schoolId") int schoolId, MultipartFile avatar, HttpServletRequest request) {

		School school = schoolService.findSchoolById(schoolId);
		User user = new User();
		user.setAccount(account);
		user.setPasswordHash(passwordHash);
		user.setEmail(email);
		user.setName(name);
		user.setBirthday(new Date(birthday));
		user.setPhone(phone);
		user.setSex(sex);
		user.setSchool(school);

		if (avatar != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/avatar");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath, account + ".png"));
				user.setFace_url("upload/avatar/" + account + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return userService.create(user);
	}

	// 登录接口
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User login(@RequestParam(name = "account") String account,
			@RequestParam(name = "password") String passwordHash, HttpServletRequest request) {
		User user = userService.findUserByAccount(account);
		if (user != null && passwordHash.equals(user.getPasswordHash())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("uid", user.getId());
			return user;
		} else {
			return null;
		}
	}

	// 我的信息接口
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		int uid = (int) session.getAttribute("uid");
		return userService.findUserById(uid);
	}

	// 验证邮箱接口
	@RequestMapping(value = "/inputemail", method = RequestMethod.POST)
	public @ResponseBody User email(@RequestParam(name = "email") String email, HttpServletRequest request) {
		User user = userService.findUserByEmail(email);
		return user;
	}

	// 修改密码接口
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public @ResponseBody int updataPwd(@RequestParam(name = "password") String passwordHash,
			@RequestParam(name = "account") String account, HttpServletRequest request) {
		return userService.updatePwd(passwordHash, account);
		// 返回int 1 既修改密码成功
	}

	// 学校名称接口
	@RequestMapping(value = "/school", method = RequestMethod.GET)
	public List<School> getSchoolName() {
		return schoolService.findSchoolName();
	}

	// 发布商品接口
	@RequestMapping(value = "/addgoods", method = RequestMethod.POST)
	public Goods addGoods(@RequestParam(name = "title") String title, @RequestParam(name = "content") String content,
			@RequestParam(name = "originalPrice") float originalPrice, MultipartFile[] listImage,
			HttpServletRequest request) {
		User currentUser = getUser(request);
		Goods goods = new Goods();
		goods.setAccount(currentUser);
		goods.setTitle(title);
		goods.setContent(content);
		goods.setOriginalPrice(originalPrice);
		goods = goodsService.save(goods);
		if (listImage != null) {
			for (int i = 0; i < listImage.length; i++) {
				try {
					String realPath = request.getSession().getServletContext()
							.getRealPath("/WEB-INF/upload/goodsimage");
					FileUtils.copyInputStreamToFile(listImage[i].getInputStream(),
							new File(realPath, currentUser.getAccount() + "_" + goods.getId() + "_" + i + ".png"));
					Image image = new Image();
					image.setPictureUrl(
							"upload/goodsimage/" + currentUser.getAccount() + "_" + goods.getId() + "_" + i + ".png");
					image.setGoods(goods);
					imageService.save(image);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return goods;
	}

	// 获取商品列表方法1
	@RequestMapping(value = "/getlistgoods", method = RequestMethod.GET)
	public List<Goods> getListGoods() {
		return goodsService.getListGoods();
	}

	// 获取商品列表方法2
	@RequestMapping(value = "/getlistgoods/image", method = RequestMethod.GET)
	public List<Goods> getListGoodsImage() {
		int i = 10;
		List<Goods> goodsList = goodsService.getListGoods();
		for (Goods g : goodsList) {
			int id = g.getId();
			List<Image> imageList = imageService.getListImageByGoodsId(id);
			g.setListImage(imageList);
		}
		return goodsList;
	}

	// 搜索商品接口
	@RequestMapping(value = "/goods/search/{keyword}",method = RequestMethod.GET)
	public Page<Goods> searchArticleWithKeyword(@PathVariable String keyword,
			@RequestParam(defaultValue = "0") int page) {
		return goodsService.searchGoodsWithKeyword(keyword, page);
	} 
}
