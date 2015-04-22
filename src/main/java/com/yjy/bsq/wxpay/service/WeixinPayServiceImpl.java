package com.yjy.bsq.wxpay.service;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpHost;
import org.springframework.stereotype.Service;

import com.yjy.bsq.common.StaticInitProperties;
import com.yjy.bsq.wxpay.common.Configure;
import com.yjy.bsq.wxpay.common.HttpsRequest;
import com.yjy.bsq.wxpay.common.PayCountControl;
import com.yjy.bsq.wxpay.common.PropertiesNames;
import com.yjy.bsq.wxpay.common.Signature;
import com.yjy.bsq.wxpay.pojo.PayInfo;
import com.yjy.bsq.wxpay.pojo.RequestParam;
@Service
public class WeixinPayServiceImpl implements PayService {

	private final HttpsRequest request;
	
	private final int _MAX_SEND_NUMBER = 1800;
	
	private final int _MINUTE = 1000 * 60;
	
	private PayCountControl countControl;
	
	public WeixinPayServiceImpl() {
		try {
			String proxyEnable = StaticInitProperties.getParamValue(PropertiesNames.wxPay_proxy_enable);
			//是否启用代理模式
			if (BooleanUtils.toBoolean(proxyEnable)) {
				String hostname = StaticInitProperties.getParamValue(PropertiesNames.wxPay_proxy_hostname);
				String portStr = StaticInitProperties.getParamValue(PropertiesNames.wxPay_proxy_port);
				int port = Integer.parseInt(portStr);
				request = new HttpsRequest(new HttpHost(hostname, port));
			} else {
				request = new HttpsRequest();
			}
			//计数器，一分钟只能发1800条
			countControl = new PayCountControl(_MINUTE, _MAX_SEND_NUMBER);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PayException("初始化失败！", e);
		}
	}

	public void sendRedPackets(String reOpenid, PayInfo info) {
		int amount = info.getAmount();
		if (amount > Configure.RED_PACKETS_MAX_AMOUNT) {
			int div = amount / Configure.RED_PACKETS_MAX_AMOUNT;
			for (int i = 0; i < div; i++) {
				_sendRedPackets(reOpenid, info);
			}
			amount %= Configure.RED_PACKETS_MAX_AMOUNT;
			if (amount == 0) {
				return;
			}
		}
		info.setAmount(amount);
		_sendRedPackets(reOpenid, info);

	}
	
	public void sendRedPackets(Collection<String> reOpenids, PayInfo remark) {
		for (String reOpenid : reOpenids) {
			sendRedPackets(reOpenid, remark);
		}
	}

	private String _sendRedPackets(String reOpenid, PayInfo info) {
		StringBuilder mchBillno = new StringBuilder();
		mchBillno.append(Configure.getMchid()).append(DateFormatUtils.format(new Date(), "yyyyMMdd")).append(RandomStringUtils.randomNumeric(10));

		RequestParam param = new RequestParam();
		param.setAct_name(info.getAct_name());
		param.setWishing(info.getWishing());
		param.setRemark(info.getRemark());
		param.setNick_name(info.getNick_name());
		param.setSend_name(info.getSend_name());

		param.setMax_value(info.getAmount());
		param.setMin_value(info.getAmount());
		param.setTotal_amount(info.getAmount());
		param.setTotal_num(1);

		param.setMch_billno(mchBillno.toString());

		param.setRe_openid(reOpenid);

		try {
			param.setSign(Signature.getSign(param));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new PayException(e);
		}
		synchronized(request) {
			countControl.await();
			try {
				//TODO 存数据库
				String r = request.sendPost(Configure.PAY_API, param);
				return r;
			} catch (Exception e) {
				e.printStackTrace();
				throw new PayException("支付遇到问题！", e);
			}finally{
				countControl.countDown();
			}
		}
		
	}

	

}
