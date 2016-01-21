/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.message.service;

import java.util.List;

import org.fairyks.im.server.bean.User;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月20日 下午3:54:01</p>
 * <p>类描述 :     消息分发处理    </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public interface MessageService {

	/**
	 * 
	 * 方法描述 : 搜索用户,供添加好友使用
	 * @param userAccount
	 * @return
	 */
	public List<User> searchNewFriend(String userAccount);
}
