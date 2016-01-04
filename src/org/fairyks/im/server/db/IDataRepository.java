/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.SQLException;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:27:29</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public interface IDataRepository {

	public DBOperator getDBOperator();

	/**
	 * 
	 * @throws SQLException
	 */
	public void commitTransaction() throws SQLException;

	/**
	 * 
	 * @throws SQLException
	 */
	public void rollbackTransaction() throws SQLException;

	/**
	 * 
	 * @throws SQLException
	 */
	public void startTransaction() throws SQLException;

	/**
	 * 
	 * @throws SQLException
	 */
	public void endTransaction() throws SQLException;

	/**
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException;
	
}
