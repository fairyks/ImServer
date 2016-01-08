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

import org.apache.commons.lang.StringUtils;
import org.fairyks.im.server.bean.ResponseBean;
import org.fairyks.im.server.bean.User;
import org.fairyks.im.server.userManage.userService.UserManageService;
import org.fairyks.im.server.userManage.userServiceImpl.UserManageImpl;
import org.fairyks.im.server.util.HttpRequestUtil;
import org.fairyks.im.server.util.HttpResponseUtils;
import org.fairyks.im.server.util.UserNameGenerator;

import com.google.gson.Gson;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : ImServer</p>
 * <p>创建时间 : 2016年1月8日 上午10:13:21</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */
@WebServlet(urlPatterns = { "/userManageAction" }, asyncSupported = true)
//@WebServlet("/userManageAction")
public class UserManageServlet extends HttpServlet {

	/** 
	 * serialVersionUID :  
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * <h4>  </h4>
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * <h4>  </h4>
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		ResponseBean bean = new ResponseBean();
		UserManageService userManageService = new UserManageImpl();
		String userData = HttpRequestUtil.getInstance().getRequestData(request);
		User user = gson.fromJson(userData, User.class);
		switch (user.getType()) {
		case "addUser":
			String userName = UserNameGenerator.getUserName();
			user.setUserName(userName);
			boolean flag = userManageService.registerUser(user);
			bean.setFlag(flag);
			bean.setUserId(userName);
			break;
		case "login":
			String nickName = userManageService.findUserByUserNameAndPassWord(user.getUserName(), user.getPlainPassword());
			if (StringUtils.isNotEmpty(nickName)) {
				bean.setFlag(true);
				bean.setNickName(nickName);
			}else {
				bean.setFlag(false);
				bean.setNickName(nickName);
			}
			break;
		default:
			break;
		}
		HttpResponseUtils.renderJson(response, gson.toJson(bean));
	}
}
