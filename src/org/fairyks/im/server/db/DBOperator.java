/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:25:24</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DBOperator {

	/** 数据库连接类实例 */
	private DBConnectionManager manager = null;

	/** 数据库连接 */
	private Connection connection = null;

	private String name;

	/** 声明 */
	private Statement hState = null;

	public DBOperator(String name) throws SQLException {
		this.name = name;
		init(name);
	}

	/**
	 * 初始化连接
	 * 
	 * @throws SQLException
	 * 
	 * @since 0.1
	 */
	private void init(String name) throws SQLException {
		/** 建立并获得一个数据库连接 */
		try {
			manager = DBConnectionManager.getInstance();
			connection = manager.getConnection(name);
			/** 生成一个statment */
			hState = connection.createStatement();
			if (hState == null)
				throw new SQLException("Statement is null,please check connection");
		} catch (SQLException e) {
			throw new SQLException(e.getMessage(), e);
		}
	}

	/**
	 * 执行SQL语句(插入，更新?
	 * <p>
	 * 针对Statment
	 * </p>
	 * ?
	 * 
	 * @param sqlString
	 *            SQL语句字符串
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public void executeUpdate(String sqlString) throws SQLException {
		hState.executeUpdate(sqlString);
	}

	/**
	 * 执行SQL语句(查询）
	 * <p>
	 * 针对Statement
	 * </p>
	 * 
	 * @param sqlString
	 *            SQL语句字符串
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public ResultSet executeQuery(String sqlString) throws SQLException {
		return hState.executeQuery(sqlString);
	}

	/**
	 * 获得结果集的MetaData
	 * 
	 * @param sqlString
	 *            SQL语句字符串
	 * @throws SQLException
	 * @since 0.1
	 */
	public ResultSetMetaData getMetaDate(String sqlString) throws SQLException {
		ResultSet rs = hState.executeQuery(sqlString);
		return rs.getMetaData();
	}

	/**
	 * 使用PreparedStatement
	 * 
	 * @param sqlString
	 *            SQL语句字符串
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public PreparedStatement execute(String sqlString) throws SQLException {
		return connection.prepareStatement(sqlString);
	}

	/**
	 * 调用存储过程,无返回值
	 * 
	 * @param procName
	 *            存储过程字传
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public CallableStatement callProc(String procName) throws SQLException {
		String callProc = "{Call " + procName.trim() + "}";
		return connection.prepareCall(callProc);
	}

	/**
	 * 调用存储过程,获得系统返回
	 * 
	 * @param procName
	 *            存储过程字传
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public CallableStatement callProcRet(String procName) throws SQLException {
		String callProc = "{?=Call " + procName.trim() + "}";
		return connection.prepareCall(callProc);
	}

	/**
	 * 手动提交
	 * 
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public void commit() throws SQLException {
		connection.commit();
	}

	/**
	 * 手动回滚
	 * 
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public void rollback() throws SQLException {
		connection.rollback();
	}

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	/**
	 * 结束事务
	 * 
	 * @throws SQLException
	 */
	public void endTransaction() throws SQLException {
		connection.setAutoCommit(true);
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @throws SQLException
	 *             调用时需要抛出改异常
	 * @since 0.1
	 */
	public void close() throws SQLException {
		manager.close(name, connection);
	}
}
