package com.swqs.schooltrade.controller;

import java.io.File;
import java.util.ArrayList;
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

import com.swqs.schooltrade.entity.Comment;
import com.swqs.schooltrade.entity.Goods;
import com.swqs.schooltrade.entity.Identify;
import com.swqs.schooltrade.entity.Image;
import com.swqs.schooltrade.entity.School;
import com.swqs.schooltrade.entity.User;
import com.swqs.schooltrade.service.ICommentService;
import com.swqs.schooltrade.service.IGoodsService;
import com.swqs.schooltrade.service.IIdentifyService;
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
	@Autowired
	ICommentService commentService;
	@Autowired
	IIdentifyService identifyService;

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
			@RequestParam(name = "schoolId") int schoolId, HttpServletRequest request) {

		School school = schoolService.findSchoolById(schoolId);
		// 寻找数据库中是否有同样用户名account的用户
		User accountIsExist = userService.findUserByAccount(account);
		// 寻找数据库中是否有同样邮箱email的用户
		User emailIsExist = userService.findUserByEmail(email);
		// 寻找数据库中是否有同样电话phone的用户
		User phoneIsExist = userService.findUserByPhone(phone);
		// 寻找数据库中是否有同样昵称name的用户
		User nameIsExist = userService.findUserByName(name);
		// 设置一个标志给予客户端进行判断
		User flag = new User();
		if (accountIsExist != null) {
			// 如果存在账号，则返回字符串accountExist
			flag.setAccount("accountExist");
			return flag;
		}
		if (phoneIsExist != null) {
			// 如果存在电话号码，则返回字符串phoneExist
			flag.setAccount("phoneExist");
			return flag;
		}
		if (emailIsExist != null) {
			// 如果存在邮箱，则返回字符串emailExist
			flag.setAccount("emailExist");
			return flag;
		}
		if (nameIsExist != null) {
			// 如果存在昵称，则返回字符串nameExist
			flag.setAccount("nameExist");
			return flag;
		}

		// 不存在，则注册用户
		User user = new User();
		user.setAccount(account);
		user.setPasswordHash(passwordHash);
		user.setEmail(email);
		user.setName(name);
		user.setBirthday(new Date(birthday));
		user.setPhone(phone);
		user.setSex(sex);
		user.setSchool(school);
		return userService.create(user);
	}

	// 登录接口
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User login(@RequestParam(name = "account") String account,
			@RequestParam(name = "password") String passwordHash, HttpServletRequest request) {
		User user = userService.findUserByAccount(account);
		User flag = new User();

		if (user == null) {
			flag.setAccount("accountIsNotExist");
			return flag;
		}

		// 判断用户密码是否正确
		if (passwordHash.equals(user.getPasswordHash())) {
			// 用户存在且密码正确则登录成功
			HttpSession session = request.getSession(true);
			session.setAttribute("uid", user.getId());
			return user;
		}
		flag.setAccount("passwordIsNotRight");
		return flag;

	}

	// 登录test接口
	@RequestMapping(value = "/logintest", method = RequestMethod.GET)
	public @ResponseBody User logintest(@RequestParam(name = "account") String account,
			@RequestParam(name = "password") String passwordHash, HttpServletRequest request) {
		User user = userService.findUserByAccount(account);
		User flag = new User();

		if (user == null) {
			flag.setAccount("accountIsNotExist");
			return flag;
		}

		// 判断用户密码是否正确
		if (passwordHash.equals(user.getPasswordHash())) {
			// 用户存在且密码正确则登录成功
			HttpSession session = request.getSession(true);
			session.setAttribute("uid", user.getId());
			return user;
		}
		flag.setAccount("passwordIsNotRight");
		return flag;

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
		// 通过邮箱寻找用户
		User user = userService.findUserByEmail(email);
		if (user == null) {
			// 若不存在用户，则邮箱错误或不存在
			return null;
		}
		// 否则，返回用户
		return user;
	}

	// 修改密码接口
	// 先通过验证邮箱接口再使用修改密码接口
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public @ResponseBody int updataPwd(@RequestParam(name = "password") String passwordHash,
			@RequestParam(name = "account") String account, HttpServletRequest request) {
		// 返回int 1 既修改密码成功
		return userService.updatePwd(passwordHash, account);
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
		goods.setCurPrice(originalPrice);
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
	// 就用这种方法
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
	// 需传入keyword（即搜索的关键字），返回包含该关键字的商品列表
	@RequestMapping(value = "/goods/search/{keyword}", method = RequestMethod.GET)
	public Page<Goods> searchArticleWithKeyword(@PathVariable String keyword,
			@RequestParam(defaultValue = "0") int page) {
		return goodsService.searchGoodsWithKeyword(keyword, page);
	}

	// 商品添加评论接口
	// 在商品列表Item中传入goods到商品详情Activity，
	// 需传入goods_id,从goods中获取
	@RequestMapping(value = "/goods/{goods_id}/addParentComments", method = RequestMethod.POST)
	public Comment addParentComment(@PathVariable int goods_id, @RequestParam String text, HttpServletRequest request) {
		User me = getUser(request);
		Goods goods = goodsService.findOne(goods_id);
		Comment comment = new Comment();
		comment.setAccount(me);
		comment.setGoods(goods);
		comment.setText(text);
		return commentService.save(comment);
	}

	// 评论添加评论接口
	// 在商品列表Item中传入goods到商品详情Activity，再传入添加评论Activity
	// 在评论列表Item中传入comment到添加评论Activity
	// 需传入goods_id,comment_id,从goods、comment中获取
	@RequestMapping(value = "/goods/{goods_id}/parentcomments/{comment_id}/addComments", method = RequestMethod.POST)
	public Comment addComment(@PathVariable int goods_id, @PathVariable int comment_id, @RequestParam String text,
			HttpServletRequest request) {
		User me = getUser(request);
		Goods goods = goodsService.findOne(goods_id);
		Comment parrentComment = commentService.findOne(comment_id);
		Comment comment = new Comment();
		comment.setAccount(me);
		comment.setGoods(goods);
		comment.setText(text);
		comment.setParentComment(parrentComment);
		return commentService.save(comment);
	}

	// 购买商品接口
	// 在商品列表Item中传入goods到商品详情Activity
	// 需传入goods_id,从goods中获取
	@RequestMapping(value = "/buygoods/{goods_id}", method = RequestMethod.GET)
	public Identify buyGoods(@PathVariable int goods_id, HttpServletRequest request) {
		User me = getUser(request);
		Goods goods = goodsService.findOne(goods_id);
		Identify identfy = new Identify();
		identfy.setBuyer(me);
		identfy.setGoods(goods);
		identfy.setSeller(goods.getAccount());
		identfy.setTradeState((short) 1);
		userService.setSellerBalance(goods.getCurPrice(), goods.getAccount().getId());
		userService.setBuyerBalance(me.getBalance() - goods.getCurPrice(), me.getId());
		return identifyService.save(identfy);
	}

	// 获取我卖出的商品列表
	@RequestMapping(value = "/mysell/goodslist", method = RequestMethod.GET)
	public List<Goods> getMySellGoodslist(HttpServletRequest request) {
		User me = getUser(request);
		List<Identify> listIdentify = identifyService.getGoodsIdBySellerId(me.getId());
		if (listIdentify == null) {
			return null;
		} else {
			List<Goods> goodsList=new ArrayList<Goods>();
			for(Identify identify:listIdentify){
				goodsList.add(goodsService.getMySellGoodslist(identify.getGoods().getId()));
			}
			return goodsList;
		}
	}

	// 获取我购买的商品列表
	@RequestMapping(value = "/mybuy/goodslist", method = RequestMethod.GET)
	public List<Goods> getMyBuyGoodslist(HttpServletRequest request) {
		User me = getUser(request);
		List<Identify> listIdentify = identifyService.getGoodsIdByBuyerId(me.getId());
		if (listIdentify == null) {
			return null;
		} else {
			List<Goods> goodsList=new ArrayList<Goods>();
			for(Identify identify:listIdentify){
				goodsList.add(goodsService.getMyBuyGoodslist(identify.getGoods().getId()));
			}
			return goodsList;
		}
	}

	// 显示订单详情接口
	// 在我卖出/购入商品列表Item中传入goods到订单详情Activity
	// 需传入goods_id,从goods中获取
	@RequestMapping(value = "/orderdetails/{goods_id}", method = RequestMethod.GET)
	public Identify getOrderDetails(@PathVariable int goods_id) {
		Identify identify = identifyService.findIdentifyByGoodsId(goods_id);
		if (identify == null) {
			return null;
		} else {
			return identify;
		}
	}

	// 显示商品评论接口
	// 在商品列表Item中传入goods到商品详情Activity
	// 需传入goods_id,从goods中获取
	@RequestMapping(value = "/goods/{goods_id}/comments", method = RequestMethod.GET)
	public List<Comment> getListComment(@PathVariable int goods_id) {
		return commentService.getListCommentByGoodsId(goods_id);
	}

	// 充值金额接口
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public User rechargeMoney(@RequestParam int money, HttpServletRequest request) {
		User me = getUser(request);
		me.setBalance(money);
		return userService.create(me);
	}
}
