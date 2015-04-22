package com.yjy.bsq.wxpay.common;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 支付次数控制器
 * @author zhujiwu
 * 2015-4-8 13:55
 * 
 */
public class PayCountControl {

	private final int _MAX_COUNT;
	
	private final long _MINUTE;

	public PayCountControl(long time,int maxCount) {
		_MINUTE = time;
		_MAX_COUNT = maxCount;
		count = 0;
	}
	

	/**
	 * 计数器
	 */
	private int count;
	
	private void countReset(){
		count = 0;
	}
	
	private boolean timerStop = true;
	private Timer timer = new Timer();
	/**
	 * 启动计时器
	 * 从发送的第一条开始计时，1分钟后清空计数器
	 */
	private void startTimer(){
		if(timerStop){
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					PayCountControl.this.countReset();
					timerStop = true;
				}
			}, _MINUTE);
			timerStop = false;
		}
	}
	
	public void await(){
		while (count >= _MAX_COUNT) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void countDown(){
		count ++;
		startTimer();
	}

}
