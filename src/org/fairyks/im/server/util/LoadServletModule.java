/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月8日 下午2:41:11</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class LoadServletModule {
	
	public static ConcurrentHashMap<String, String> servletModulePool = new ConcurrentHashMap<String, String>();

	/**
	 * @return the servletModulePool
	 */
	public static ConcurrentHashMap<String, String> getServletModulePool() {
		return servletModulePool;
	}

	private LoadServletModule() {}

	public static LoadServletModule getInstance() {
		return Singtonle.instance;
	}

	private static class Singtonle {
		private static LoadServletModule instance = new LoadServletModule();
	}

	public void init() {
		InputStream in = LoadServletModule.class.getClassLoader().getResourceAsStream("ServletModuleConfig.xml");
		initModulePools(in);
	}

	private void initModulePools(InputStream in) {
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(in);
			Element rootElt = document.getRootElement();
			Iterator iter = rootElt.elementIterator("bean");
			while (iter.hasNext()) {
				Element moduleNameEle = (Element) iter.next();
				String moduleName = moduleNameEle.elementTextTrim("moduleName");
				String path = moduleNameEle.elementTextTrim("path");
				servletModulePool.put(moduleName, path);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}