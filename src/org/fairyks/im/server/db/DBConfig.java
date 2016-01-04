/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.db;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:19:39</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class DBConfig {
	// 连接池属性
		private String driver;
		private String url;
		private String userName;
		private String password;
		// 连接池名称
		private String poolName;
		private int maxCon;
		private int minCon;

		/**
		 * 
		 * @param url
		 * @param userName
		 * @param password
		 * @param poolName
		 * @param maxCon
		 * @param minCon
		 */
		public DBConfig(String driver, String url, String userName, String password, String poolName, int maxCon, int minCon) {
			this.driver = driver;
			this.url = url;
			this.userName = userName;
			this.password = password;
			this.poolName = poolName;
			this.maxCon = maxCon;
			this.minCon = minCon;
		}

		public DBConfig() {
		}

		/**
		 * @return the driver
		 */
		public String getDriver() {
			return driver;
		}

		/**
		 * @param driver the driver to set
		 */
		public void setDriver(String driver) {
			this.driver = driver;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		/**
		 * @return the userName
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * @param userName the userName to set
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the poolName
		 */
		public String getPoolName() {
			return poolName;
		}

		/**
		 * @param poolName the poolName to set
		 */
		public void setPoolName(String poolName) {
			this.poolName = poolName;
		}

		/**
		 * @return the maxCon
		 */
		public int getMaxCon() {
			return maxCon;
		}

		/**
		 * @param maxCon the maxCon to set
		 */
		public void setMaxCon(int maxCon) {
			this.maxCon = maxCon;
		}

		/**
		 * @return the minCon
		 */
		public int getMinCon() {
			return minCon;
		}

		/**
		 * @param minCon the minCon to set
		 */
		public void setMinCon(int minCon) {
			this.minCon = minCon;
		}
		
}
