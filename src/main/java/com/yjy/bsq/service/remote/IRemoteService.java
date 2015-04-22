package com.yjy.bsq.service.remote;

import java.util.Map;

public interface IRemoteService {
	
	/**
	 * 通过手机绑定微信号
	 * @param phone
	 * @param openId
	 * @return
	 */
	public Map<String,Object> boundPhone(String phone,String openId);
	
	/**
	 * 通过openId修改采购人员email
	 * @param openId
	 * @return
	 */
	public Map<String,Object> modifyEmail(String openId,String email);
	/**
	 * 通过openId查询采购人员email
	 * @param openId
	 * @return
	 */
	public String getEmailByOpenId(String openId);

}