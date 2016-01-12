/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.http;

import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.fairyks.im.server.util.ConfigReader;
import org.fairyks.im.server.util.LoadServletModule;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午2:51:28</p>
 * <p>类描述 :     Web服务启动器    </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class HttpServerStarter implements Runnable {
//private static HttpServerStarter httpServerStarter;
//	
//	private HttpServerStarter(){}
//	
//	public static HttpServerStarter getInstance() {
//		if(httpServerStarter!=null){
//			return httpServerStarter;
//		}else {
//			httpServerStarter = new HttpServerStarter();
//			return httpServerStarter;
//		}
//	}
	public void run() {
			try{
				ResourceHandler resourceHandler = new ResourceHandler();
			    resourceHandler.setDirectoriesListed(true);
			    resourceHandler.setResourceBase("data");
			    resourceHandler.setStylesheet("");
				    
			    ContextHandler staticContextHandler = new ContextHandler();
			    staticContextHandler.setContextPath("/data");
			    //staticContextHandler.setContextPath("/files");
			    staticContextHandler.setHandler(resourceHandler);
				  
				//添加servlet支持
				ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
				servletContextHandler.setContextPath("/imServer");
				
				 LoadServletModule.getInstance().init();
				 for (String key : LoadServletModule.getServletModulePool().keySet()) {
					   servletContextHandler.addServlet(new ServletHolder((HttpServlet) Class.forName(LoadServletModule.getServletModulePool().get(key)).newInstance()),  "/"+key);
					  }
				     
			    /**可在下面依次添加多个servlet**/
//			    servletContextHandler.addServlet(new ServletHolder(new SyncServlet()), "/syncAction");
//			    servletContextHandler.addServlet(new ServletHolder(new UserManageServlet()), "/userManageAction");
			    
			    HandlerList handlers = new HandlerList();
			    handlers.addHandler(servletContextHandler);
			    handlers.addHandler(staticContextHandler);
			    
			    Server server = new Server(Integer.parseInt(ConfigReader.getValue("config.properties", "HTTP_SERVER_PORT")));
			    // 设置线程池     
		/*	    ThreadPool  threadPool = new ExecutorThreadPool(Executors.newFixedThreadPool(5));
			    server.getThreadPool().
				  // 设置连接参数     
				  Connector connector = new SelectChannelConnector();     
			   // 设置监听端口     
			   connector.setPort(hosts);     
			   // 为服务设置连接器     
			   server.setConnectors(new Connector[] { connector }); 
			   */	
//		    server.setHandler(servletContextHandler);
			    server.setHandler(handlers);
				server.start();
				server.join(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
} 