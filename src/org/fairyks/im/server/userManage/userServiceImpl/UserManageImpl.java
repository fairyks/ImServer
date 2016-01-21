/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.userManage.userServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.fairyks.im.server.bean.User;
import org.fairyks.im.server.db.DBUtilConnectionManager;
import org.fairyks.im.server.userManage.userService.UserManageService;
import org.fairyks.im.server.util.UserNameGenerator;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月7日 下午4:43:16</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class UserManageImpl implements UserManageService {

	private static Logger logger = Logger.getLogger(UserManageImpl.class);

	/**
	 * <h4>  </h4>
	 * @see org.fairyks.im.server.userManage.userService.UserManageService#registerUser(org.fairyks.im.server.bean.User)
	 * @param user
	 * @return
	 * @throws 
	 */
	@Override
	public boolean registerUser(User user) {
		boolean flag = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO im_user (user_name,plain_password,nick_name,creation_date,modification_date) VALUES (?,?,?,?,?)";
		try {
			connection = DBUtilConnectionManager.getInstance().getDBconnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPlainPassword());
			preparedStatement.setString(3, user.getNickName());
			preparedStatement.setString(4, user.getCreationdate());
			preparedStatement.setString(5, user.getModificationDate());
			preparedStatement.execute();
			connection.commit();
			flag = true;
		} catch (Exception e) {
			logger.error("register user " + sql + "  /error", e);
			flag = false;
		} finally {
			DBUtilConnectionManager.getInstance().close(connection, preparedStatement, null);
		}
		return flag;
	}

	/**
	 * <h4>  </h4>
	 * @see org.fairyks.im.server.userManage.userService.UserManageService#findUserByUserNameAndPassWord(java.lang.String, java.lang.String)
	 * @param userName
	 * @param password
	 * @return String
	 * @throws 
	 */
	@Override
	public String findUserByUserNameAndPassWord(String userName, String password) {
		String nick_name = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select nick_name from im_user where user_name = ? and plain_password = ?";
		try {
			connection = DBUtilConnectionManager.getInstance().getDBconnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				nick_name = resultSet.getString("nick_name");
			}
		} catch (Exception e) {
			logger.error("register user " + sql + "  /error", e);
		} finally {
			DBUtilConnectionManager.getInstance().close(connection, preparedStatement, null);
		}
		return nick_name;
	}

	/**
	 * <h4>  </h4>
	 * @see org.fairyks.im.server.userManage.userService.UserManageService#searchNewFriend(java.lang.String)
	 * @param userAccount
	 * @return
	 * @throws 
	 */
	@Override
	public List<User> searchNewFriend(String userAccount) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select user_name, nick_name from im_user where user_name = ? or nick_name = ?";
		List<User> list = new ArrayList<User>();
		try {
			connection = DBUtilConnectionManager.getInstance().getDBconnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userAccount);
			preparedStatement.setString(2, userAccount);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setUserName(resultSet.getString("user_name"));
				user.setNickName(resultSet.getString("nick_name"));
				list.add(user);
			}
		} catch (Exception e) {
			logger.error("search user " + sql + "  /error", e);
		} finally {
			DBUtilConnectionManager.getInstance().close(connection, preparedStatement, null);
		}
		return list;
	}

}
