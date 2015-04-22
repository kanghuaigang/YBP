package com.yjy.bsq.wxpay.pojo;

import com.yjy.bsq.wxpay.common.Configure;
import com.yjy.bsq.wxpay.common.RandomStringGenerator;

public class RequestParam {
	// 随机字符串,随机字符串，不长于32位
	private String nonce_str;
	// 签名
	private String sign;
	// 商户订单号,商户订单号（每个订单号必须唯一）;组成： mch_id+yyyymmdd+10位一天内不能重复的数字。
	private String mch_billno; 
	// 商户号
	private String mch_id; 
	// 公众账号appid
	private String wxappid; 
	// 提供方名称
	private String nick_name; 
	// 商户名称
	private String send_name; 
	// 用户openid
	private String re_openid; 
	// 付款金额,付款金额，单位分
	private Integer total_amount;
	// 最小红包金额,最小红包金额，单位分
	private Integer min_value; 
	// 最大红包金额,最大红包金额，单位分
	private Integer max_value; 
	// 红包发放总人数
	private Integer total_num; 
	// 红包祝福语
	private String wishing; 
	// Ip地址,调用接口的机器Ip地址
	private String client_ip; 
	// 活动名称
	private String act_name; 
	// 备注
	private String remark; 
	
	public RequestParam() {
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(20);
		this.mch_id = Configure.getMchid();
		this.wxappid = Configure.getAppid();
		this.client_ip = Configure.getIP();
	}
	
	public RequestParam(String mchBillno, String nickName,
			String sendName, String reOpenid, Integer totalAmount,
			Integer minValue, Integer maxValue, Integer totalNum, String wishing,
			String actName, String remark) {
		this.mch_billno = mchBillno;
		this.nick_name = nickName;
		this.send_name = sendName;
		this.re_openid = reOpenid;
		this.total_amount = totalAmount;
		this.min_value = minValue;
		this.max_value = maxValue;
		this.total_num = totalNum;
		this.wishing = wishing;
		this.act_name = actName;
		this.remark = remark;
		this.nonce_str = RandomStringGenerator.getRandomStringByLength(20);
		this.mch_id = Configure.getMchid();
		this.wxappid = Configure.getAppid();
		this.client_ip = Configure.getIP();
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}

	public Integer getMin_value() {
		return min_value;
	}

	public void setMin_value(Integer min_value) {
		this.min_value = min_value;
	}

	public Integer getMax_value() {
		return max_value;
	}

	public void setMax_value(Integer max_value) {
		this.max_value = max_value;
	}

	public Integer getTotal_num() {
		return total_num;
	}

	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
