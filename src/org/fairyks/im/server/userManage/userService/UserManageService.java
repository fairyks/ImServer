/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.userManage.userService;

import java.util.List;

import org.fairyks.im.server.bean.User;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月7日 下午4:39:58</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public interface UserManageService {

	/**
	 * 
	 * 方法描述 : 注册用户
	 * @param user
	 * @return true:成功;false:失败
	 */
	public boolean registerUser(User user);
	
	/**
	 * 
	 * 方法描述 : 根据用户名密码查找用户
	 * @param userName 用户名
	 * @param password 密码
	 * @return true:成功;false:失败
	 */
	public String findUserByUserNameAndPassWord(String userName,String password);

	/**
	 * 方法描述 : 搜索用户,供添加好友使用
	 * @param userAccount
	 * @return
	 */
	public List<User> searchNewFriend(String userAccount);
	
	
}
