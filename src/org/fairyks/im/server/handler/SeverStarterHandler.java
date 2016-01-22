/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.handler;

import java.net.InetAddress;
import java.util.List;

import org.eclipse.jetty.server.session.JDBCSessionManager.Session;
import org.fairyks.im.server.bean.Packet;
import org.fairyks.im.server.bean.User;
import org.fairyks.im.server.message.service.MessageService;
import org.fairyks.im.server.message.service.serviceImpl.MessageServiceImpl;
import org.fairyks.im.server.util.MessageProtocal;
import org.fairyks.im.server.util.SessionManager;

import com.google.gson.Gson;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :陈延军 </p>
 * <p>项目名称 : MyServer</p>
 * <p>创建时间 : 2015年12月24日 下午1:30:48</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">陈延军</a>
 */

public class SeverStarterHandler extends SimpleChannelInboundHandler<String> {

	static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	static MessageService messageService = null;
	static Gson gson = null;

	static {
		messageService = new MessageServiceImpl();
		gson = new Gson();
	}

	@Override
	public void channelActive(final ChannelHandlerContext context) {
		context.pipeline().get(SslHandler.class).handshakeFuture()
				.addListener(new GenericFutureListener<Future<Channel>>() {
					public void operationComplete(Future<Channel> future) throws Exception {
						System.out
								.println("Welcome to " + InetAddress.getLocalHost().getHostName() + " chat service!\n");
						SessionManager.getRemoteaddress().add(context.channel().remoteAddress().toString());
						SessionManager.getConnectedclientid().put(context.channel().remoteAddress().toString(),
								context.channel().id());
						System.out.println(context.channel().id().toString());
						channels.add(context.channel());
						//TODO: 要加入上线通知功能
					}
				});
	}

	@Override
	public void messageReceived(ChannelHandlerContext context, String message) throws Exception {
		try {
			//		发送数据时,必须在最后加上回车符号"/n",类似这种格式
			//		channels.find(context.channel().id()).writeAndFlush(gson.toJson(packet) + "\n");
			//		channels.find(context.channel().id()).writeAndFlush(context.channel().remoteAddress()+ "说" + message + "\n");

			//		msg的协议，根据情况，进行消息转发
			Packet packet = gson.fromJson(message, Packet.class);
			switch (packet.getType()) {
				case MessageProtocal.PRESENCE_ONLINE:
					if (SessionManager.getRemoteaddress().contains(context.channel().remoteAddress().toString())) {
						//此处要将登陆用户的信息，和channl信息进行绑定
						SessionManager.getConnectedclientaddress().put(packet.getFrom(),
								context.channel().remoteAddress().toString());
						//取得特定用户channelId的方式,在向特定用户发送信息的时候,需要这个channelId
						ChannelId id = SessionManager.getConnectedclientid()
								.get(SessionManager.getConnectedclientaddress().get(packet.getFrom()));
						packet.setLoginFlag(true);
						channels.find(id).writeAndFlush(gson.toJson(packet) + SessionManager.MESSAGE_SEPERATOR);
					}					
					break;
				case MessageProtocal.PRESENCE_OFFLINE:
	
					break;
				case MessageProtocal.IQ_SEARCH_FRIEND:
					List<User> result = messageService.searchNewFriend(packet.getTo());
					Packet resultPacket = new Packet();
					resultPacket.setList(result);
					resultPacket.setType(MessageProtocal.IQ_SEARCH_FRIEND);
					channels.find(context.channel().id()).writeAndFlush(gson.toJson(packet) + SessionManager.MESSAGE_SEPERATOR);
					break;
				case MessageProtocal.IQ_ADD_FRIEND:
	
					break;
				case MessageProtocal.IQ_DELETE_FRIEND:
	
					break;
				case MessageProtocal.CHAT:
					break;
				case MessageProtocal.GROUP_CHAT:
					break;
				default:
					break;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}