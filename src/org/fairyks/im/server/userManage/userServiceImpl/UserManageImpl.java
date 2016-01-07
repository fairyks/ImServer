/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.userManage.userServiceImpl;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.fairyks.im.server.bean.User;
import org.fairyks.im.server.db.DBOperator;
import org.fairyks.im.server.db.DataRepositoryFactory;
import org.fairyks.im.server.db.IDataRepository;
import org.fairyks.im.server.userManage.userService.UserManageService;

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
		IDataRepository dataRepository = null;
		DBOperator dBOperator = null;
		ResultSet rs = null;
		String token = null;
		String sql = "INSERT INTO im_user (user_name,plain_password,nick_name,creation_date,modification_date) VALUES (?,?,?,?,?)";
		try {
			dataRepository = DataRepositoryFactory.getInstance().getDataRepository("IMPool");
			dBOperator = dataRepository.getDBOperator();
			rs = dBOperator.executeQuery(sql.toString());
		} catch (Exception e) {
			logger.error("register user " + sql + "  /error", e);
		} finally {
//			closeConnDB(dataRepository);
		}
		return false;
	}

	/**
	 * <h4>  </h4>
	 * @see org.fairyks.im.server.userManage.userService.UserManageService#findUserByUserNameAndPassWord(java.lang.String, java.lang.String)
	 * @param userName
	 * @param password
	 * @return
	 * @throws 
	 */
	@Override
	public boolean findUserByUserNameAndPassWord(String userName, String password) {
		
		
		return false;
	}

}
