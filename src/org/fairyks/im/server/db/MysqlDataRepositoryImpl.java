/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:28:32</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class MysqlDataRepositoryImpl implements IDataRepository {

	private static final Logger logger = Logger.getLogger(MysqlDataRepositoryImpl.class.getName());
	public DBOperator dbOperator;

	public MysqlDataRepositoryImpl(String name) throws SQLException {
		dbOperator = new DBOperator(name);
	}

	public DBOperator getDBOperator() {
		return this.dbOperator;
	}

	// 提交事务
	public void commitTransaction() throws SQLException {
		dbOperator.commit();
	}

	// 回滚事务
	public void rollbackTransaction() throws SQLException {
		dbOperator.rollback();
	}

	// 开始事务
	public void startTransaction() throws SQLException {
		dbOperator.startTransaction();
	}

	// 结束事务
	public void endTransaction() throws SQLException {
		dbOperator.endTransaction();
	}

	// 关闭连接
	public void closeConnection() throws SQLException {
		dbOperator.close();
		dbOperator = null;
	}

}
