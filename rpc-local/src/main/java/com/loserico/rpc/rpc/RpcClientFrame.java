package com.loserico.rpc.rpc;

import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

/**
 * <p>
 * Copyright: (C), 2023-10-30 17:43
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
@Service
public class RpcClientFrame {
	
	private static Random random = new Random();
	
	public static<T> T getRemoteProxyObj(final Class<?> serviceInterface) {
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8778);
		return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), 
				new Class<?>[]{serviceInterface}, 
				new SmsServiceProxy(address, serviceInterface));
	}
	
	/**
	 * 动态代理, 实现对远程服务的调用
	 */
	private static class SmsServiceProxy implements InvocationHandler {
		
		private InetSocketAddress address;
		private Class<?> serviceInterface;
		
		public SmsServiceProxy(InetSocketAddress address, Class<?> serviceInterface) {
			this.address = address;
			this.serviceInterface = serviceInterface;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Socket socket = null;
			ObjectInputStream inputStream = null;
			ObjectOutputStream outputStream = null;
			InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8778);
			try {
				socket = new Socket();
				socket.connect(address);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				
				outputStream.writeUTF(serviceInterface.getName());
				outputStream.writeUTF(method.getName());
				outputStream.writeObject(method.getParameterTypes());
				outputStream.writeObject(args);
				outputStream.flush();
				
				inputStream = new ObjectInputStream(socket.getInputStream());
				//接收服务器的输出
				System.out.println(serviceInterface+"."+method.getName()+"()方法调用成功");
				Object result = inputStream.readObject();
				System.out.println("收到服务端响应: " + result);
				return result;
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (socket != null) {
					socket.close();
				}
			}
			return null;
		}
	}
	/**
	 * 获得远程服务的地址
	 */
	//private static InetSocketAddress getService(String serviceName) {
	//	List<InetSocketAddress> serviceVoList = getServiceList(serviceName);
	//	InetSocketAddress address = serviceVoList.get(random.nextInt(serviceVoList.size()));
	//	System.out.println("远程服务地址: " + address);
	//	return address;
	//}
}
