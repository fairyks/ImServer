/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.message.service.serviceImpl;

import java.util.List;

import org.fairyks.im.server.bean.User;
import org.fairyks.im.server.message.service.MessageService;
import org.fairyks.im.server.userManage.userService.UserManageService;
import org.fairyks.im.server.userManage.userServiceImpl.UserManageImpl;

import com.google.gson.Gson;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月20日 下午3:57:09</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class MessageServiceImpl implements MessageService {

	private Gson gson = new Gson();
	private static UserManageService userManager;
	
	static{
		userManager = new UserManageImpl();
	}
	/**
	 * <h4> 搜索用户,供添加好友使用 </h4>
	 * @see org.fairyks.im.server.message.service.MessageService#searchNewFriend(java.lang.String)
	 * @param userAccount
	 * @return
	 * @throws 
	 */
	@Override
	public List<User>  searchNewFriend(String userAccount) {
		List<User> list = userManager.searchNewFriend(userAccount);
		return list;
	}

}
