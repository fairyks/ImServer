/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月8日 上午9:49:25</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DBUtilConnectionManager {

	static Connection connection = null;
	private static DBUtilConnectionManager dBConnectionManager;
	
	private DBUtilConnectionManager(){}
	
	public static DBUtilConnectionManager getInstance(){
		if (dBConnectionManager!=null) {
			return dBConnectionManager;
		} else {
			dBConnectionManager = new DBUtilConnectionManager();
			return dBConnectionManager;
		}
	}
	
	public Connection getDBconnection(){
		connection = DBUtil.getConnection();
		return connection;
	}
	
	public void close(Connection conn,PreparedStatement pst,ResultSet rs){ 
		DBUtil.close(conn, pst, rs);
	}
}
