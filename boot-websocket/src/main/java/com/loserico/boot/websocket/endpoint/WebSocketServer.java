package com.loserico.boot.websocket.endpoint;

import com.loserico.boot.annotation.RedisListener;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Copyright: (C), 2020-7-23 0023 16:39
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Slf4j
@Component
@ServerEndpoint("/ws/asset")
public class WebSocketServer {
	
	private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
	
	/**
	 * concurrent包的线程安全Set, 用来存放每个客户端对应的Session对象
	 */
	private static final CopyOnWriteArraySet<Session> SESSIONS = new CopyOnWriteArraySet<>();
	
	@PostConstruct
	public void init() {
		System.out.println("websocket 加载");
	}
	
	/**
	 * 连接建立成功调用的方法
	 *
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		SESSIONS.add(session);
		int count = ONLINE_COUNT.incrementAndGet();
		log.info("有连接加入, 当前连接数为：{}", count);
	}
	
	/**
	 * 连接关闭调用的方法
	 *
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		SESSIONS.remove(session);
		int count = ONLINE_COUNT.decrementAndGet();
		log.info("有连接关闭, 当前连接数为：{}", count);
	}
	
	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端的消息：{}", message);
		sendMessage(session, "收到消息，消息内容：" + message);
	}
	
	/**
	 * 发送消息. 每次浏览器刷新, session会发生变化。
	 *
	 * @param session
	 * @param message
	 */
	@SneakyThrows
	private void sendMessage(Session session, String message) {
		session.getBasicRemote().sendText(String.format("%s (From Server, Session ID=%s)", message, session.getId()));
		//session.getBasicRemote().sendText(message);
	}
	
	/**
	 * 群发消息
	 *
	 * @param message
	 */
	@RedisListener(channels = "inbound")
	public void broadCastMessage(String channel, String message) {
		for (Session session : SESSIONS) {
			if (session.isOpen()) {
				sendMessage(session, message);
			}
		}
	}
	
	/**
	 * 指定Session发送消息
	 * 
	 * @param message
	 * @param sessionId
	 */
	private void sendMessage(String message, String sessionId) {
		Session session = null;
		for (Session s : SESSIONS) {
			if (s.getId().equals(sessionId)) {
				session = s;
				break;
			}
		}
		
		if (session != null) {
			sendMessage(session, message);
		} else {
			log.warn("没有找到你指定ID的会话：{}", sessionId);
		}
	}
}
