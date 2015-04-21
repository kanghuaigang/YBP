package com.asc.bdhub.wxpay;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Arrays;
import java.util.Collection;

import com.asc.bdhub.wxpay.pojo.PayInfo;
import com.asc.bdhub.wxpay.service.PayService;
import com.asc.bdhub.wxpay.service.WeixinPayServiceImpl;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {

		Collection<String> reOpenids = Arrays.asList("o7a-wuDXXg4mhoIDHknagyOrwoLY", "o7a-wuAHHJ1It2UC9lxCUMxExOBQ");

		PayInfo info = new PayInfo();
		info.setRemark("测试");
		info.setWishing("红包测试");
		info.setAct_name("测试活动");
		info.setAmount(100);
		info.setNick_name("三九");
		info.setSend_name("三九");

		PayService payService = new WeixinPayServiceImpl();
		payService.sendRedPackets("o7a-wuDXXg4mhoIDHknagyOrwoLY", info);
		

	}

}
