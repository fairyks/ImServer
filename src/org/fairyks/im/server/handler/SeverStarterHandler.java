/**
 * Copyright  陈延军 All rights reserved.
 */
package org.fairyks.im.server.handler;

import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
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

	//处理登录，登录用户名和远程地址组成map
	Map<String, Channel> map = new ConcurrentHashMap<String,Channel>();
	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		// Once session is secured, send a greeting and register the channel to the global channel
		// list so the channel received the messages from others.
		ctx.pipeline().get(SslHandler.class).handshakeFuture()
				.addListener(new GenericFutureListener<Future<Channel>>() {
					public void operationComplete(Future<Channel> future) throws Exception {
//						ctx.writeAndFlush(
//								"Welcome to " + InetAddress.getLocalHost().getHostName() + " chat service!\n");
//						ctx.writeAndFlush("Your session is protected by "
//								+ ctx.pipeline().get(SslHandler.class).engine().getSession().getCipherSuite()
//								+ " cipher suite.\n");
						
						map.put(ctx.channel().remoteAddress().toString(), ctx.channel());

						channels.add(ctx.channel());
					}
				});
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		// Send the received message to all channels but the current one.
		for (Channel c : channels) {
			if (c != ctx.channel()) {
//				map.get(ctx.channel().remoteAddress()).writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + '\n');
				c.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + '\n');
			} 
			/*else {
				c.writeAndFlush(ctx.channel().remoteAddress()+ "说" + msg + '\n');
				System.out.println(ctx.channel().remoteAddress());
//				System.out.println(ctx.channel().id());
			}*/
		}

		// Close the connection if the client has sent 'bye'.
		if ("bye".equals(msg.toLowerCase())) {
			ctx.close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}