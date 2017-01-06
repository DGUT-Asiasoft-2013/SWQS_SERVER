package com.swqs.schooltrade.util;

import java.util.Map;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class PushToClient {

	public static void sendPush(String title, String content, String alias, Map<String, String> extra) {
		String MASTER_SECRET = "8ea0c0339a86747b97f5024c";
		String APP_KEY = "f6d10137deeefed4339d2888";
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(3);
		config.setConnectionTimeout(5000);
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
		try {
			// 构建一个推送对象
			PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.android())
					.setAudience(Audience.alias(alias)).setNotification(Notification.android(content, title, extra))
					.build();
			// 开始推送
			PushResult result = jpushClient.sendPush(pushPayload);
			boolean state = result.isResultOK();
			if (state) {
				System.out.println("成功");
			} else {
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
