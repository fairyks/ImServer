/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

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

public class SessionManager {
	
//	消息结尾符
	public static final String MESSAGE_SEPERATOR = "\n"; 
	
	//处理登录，登录用户名和远程地址组成map
	private static final Map<String, Channel> connectedClient = new ConcurrentHashMap<String,Channel>();
	private static final Map<String, ChannelId> connectedClientId = new ConcurrentHashMap<String,ChannelId>();
	private static final Map<String, String> connectedClientAddress = new ConcurrentHashMap<String,String>();
	private static final Set<String> remoteAddress = new ConcurrentSkipListSet<String>(); 

	/**
	 * @return the connectedclient
	 */
	public static Map<String, Channel> getConnectedclient() {
		return connectedClient;
	}

	/**
	 * 
	 * 方法描述 : 保存客户端连接地址信息和channel信息
	 * @return
	 */
	public static Map<String, ChannelId> getConnectedclientid() {
		return connectedClientId;
	}

	/**
	 * 
	 * 方法描述 : 保存用户账户登录信息和客户端连接地址信息
	 * @return
	 */
	public static Map<String, String> getConnectedclientaddress() {
		return connectedClientAddress;
	}

	/**
	 * @return the remoteaddress
	 */
	public static Set<String> getRemoteaddress() {
		return remoteAddress;
	}
	
}
