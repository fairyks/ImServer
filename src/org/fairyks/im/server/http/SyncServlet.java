/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fairyks.im.server.bean.Packet;
import org.fairyks.im.server.util.HttpRequestUtil;
import org.fairyks.im.server.util.HttpResponseUtils;

import com.google.gson.Gson;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月4日 下午1:21:58</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */
@WebServlet(urlPatterns = { "/syncAction" }, asyncSupported = true)
//@WebServlet("/syncAction")
public class SyncServlet extends HttpServlet {

	/** 
	 * serialVersionUID :  
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		doPost(request, response);
	};
	/**
	 * <h4>  </h4>
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String packatMetaData = HttpRequestUtil.getInstance().getRequestData(request);
		Packet packet = gson.fromJson(packatMetaData, Packet.class);
		
		
		// deal business
		HttpResponseUtils.renderJson(response, gson.toJson(packet));
	}

}
