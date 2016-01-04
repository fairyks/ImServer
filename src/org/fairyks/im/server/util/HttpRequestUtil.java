/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.util;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午3:34:23</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class HttpRequestUtil {

	private static HttpRequestUtil httpRequestUtil;

	private HttpRequestUtil() {
	}

	public static HttpRequestUtil getInstance() {
		if (httpRequestUtil != null) {
			return httpRequestUtil;
		} else {
			httpRequestUtil = new HttpRequestUtil();
			return httpRequestUtil;
		}
	}

	private static final Logger logger = Logger.getLogger(HttpRequestUtil.class);

	public static String getRequestData(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			InputStream inputStream = request.getInputStream();
			String requestData = IOUtils.toString(inputStream);
			return requestData;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fail to get RequestData,the reason is " + e.getMessage().toString());
			return null;
		}
	}

}
