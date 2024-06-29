package com.loserico.rpc;

import com.loserico.rpc.vo.UserInfo;

/**
 * <p>
 * Copyright: (C), 2023-10-30 16:47
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public interface SendSms {
	
	/**
	 * 短信发送接口
	 * @param userInfo
	 */
	public void sendMail(UserInfo userInfo);
}
