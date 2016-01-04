/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.fairyks.im.server.util.ConfigReader;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:20:41</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DBConnectionManager {
		// 配置文件名
		public static String PROPS_FILE_PATH;
		// 连接池存放
		public Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();

		// 初始化
		private DBConnectionManager() {
			try {
				init();
			} catch (IOException e) {
			}
		}

		// 单例实现
		public static DBConnectionManager getInstance() {
			return Singtonle.instance;
		}

		private static class Singtonle {
			private static DBConnectionManager instance = new DBConnectionManager();
		}

		// 获得连接,根据连接池名字 获得连接
		public Connection getConnection(String poolName) {

			Connection conn = null;
			if (pools.size() > 0 && pools.containsKey(poolName)) {
				conn = getPool(poolName).getConnection();
			} else {
				System.out.println("Error:Can't find this connecion pool ->" + poolName);
			}
			//System.out.println("on---------"+SearchAllQuestionsAction.n++);
			return conn;
		}

		// 关闭，回收连接
		public void close(String poolName, Connection conn) throws SQLException {

			DBConnectionPool pool = getPool(poolName);
			if (pool != null) {
				pool.releaseConn(conn);
			}
			//System.out.println("close--------"+SearchAllQuestionsAction.i++);
		}

		// 清空连接池
		public void destroy(String poolName) {
			DBConnectionPool pool = getPool(poolName);
			if (pool != null) {
				pool.destroy();
			}
		}

		// 获得连接池
		public DBConnectionPool getPool(String poolName) {
			DBConnectionPool pool = null;
			if (pools.size() > 0) {
				pool = pools.get(poolName);
			}
			return pool;
		}

		/**
		 * 读取属性完成初始化
		 * 
		 * @param propsFilePath
		 *            文件路径
		 * @since 0.1
		 */
		private void init() throws IOException {
			Properties dbProp = ConfigReader.getProperties("dbconfig.properties");
			createPools(dbProp);
		}

		/**
		 * 根据指定属性创建连接池实例.
		 * 
		 * @param name
		 *            连接池名称
		 * @param max
		 *            最大连接数
		 * @param user
		 *            连接用户名
		 * @param pwd
		 *            连接密码
		 * @param url
		 *            连接url
		 * @since 0.1
		 */
		private void createPools(Properties prop) {
			Enumeration<?> propNames = prop.propertyNames();
			while (propNames.hasMoreElements()) {
				String name = (String) propNames.nextElement();

				if (name.endsWith(".driver")) {
					String poolName = name.substring(0, name.lastIndexOf("."));
					String driver = prop.getProperty(poolName + ".driver");
					if (driver == null) {
						System.out.println("don't specify driver for pool!");
						continue;
					}
					String url = prop.getProperty(poolName + ".url");
					String user = prop.getProperty(poolName + ".user");
					String password = prop.getProperty(poolName + ".password");
					String maxconn = prop.getProperty(poolName + ".maxconn", "50");
					String minconn = prop.getProperty(poolName + ".minconn", "10");

					int max = 50;
					int min = 10;
					try {
						max = Integer.parseInt(maxconn);
					} catch (NumberFormatException ex) {
						max = 50;
					}
					try {
						min = Integer.parseInt(minconn);
					} catch (NumberFormatException ex) {
						min = 50;
					}
					DBConfig config = new DBConfig(driver, url, user, password, poolName, max, min);
					try {
						DBConnectionPool pool = new DBConnectionPool(config);
						pools.put(poolName, pool);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
}
