package com.yjy.bsq.wxpay.pojo;

import java.io.Serializable;

public class PayInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	//amount 金额（单位：分）
	private int amount;

	// 提供方名称
	private String nick_name;
	// 商户名称
	private String send_name;

	//活动名称 
	private String act_name;

	//红包祝福语
	private String wishing;

	//备注
	private String remark;

	//商户logo的url
	private String logo_imgurl;

	//分享文案
	private String shareContent;

	//分享链接 
	private String share_url;

	//分享的图片
	private String share_imgurl;

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogo_imgurl() {
		return logo_imgurl;
	}

	public void setLogo_imgurl(String logo_imgurl) {
		this.logo_imgurl = logo_imgurl;
	}

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public String getShare_imgurl() {
		return share_imgurl;
	}

	public void setShare_imgurl(String share_imgurl) {
		this.share_imgurl = share_imgurl;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
	
}
