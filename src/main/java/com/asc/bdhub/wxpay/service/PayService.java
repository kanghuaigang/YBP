package com.asc.bdhub.wxpay.service;

import java.util.Collection;

import com.asc.bdhub.wxpay.pojo.PayInfo;

public interface PayService {
	/**
	 * 发送红包
	 * @param amount 金额（单位：分）
	 * @param reOpenid 接受收红包的用户（用户在wxappid下的openid） 
	 * @return
	 */
	void sendRedPackets(String reOpenid,PayInfo remark);
	
	/**
	 * 发送红包
	 * @param reOpenids 接受收红包的用户（用户在wxappid下的openid） 
	 * @return
	 */
	void sendRedPackets(Collection<String> reOpenids,PayInfo remark);
}
