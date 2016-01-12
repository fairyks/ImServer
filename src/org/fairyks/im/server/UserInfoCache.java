/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月12日 上午10:12:37</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class UserInfoCache {
	
	//处理登录，登录用户名和远程地址组成map
	private static final Map<String, Channel> connectedClient = new ConcurrentHashMap<String,Channel>();

	/**
	 * @return the connectedclient
	 */
	public static Map<String, Channel> getConnectedclient() {
		return connectedClient;
	}
		
}
