/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:21:39</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DBConnectionPool {

	private ComboPooledDataSource dataSource;

	public DBConnectionPool(DBConfig dbBean) throws Exception {
		dataSource = new ComboPooledDataSource();
		dataSource.setDataSourceName(dbBean.getPoolName());
		dataSource.setDriverClass(dbBean.getDriver());
		dataSource.setJdbcUrl(dbBean.getUrl());
		dataSource.setUser(dbBean.getUserName());
		dataSource.setPassword(dbBean.getPassword());
		//连接池中保留的最大连接数。Default: 15
		dataSource.setMaxPoolSize(1);
		//连接池中保留的最小连接数。
		dataSource.setMinPoolSize(1);
		//初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间。Default: 3 
		dataSource.setInitialPoolSize(1);
		//如果设为true那么在取得连接的同时将校验连接的有效性。
		dataSource.setTestConnectionOnCheckin(true);
		//每10秒检查所有连接池中的空闲连接。Default: 0
		dataSource.setIdleConnectionTestPeriod(1);
		dataSource.setAutomaticTestTable("c3p0TestTable");
		//最大空闲时间,10秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 
		dataSource.setMaxIdleTime(3);
		dataSource.setTestConnectionOnCheckout(true);
	}

	// 获得连接
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	// 释放连接
	public void releaseConn(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	// 销毁连接池
	public synchronized void destroy() {
		if (dataSource != null) {
			dataSource.close();
		}
	}
}
