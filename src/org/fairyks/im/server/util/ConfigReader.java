/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:05:51</p>
 * <p>类描述 :     配置文件阅读器    </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class ConfigReader {
	
		public static String is_encrypt="false";
		public static String encrypt="false";
		
		static{
			is_encrypt=ConfigReader.getValue("config.properties", "IS_ENCRYPT");
		}
		
		public static String getValue(String configfile,String key){
			String value=null;
			if(!StringUtils.isBlank(configfile)){
				InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(configfile);
				Properties p = new Properties();
				try{
				    p.load(inputStream);
				    value=p.getProperty(key,null);
				} catch (IOException e1){
				   e1.printStackTrace();
				}
			}
			return value;
		}
		
	   public static Properties getProperties(String configfile){
			Properties p=null;
			if(!StringUtils.isEmpty(configfile)){
				InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(configfile);
				p = new Properties();
				try{
				    p.load(inputStream);
				   
				} catch (IOException e){
				   e.printStackTrace();
				}
			}
			return p;
		}
}