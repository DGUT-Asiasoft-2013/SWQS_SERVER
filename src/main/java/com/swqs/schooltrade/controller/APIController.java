package com.swqs.schooltrade.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.swqs.schooltrade.entity.Collection;
import com.swqs.schooltrade.entity.Comment;
import com.swqs.schooltrade.entity.Goods;
import com.swqs.schooltrade.entity.GoodsLike;
import com.swqs.schooltrade.entity.Identify;
import com.swqs.schooltrade.entity.Image;
import com.swqs.schooltrade.entity.Mail;
import com.swqs.schooltrade.entity.School;
import com.swqs.schooltrade.entity.User;
import com.swqs.schooltrade.entity.UserLike;
import com.swqs.schooltrade.service.ICollectionService;
import com.swqs.schooltrade.service.ICommentService;
import com.swqs.schooltrade.service.IGoodsLikeService;
import com.swqs.schooltrade.service.IGoodsService;
import com.swqs.schooltrade.service.IIdentifyService;
import com.swqs.schooltrade.service.IImageService;
import com.swqs.schooltrade.service.ISchoolService;
import com.swqs.schooltrade.service.IUserLikeService;
import com.swqs.schooltrade.service.IUserService;
import com.swqs.schooltrade.util.PushToClient;

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
	@Autowired
	IGoodsLikeService goodsLikeService;
	@Autowired
	IUserLikeService userLikeService;
	@Autowired
	ICollectionService collectionService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody String hello() {
		return "HELLO WORLD";
	}

	// 注册接口
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody User register(@RequestParam(name = "email") String email,
			@RequestParam(name = "account") String account, @RequestParam(name = "password") String passwordHash,
			@RequestParam(name = "schoolId") int schoolId, HttpServletRequest request) {
		School school = schoolService.findSchoolById(schoolId);
		// 寻找数据库中是否有同样用户名account的用户
		User accountIsExist = userService.findUserByAccount(account);
		; // 设置一个标志给予客户端进行判断
		User flag = new User();
		if (accountIsExist != null) {
			// 如果存在账号，则返回字符串accountExist
			flag.setAccount("accountExist");
			return flag;
		}
		User user = new User();
		user.setEmail(email);
		user.setAccount(account);
		user.setPasswordHash(passwordHash);
		user.setName(account);
		user.setSchool(school);
		user.setFace_url("upload/avatar/face.jpg");
		return userService.create(user);
	}

	// // 注册接口
	// @RequestMapping(value = "/register", method = RequestMethod.POST)
	// public @ResponseBody User register(@RequestParam(name = "account") String
	// account,
	// @RequestParam(name = "password") String passwordHash, @RequestParam(name
	// = "email") String email,
	// @RequestParam(name = "name") String name, @RequestParam(name =
	// "birthday") long birthday,
	// @RequestParam(name = "phone") String phone, @RequestParam(name = "sex")
	// short sex,
	// @RequestParam(name = "schoolId") int schoolId, HttpServletRequest
	// request) {
	//
	// School school = schoolService.findSchoolById(schoolId);
	// // 寻找数据库中是否有同样用户名account的用户
	// User accountIsExist = userService.findUserByAccount(account);
	// // 寻找数据库中是否有同样邮箱email的用户
	// User emailIsExist = userService.findUserByEmail(email);
	// // 寻找数据库中是否有同样电话phone的用户
	// User phoneIsExist = userService.findUserByPhone(phone);
	// // 寻找数据库中是否有同样昵称name的用户
	// User nameIsExist = userService.findUserByName(name);
	// // 设置一个标志给予客户端进行判断
	// User flag = new User();
	// if (accountIsExist != null) {
	// // 如果存在账号，则返回字符串accountExist
	// flag.setAccount("accountExist");
	// return flag;
	// }
	// if (phoneIsExist != null) {
	// // 如果存在电话号码，则返回字符串phoneExist
	// flag.setAccount("phoneExist");
	// return flag;
	// }
	// if (emailIsExist != null) {
	// // 如果存在邮箱，则返回字符串emailExist
	// flag.setAccount("emailExist");
	// return flag;
	// }
	// if (nameIsExist != null) {
	// // 如果存在昵称，则返回字符串nameExist
	// flag.setAccount("nameExist");
	// return flag;
	// }
	//
	// // 不存在，则注册用户
	// User user = new User();
	// user.setAccount(account);
	// user.setPasswordHash(passwordHash);
	// user.setEmail(email);
	// user.setName(name);
	// user.setBirthday(new Date(birthday));
	// user.setPhone(phone);
	// user.setSex(sex);
	// user.setSchool(school);
	// return userService.create(user);
	// }

	// 登录接口
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User login(@RequestParam(name = "accountOrEmail") String accountOrEmail,
			@RequestParam(name = "password") String passwordHash) {
		User account = userService.findUserByAccount(accountOrEmail);
		User email = userService.findUserByEmail(accountOrEmail);
		User flag = new User();

		if (account == null && email == null) {
			flag.setAccount("userIsNotExist");
			return flag;
		}
		// 判断用户密码是否正确
		// 判断是否用account登录，否则用email登录
		if (account != null) {
			if (passwordHash.equals(account.getPasswordHash())) {
				// 用户存在且密码正确则登录成功
				return account;
			}
			flag.setAccount("passwordIsNotRight");
			return flag;
		} else {
			if (passwordHash.equals(email.getPasswordHash())) {
				// 用户存在且密码正确则登录成功
				return email;
			}
			flag.setAccount("passwordIsNotRight");
			return flag;
		}
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
	@RequestMapping(value = "/me", method = RequestMethod.POST)
	public User getUser(@RequestParam(name = "uid") int uid) {
		return userService.findUserById(uid);
	}

	// 注册账号验证邮箱接口
	@RequestMapping(value = "/registerCehckEmail", method = RequestMethod.POST)
	public @ResponseBody boolean registerCehckEmail(@RequestParam(name = "email") String email,
			HttpServletRequest request) {
		User emailIsExist = userService.findUserByEmail(email);
		if (emailIsExist != null) {
			return false;
		}
		Mail mail = new Mail();
		try {
			int code = new Random().nextInt(900000) + 100000;
			String message = String.format("您好，欢迎使用校园跳蚤\n您的注册邮箱验证码为:%d\n若不是本人操作，请忽略", code);
			mail.sendMail(email, "校园跳蚤验证码", message);
			HttpSession session = request.getSession(true);
			session.setAttribute("code", code);
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// 修改密码验证邮箱接口
	@RequestMapping(value = "/passwordCheckEmail", method = RequestMethod.POST)
	public @ResponseBody boolean passwordCheckEmail(@RequestParam(name = "email") String email,
			HttpServletRequest request) {
		// 通过邮箱寻找用户
		User user = userService.findUserByEmail(email);
		if (user == null) {
			// 若不存在用户，则邮箱错误或不存在
			return false;
		}
		// 否则，返回用户
		Mail mail = new Mail();
		try {
			int code = new Random().nextInt(900000) + 100000;
			String message = String.format("您好，欢迎使用校园跳蚤\n您的修改密码验证码为:%d\n若不是本人操作，请忽略", code);
			mail.sendMail(email, "校园跳蚤验证码", message);
			HttpSession session = request.getSession(true);
			session.setAttribute("code", code);
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// 验证验证码接口
	@RequestMapping(value = "/checkcode", method = RequestMethod.POST)
	public boolean checkCode(@RequestParam(name = "code") String code, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		int emailCode = (int) session.getAttribute("code");
		System.out.println("emailCode==============" + emailCode);
		if (code.equals(emailCode + "")) {
			return true;
		}
		return false;
	}

	// 修改密码接口
	// 先通过验证邮箱接口再使用修改密码接口
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public @ResponseBody User updataPwd(@RequestParam(name = "password") String passwordHash,
			@RequestParam(name = "email") String email, HttpServletRequest request) {
		// 返回int 1 既修改密码成功

		int flag = userService.updatePwdByEmail(passwordHash, email);
		if (flag == 1) {
			return userService.findUserByEmail(email);
		}
		return null;
	}

	// 学校名称接口
	@RequestMapping(value = "/school", method = RequestMethod.GET)
	public List<School> getSchoolName() {
		return schoolService.findSchoolName();
	}

	// 修改头像接口
	@RequestMapping(value = "/updateFace", method = RequestMethod.POST)
	public User updateFace(@RequestParam(name = "uid") int uid, MultipartFile avatar, HttpServletRequest request) {
		User user = getUser(uid);
		if (avatar != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/avatar");
				FileUtils.copyInputStreamToFile(avatar.getInputStream(),
						new File(realPath, user.getAccount() + ".png"));
				user.setFace_url("upload/avatar/" + user.getAccount() + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userService.create(user);
	}

	// 发布商品接口
	@RequestMapping(value = "/addgoods", method = RequestMethod.POST)
	public Goods addGoods(@RequestParam(name = "uid") int uid, @RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @RequestParam(name = "originalPrice") float originalPrice,
			MultipartFile[] listImage, HttpServletRequest request) {
		User currentUser = getUser(uid);
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
	public Comment addParentComment(@RequestParam(name = "uid") int uid, @PathVariable int goods_id,
			@RequestParam String text) {
		User me = getUser(uid);
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
	public Comment addComment(@RequestParam(name = "uid") int uid, @PathVariable int goods_id,
			@PathVariable int comment_id, @RequestParam String text) {
		User me = getUser(uid);
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
	@RequestMapping(value = "/buygoods/{goods_id}", method = RequestMethod.POST)
	public String buyGoods(@RequestParam(name = "uid") int uid, @PathVariable int goods_id,
			@RequestParam String password) {
		User me = getUser(uid);
		String flag = null;
		if (!password.equals(me.getPasswordHash())) {
			// 密码不正确
			flag = "passwordIsNotRight";
			return flag;
		}
		Goods goods = goodsService.findOne(goods_id);
		Identify identfy = new Identify();
		identfy.setBuyer(me);
		identfy.setGoods(goods);
		identfy.setSeller(goods.getAccount());
		identfy.setTradeState((short) 1);
		User root = userService.findUserById(1);
		userService.setRootBalance(root.getBalance() + goods.getCurPrice());
		userService.setBuyerBalance(me.getBalance() - goods.getCurPrice(), me.getId());
		goodsService.setSell(goods_id);
		identifyService.save(identfy);
		flag = "Success";
		Map<String, String> extra = new HashMap<String, String>();
		extra.put("goods_id", goods.getId() + "");
		extra.put("pushType", "1");
		PushToClient.sendPush("发货通知",
				"您的商品\"" + goods.getTitle() + "\"已被\"" + identfy.getBuyer().getAccount() + "\"购买，请及时发货",
				identfy.getSeller().getAccount(), extra);
		return flag;
	}

	// 获取一个商品接口
	@RequestMapping(value = "/getGoods", method = RequestMethod.GET)
	public Goods getGoods(@RequestParam int goods_id) {
		return goodsService.findOne(goods_id);
	}

	// 获取我卖出的商品列表
	@RequestMapping(value = "/mysell/goodslist", method = RequestMethod.POST)
	public List<Identify> getMySellGoodslist(@RequestParam(name = "uid") int uid) {
		User me = getUser(uid);
		return identifyService.getGoodsIdBySellerId(me.getId());
	}

	// 获取我购买的商品列表
	@RequestMapping(value = "/mybuy/goodslist", method = RequestMethod.POST)
	public List<Identify> getMyBuyGoodslist(@RequestParam(name = "uid") int uid) {
		User me = getUser(uid);
		return identifyService.getGoodsIdByBuyerId(me.getId());
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
	public User rechargeMoney(@RequestParam(name = "uid") int uid, @RequestParam int money) {
		User me = getUser(uid);
		me.setBalance(money + me.getBalance());
		return userService.create(me);
	}

	// 用户评价商品接口
	@RequestMapping(value = "/goods/{goods_id}/goodslike", method = RequestMethod.POST)
	public GoodsLike goodsLike(@RequestParam(name = "uid") int uid, @PathVariable int goods_id,
			@RequestParam Boolean isLike) {
		User me = getUser(uid);
		GoodsLike like = new GoodsLike();
		Goods goods = goodsService.findOne(goods_id);
		if (isLike) {
			like.setGoods(goods);
			like.setAccount(me);
			like.setIsLike(isLike);
			return goodsLikeService.like(like);
		}
		like.setGoods(goods);
		like.setAccount(me);
		like.setIsLike(isLike);
		return goodsLikeService.disLike(like);
	}

	// 用户评价用户接口
	@RequestMapping(value = "/user/{user_id}/userlike", method = RequestMethod.POST)
	public UserLike userLike(@RequestParam(name = "uid") int uid, @PathVariable int user_id,
			@RequestParam Boolean isLike) {
		User me = getUser(uid);
		UserLike like = new UserLike();
		User assessee = userService.findUserById(user_id);
		if (isLike) {
			like.setAssessee(assessee);
			like.setEvaluator(me);
			like.setIsLike(isLike);
			return userLikeService.like(like);
		}
		like.setAssessee(assessee);
		like.setEvaluator(me);
		like.setIsLike(isLike);
		return userLikeService.disLike(like);
	}

	// 商品好评/差评数量显示接口
	@RequestMapping(value = "/goodslike/{goods_id}/count", method = RequestMethod.GET)
	public int[] getGoodsLikeCount(@PathVariable int goods_id) {
		int[] count = new int[2];
		int countLike = goodsLikeService.countLike(goods_id);
		;
		int countDisLike = goodsLikeService.countDisLike(goods_id);
		count[0] = countLike;
		count[1] = countDisLike;
		return count;
	}

	// 用户好评/差评数量显示接口
	@RequestMapping(value = "/userlike/{user_id}/count", method = RequestMethod.POST)
	public int[] getUserLikeCount(@PathVariable int user_id) {
		int[] count = new int[2];
		int countLike = userLikeService.countLike(user_id);
		;
		int countDisLike = userLikeService.countDisLike(user_id);
		count[0] = countLike;
		count[1] = countDisLike;
		return count;
	}

	// 获取我发布的商品列表
	@RequestMapping(value = "/mypublishment/goodslist", method = RequestMethod.POST)
	public List<Goods> getMyPublishmentGoodslist(@RequestParam(name = "uid") int uid) {
		User me = getUser(uid);
		return goodsService.getMyPublishmentGoodslist(me.getId());
	}

	// 已发货、确认收货、已评价设置标志接口
	// flag=2（确认发货） flag=3（确认收货）
	@RequestMapping(value = "/settradestate", method = RequestMethod.POST)
	public int setTradestate(@RequestParam int identifyId, @RequestParam int flag) {
		short tradestate = (short) flag;
		Identify identify = identifyService.findIdentifyById(identifyId);
		if (tradestate == 2) {
			// 确认发货发送推送
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("goods_id", identify.getGoods().getId() + "");
			extra.put("pushType", "2");
			PushToClient.sendPush("商家已发货", "您购买的商品\"" + identify.getGoods().getTitle() + "\"已经发货",
					identify.getBuyer().getAccount(), extra);
			return identifyService.setTradeStateById(tradestate, identify.getId());
		} else if (tradestate == 3) {
			User root = userService.findUserById(1);
			Goods goods = goodsService.findOne(identify.getGoods().getId());
			User seller = userService.findUserById(identify.getSeller().getId());
			userService.setRootBalance(root.getBalance() - goods.getCurPrice());
			userService.setSellerBalance(seller.getBalance() + goods.getCurPrice(), seller.getId());
			// 确认收货发送推送
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("goods_id", identify.getGoods().getId() + "");
			extra.put("pushType", "3");
			PushToClient.sendPush("买家已收货", "您上架的商品\"" + identify.getGoods().getTitle() + "\"已经被接收",
					identify.getSeller().getAccount(), extra);
			return identifyService.setTradeStateById(tradestate, identify.getId());
		}
		return 5;
	}

	// 修改资料接口
	@RequestMapping(value = "/updateme", method = RequestMethod.POST)
	public User updateMe(@RequestParam(name = "uid") int uid, @RequestParam(name = "name") String name,
			@RequestParam(name = "birthday") long birthday, @RequestParam(name = "phone") String phone,
			@RequestParam(name = "schoolId") int schoolId, @RequestParam(name = "sex") short sex) {
		User me = getUser(uid);
		School school = schoolService.findSchoolById(schoolId);
		// 寻找数据库中是否有同样电话phone的用户
		// User phoneIsExist = userService.findUserByPhone(phone);
		// 寻找数据库中是否有同样昵称name的用户
		User nameIsExist = userService.findUserByName(name);
		// 设置一个标志给予客户端进行判断
		User flag = new User();

		if (phone == null || phone.equals("")) {
			me.setPhone(null);
		} else {
			User phoneIsExist = userService.findUserByPhone(phone);
			if (phoneIsExist != null && !me.getPhone().equals(phoneIsExist.getPhone())) {
				// 如果存在电话号码，则返回字符串phoneExist
				flag.setAccount("phoneExist");
				return flag;
			}
			me.setPhone(phone);
		}

		if (!me.getName().equals(name)) {
			if (nameIsExist != null) {
				// 如果存在昵称，则返回字符串nameExist
				flag.setAccount("nameExist");
				return flag;
			}
		}
		me.setSex(sex);
		me.setName(name);
		if (birthday != 0) {
			me.setBirthday(new Date(birthday));
		}
		me.setSchool(school);
		return userService.create(me);
	}

	// 修改商品接口
	@RequestMapping(value = "/updategoods/{goods_id}", method = RequestMethod.POST)
	public Goods updateGoods(@PathVariable int goods_id, @RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @RequestParam(name = "curPrice") float curPrice) {
		Goods curGoods = goodsService.findOne(goods_id);
		curGoods.setTitle(title);
		curGoods.setContent(content);
		curGoods.setCurPrice(curPrice);
		return goodsService.save(curGoods);
	}

	// 收藏商品接口
	@RequestMapping(value = "/collectGoods/{goods_id}", method = RequestMethod.POST)
	public int collectGoods(@RequestParam(name = "uid") int uid, @PathVariable int goods_id,
			@RequestParam boolean collection) {
		User me = getUser(uid);
		Goods curGoods = goodsService.findOne(goods_id);
		if (collection) {
			collectionService.addCollection(me, curGoods);
		} else {
			collectionService.removeCollection(me, curGoods);
		}

		return collectionService.countCollection(goods_id);
	}

	// 商品被收藏数接口
	@RequestMapping(value = "/goods/{goods_id}/countCollection", method = RequestMethod.GET)
	public int countLikes(@PathVariable int goods_id) {
		return collectionService.countCollection(goods_id);
	}

	// 商品是否被收藏接口
	@RequestMapping(value = "/goods/{goods_id}/isCollection", method = RequestMethod.POST)
	public boolean checkCollection(@RequestParam(name = "uid") int uid, @PathVariable int goods_id) {
		User me = getUser(uid);
		return collectionService.checkCollection(me.getId(), goods_id);
	}

	// 我的收藏接口
	@RequestMapping(value = "/myCollection", method = RequestMethod.POST)
	public List<Collection> getMyCollection(@RequestParam(name = "uid") int uid) {
		User me = getUser(uid);
		return collectionService.getMyCollection(me.getId());
	}
}
