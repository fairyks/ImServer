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
 * <p>创建时间 : 2016年1月4日 下午3:29:27</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class OracleDataRepositoryImpl implements IDataRepository {

	private static final Logger logger = Logger.getLogger(OracleDataRepositoryImpl.class.getName());

	@Override
	public void commitTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollbackTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endTransaction() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startTransaction() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public DBOperator getDBOperator() {
		return null;
	}
}
