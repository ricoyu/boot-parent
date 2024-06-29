package com.loserico.rpc.rpc;

import com.loserico.rpc.base.RegisterService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * RPC框架服务端部分
 * <p>
 * Copyright: (C), 2023-10-30 16:54
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class RpcServerFrame {
	
	@Autowired
	private RegisterService registerService;
	
	private int port;
	
	public void startService(String serviceName, String host, int port, Class impl) throws IOException {
		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress(port));
		System.out.println("RPC Server on: " + port + " 运行");
		registerService.regService(serviceName, impl);
		try {
			while (true) {
				Socket socket = server.accept();
				new ServerTask(socket, registerService).run();
			}
		} finally {
			server.close();
		}
	}
	
	private static class ServerTask implements Runnable {
		
		private Socket client;
		
		private RegisterService registerService;
		
		public ServerTask(Socket client, RegisterService registerService) {
			this.client = client;
			this.registerService = registerService;
		}
		
		@SneakyThrows
		@Override
		public void run() {
			try (ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
			     ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream())) {
				String serviceName = inputStream.readUTF();
				String methodName = inputStream.readUTF();
				System.out.println("收到远程调用请求: " + serviceName + "." + methodName + "()");
				//方法入参的类型
				Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
				//方法入参的实际值
				Object[] params = (Object[]) inputStream.readObject();
				
				Class localService = registerService.getLocalService(serviceName);
				if (localService == null) {
					throw new ClassNotFoundException(serviceName + " 服务未找到");
				}
				Object result = localService.getMethod(methodName, paramTypes).invoke(localService.newInstance(), params);
				
				outputStream.writeObject(result);
				outputStream.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				client.close();
			}
		}
	}
}
