/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.SQLException;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:26:56</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DataRepositoryFactory {

	private static class SingletonContainer {
		private static DataRepositoryFactory instance = new DataRepositoryFactory();
	}

	public static DataRepositoryFactory getInstance() {
		return SingletonContainer.instance;
	}

	private DataRepositoryFactory() {

	}

	/**
	 * 获取DataRepository
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public IDataRepository getDataRepository(String name) throws SQLException {
		DBConnectionPool pool = DBConnectionManager.getInstance().getPool(name);
		if (pool != null && true) {
			return new MysqlDataRepositoryImpl(name);
		} else {
			return new OracleDataRepositoryImpl();
		}
	}
}
